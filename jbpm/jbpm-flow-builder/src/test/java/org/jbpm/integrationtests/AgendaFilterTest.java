package org.jbpm.integrationtests;

import org.jbpm.test.util.AbstractBaseTest;
import org.junit.jupiter.api.Test;
import org.kie.kogito.internal.process.runtime.KogitoProcessRuntime;

import static org.assertj.core.api.Assertions.assertThat;

public class AgendaFilterTest extends AbstractBaseTest {

    public static class Message {

        private String message;

        private int status;

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    @Test
    public void testGetListeners() {
        // JBRULES-3378
        if (builder.hasErrors()) {
            throw new RuntimeException(builder.getErrors().toString());
        }

        KogitoProcessRuntime kruntime = createKogitoProcessRuntime();
        assertThat(kruntime).isNotNull();

        assertThat(kruntime.getKieSession().getAgendaEventListeners()).isNotEmpty();
        assertThat(kruntime.getProcessEventManager().getProcessEventListeners()).isEmpty();
        assertThat(kruntime.getKieSession().getRuleRuntimeEventListeners()).isEmpty();

        kruntime.getKieSession().dispose();
    }
}
