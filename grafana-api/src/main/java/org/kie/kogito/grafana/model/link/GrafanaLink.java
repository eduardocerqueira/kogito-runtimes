package org.kie.kogito.grafana.model.link;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GrafanaLink {

    @JsonProperty("icon")
    public String icon = "external link";

    @JsonProperty("tags")
    public List<String> tags;

    @JsonProperty("includeVars")
    public boolean includeVars = true;

    @JsonProperty("keepTime")
    public boolean keepTime = true;

    @JsonProperty("targetBlank")
    public boolean targetBlank = true;

    @JsonProperty("title")
    public String title;

    @JsonProperty("tooltip")
    public String tooltip = "";

    @JsonProperty("type")
    public String type = "link";

    @JsonProperty("url")
    public String url;

    public GrafanaLink() {
    }

    public GrafanaLink(String title, String url) {
        this.title = title;
        this.url = url;
    }
}