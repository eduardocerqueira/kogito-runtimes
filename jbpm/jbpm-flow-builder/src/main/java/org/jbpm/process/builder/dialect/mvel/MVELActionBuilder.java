package org.jbpm.process.builder.dialect.mvel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.drools.compiler.compiler.DescrBuildError;
import org.drools.compiler.rule.builder.PackageBuildContext;
import org.drools.drl.ast.descr.ActionDescr;
import org.drools.mvel.MVELDialectRuntimeData;
import org.drools.mvel.builder.MVELAnalysisResult;
import org.drools.mvel.builder.MVELDialect;
import org.drools.mvel.expr.MVELCompilationUnit;
import org.jbpm.process.builder.ActionBuilder;
import org.jbpm.process.builder.ProcessBuildContext;
import org.jbpm.process.core.ContextResolver;
import org.jbpm.process.core.context.variable.VariableScope;
import org.jbpm.process.instance.impl.MVELAction;
import org.jbpm.workflow.core.DroolsAction;
import org.mvel2.Macro;
import org.mvel2.MacroProcessor;

public class MVELActionBuilder extends AbstractMVELBuilder implements ActionBuilder {

    private static final Map<String, Macro> macros = new HashMap<>(5);
    static {
        macros.put("insert",
                new Macro() {
                    public String doMacro() {
                        return "kcontext.getKieRuntime().insert";
                    }
                });
    }

    public MVELActionBuilder() {

    }

    public static String processMacros(String consequence) {
        MacroProcessor macroProcessor = new MacroProcessor();
        macroProcessor.setMacros(macros);
        return macroProcessor.parse(delimitExpressions(consequence));
    }

    @Override
    public void build(final PackageBuildContext context,
            final DroolsAction action,
            final ActionDescr actionDescr,
            final ContextResolver contextResolver) {

        String text = processMacros(actionDescr.getText());
        Map<String, Class<?>> variables = new HashMap<>();

        try {
            MVELDialect dialect = (MVELDialect) context.getDialect("mvel");
            MVELAnalysisResult analysis = getAnalysis(context, actionDescr, dialect, text, variables);

            if (analysis == null) {
                // not possible to get the analysis results
                return;
            }

            buildAction(context,
                    action,
                    actionDescr,
                    contextResolver,
                    dialect,
                    analysis,
                    text,
                    variables);
        } catch (final Exception e) {
            context.getErrors().add(new DescrBuildError(context.getParentDescr(),
                    actionDescr,
                    null,
                    "Unable to build expression for action '" + actionDescr.getText() + "' :" + e));
        }
    }

    protected void buildAction(final PackageBuildContext context,
            final DroolsAction action,
            final ActionDescr actionDescr,
            final ContextResolver contextResolver,
            final MVELDialect dialect,
            final MVELAnalysisResult analysis,
            final String text,
            Map<String, Class<?>> variables) throws Exception {

        Set<String> variableNames = analysis.getNotBoundedIdentifiers();
        if (contextResolver != null) {
            for (String variableName : variableNames) {
                if (analysis.getMvelVariables().keySet().contains(variableName)
                        || variableName.equals("kcontext")
                        || variableName.equals("context")) {
                    continue;
                }
                VariableScope variableScope = (VariableScope) contextResolver.resolveContext(VariableScope.VARIABLE_SCOPE, variableName);
                if (variableScope == null) {
                    context.getErrors().add(
                            new DescrBuildError(context.getParentDescr(),
                                    actionDescr,
                                    null,
                                    "Could not find variable '" + variableName + "' "
                                            + "for action '" + actionDescr.getText() + "'"));
                } else {
                    variables.put(variableName,
                            context.getDialect().getTypeResolver().resolveType(
                                    variableScope.findVariable(variableName).getType().getStringType()));
                }
            }
        }

        MVELCompilationUnit unit = dialect.getMVELCompilationUnit(text,
                analysis,
                null,
                null,
                variables,
                context,
                "context",
                org.kie.api.runtime.process.ProcessContext.class,
                false,
                MVELCompilationUnit.Scope.EXPRESSION);
        MVELAction expr = new MVELAction(unit, context.getDialect().getId());
        action.setMetaData("Action", expr);

        MVELDialectRuntimeData data = (MVELDialectRuntimeData) context.getPkg().getDialectRuntimeRegistry().getDialectData(dialect.getId());
        data.addCompileable(action, expr);

        expr.compile(data);

        collectTypes("MVELDialect", analysis, (ProcessBuildContext) context);
    }

}
