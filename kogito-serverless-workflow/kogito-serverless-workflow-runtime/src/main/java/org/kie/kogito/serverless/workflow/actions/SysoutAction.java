package org.kie.kogito.serverless.workflow.actions;

import org.kie.kogito.internal.process.runtime.KogitoProcessContext;
import org.kie.kogito.internal.utils.ConversionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SysoutAction extends BaseExpressionAction {

    private static final Logger logger = LoggerFactory.getLogger(SysoutAction.class);

    protected final WorkflowLogLevel logLevel;
    protected final boolean validExpr;

    public SysoutAction(String lang, String expr, String inputVar, String level) {
        super(lang, expr, inputVar);
        logLevel = ConversionUtils.isEmpty(level) ? WorkflowLogLevel.INFO : WorkflowLogLevel.valueOf(level);
        this.validExpr = super.expr.isValid();
    }

    public SysoutAction(String lang, String expr, String inputVar, WorkflowLogLevel logLevel, boolean validExpr) {
        super(lang, expr, inputVar);
        this.logLevel = logLevel;
        this.validExpr = validExpr;
    }

    @Override
    public void execute(KogitoProcessContext context) throws Exception {
        log(validExpr ? evaluate(context, String.class) : expr.asString());
    }

    private void log(String message) {
        switch (logLevel) {
            case TRACE:
                logger.trace(message);
                break;
            case DEBUG:
                logger.debug(message);
                break;
            case INFO:
                logger.info(message);
                break;
            case WARN:
                logger.warn(message);
                break;
            case ERROR:
                logger.error(message);
                break;
        }
    }
}
