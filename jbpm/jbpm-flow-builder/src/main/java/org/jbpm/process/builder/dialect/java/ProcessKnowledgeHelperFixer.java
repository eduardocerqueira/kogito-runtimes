package org.jbpm.process.builder.dialect.java;

import java.util.HashMap;
import java.util.Map;

import org.mvel2.Macro;
import org.mvel2.MacroProcessor;

public class ProcessKnowledgeHelperFixer {

    private ProcessKnowledgeHelperFixer() {

    }

    private static final Map macros;

    static {
        macros = new HashMap(5);

        macros.put("insert",
                new Macro() {
                    public String doMacro() {
                        return "kcontext.getKieRuntime().insert";
                    }
                });
    }

    public static String fix(final String raw) {
        if (raw == null || "".equals(raw.trim())) {
            return raw;
        }

        MacroProcessor macroProcessor = new MacroProcessor();
        macroProcessor.setMacros(macros);
        return macroProcessor.parse(raw);
    }
}