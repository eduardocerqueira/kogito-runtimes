package org.kie.kogito.jobs.api.event;

import static org.assertj.core.api.Assertions.assertThat;

abstract class AbstractProcessInstanceContextJobCloudEventTest<E extends ProcessInstanceContextJobCloudEvent<?>> extends AbstractJobCloudEventTest<E> {

    static final String PROCESS_ID = "PROCESS_ID";
    static final String PROCESS_INSTANCE_ID = "PROCESS_INSTANCE_ID";
    static final String ROOT_PROCESS_INSTANCE_ID = "ROOT_PROCESS_INSTANCE_ID";
    static final String ROOT_PROCESS_ID = "ROOT_PROCESS_ID";
    static final String ADDONS = "ADDONS";

    @Override
    void assertFields(E event) {
        super.assertFields(event);
        assertThat(event.getProcessInstanceId()).isEqualTo(PROCESS_INSTANCE_ID);
        assertThat(event.getProcessId()).isEqualTo(PROCESS_ID);
        assertThat(event.getRootProcessInstanceId()).isEqualTo(ROOT_PROCESS_INSTANCE_ID);
        assertThat(event.getRootProcessId()).isEqualTo(ROOT_PROCESS_ID);
        assertThat(event.getKogitoAddons()).isEqualTo(ADDONS);
    }
}
