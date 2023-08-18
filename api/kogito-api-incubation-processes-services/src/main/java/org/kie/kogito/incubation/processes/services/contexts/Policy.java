package org.kie.kogito.incubation.processes.services.contexts;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class Policy {
    public static Policy of(String user, List<String> groups) {
        return new Policy(user, groups);
    }

    @JsonProperty
    private String user;
    @JsonProperty
    private List<String> groups;

    protected Policy() {
    }

    protected Policy(String user, List<String> groups) {
        this.user = user;
        this.groups = groups;
    }

    public String user() {
        return user;
    }

    void setUser(String user) {
        this.user = user;
    }

    public List<String> groups() {
        return groups;
    }

    void setGroups(List<String> groups) {
        this.groups = groups;
    }

}
