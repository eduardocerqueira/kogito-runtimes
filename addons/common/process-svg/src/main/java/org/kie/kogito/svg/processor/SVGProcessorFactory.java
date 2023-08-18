package org.kie.kogito.svg.processor;

import org.w3c.dom.Document;

public class SVGProcessorFactory {

    public SVGProcessor create(Document svg) {
        return new DefaultSVGProcessor(svg);
    }
}
