package org.jbpm.bpmn2.core;

import java.io.Serializable;

public class Expression implements Serializable {

    private static final long serialVersionUID = 2451747420211773744L;
    private String id;
    private String lang;
    private String script;
    private String outcomeType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getOutcomeType() {
        return outcomeType;
    }

    public void setOutcomeType(String outcomeType) {
        this.outcomeType = outcomeType;
    }

}