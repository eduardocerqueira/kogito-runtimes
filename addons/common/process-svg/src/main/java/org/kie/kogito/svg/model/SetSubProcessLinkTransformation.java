package org.kie.kogito.svg.model;

import org.w3c.dom.Element;

public class SetSubProcessLinkTransformation extends NodeTransformation {

    private String link;

    public SetSubProcessLinkTransformation(String nodeId, String link) {
        super(nodeId);
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void transform(SVGSummary summary) {
        NodeSummary node = summary.getNode(getNodeId());
        if (node != null) {
            Element linkNode = node.getSubProcessLink();
            if (linkNode != null) {
                linkNode.setAttribute("onclick", "");
                linkNode.setAttribute("xlink:href", link);
                linkNode.setAttribute("target", "_blank");
            }
        }
    }
}
