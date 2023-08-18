package org.kie.kogito.svg.model;

import java.util.Optional;

import org.w3c.dom.Element;

public class NodeSummary {

    private final String nodeId;
    private final Element border;
    private final Element borderSubProcess;
    private final Element background;
    private final Element subProcessLink;
    private final Optional<RenderType> renderType;

    public NodeSummary(String nodeId, Element border, Element background, Element borderSubProcess, Element subProcessLink) {
        this(nodeId, border, background, borderSubProcess, subProcessLink, null);
    }

    public NodeSummary(String nodeId) {
        this(nodeId, null, null, null, null, null);
    }

    public NodeSummary(String nodeId, Element border, Element background, Element borderSubProcess, Element subProcessLink, RenderType renderType) {
        this.nodeId = nodeId;
        this.border = border;
        this.background = background;
        this.borderSubProcess = borderSubProcess;
        this.subProcessLink = subProcessLink;
        this.renderType = Optional.ofNullable(renderType);
    }

    public String getNodeId() {
        return nodeId;
    }

    public Element getBorder() {
        return border;
    }

    public Element getBackground() {
        return background;
    }

    public Element getBorderSubProcess() {
        return borderSubProcess;
    }

    public Element getSubProcessLink() {
        return subProcessLink;
    }

    public Optional<RenderType> getRenderType() {
        return renderType;
    }
}