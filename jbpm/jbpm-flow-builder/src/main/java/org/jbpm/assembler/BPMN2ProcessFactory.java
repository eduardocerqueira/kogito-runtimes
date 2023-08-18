package org.jbpm.assembler;

import org.kie.api.internal.utils.KieService;
import org.kie.internal.builder.KnowledgeBuilder;

public class BPMN2ProcessFactory {

    private BPMN2ProcessFactory() {

    }

    private static class LazyHolder {
        private static BPMN2ProcessProvider provider = KieService.load(BPMN2ProcessProvider.class);
    }

    public static void configurePackageBuilder(KnowledgeBuilder kBuilder) {
        getBPMN2ProcessProvider().configurePackageBuilder(kBuilder);
    }

    public static BPMN2ProcessProvider getBPMN2ProcessProvider() {
        return LazyHolder.provider;
    }
}
