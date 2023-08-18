package org.kie.kogito.test.utils;

import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.ComparisonResult;
import org.xmlunit.diff.Diff;
import org.xmlunit.diff.DifferenceEvaluators;

public class CustomSVGDiffer {

    private DiffBuilder diffBuilder;

    public CustomSVGDiffer(String content) {
        // Configure the DiffBuilder to ignore whitespace, element order and the svf tag
        diffBuilder = DiffBuilder.compare(Input.fromString(content))
                .ignoreWhitespace()
                .ignoreElementContentWhitespace()
                .checkForSimilar()
                .withDifferenceEvaluator(DifferenceEvaluators.chain(
                        DifferenceEvaluators.Default,
                        (comparison, outcome) -> {
                            //this tag may differ from svg processors like batik
                            if (comparison.getControlDetails().getTarget().getNodeName().equals("svg")) {
                                return ComparisonResult.SIMILAR;
                            }
                            return outcome;
                        }));
    }

    public Diff withTest(Object item) {
        return diffBuilder.withTest(item).build();
    }
}
