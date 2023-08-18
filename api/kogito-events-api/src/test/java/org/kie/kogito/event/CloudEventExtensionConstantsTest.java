package org.kie.kogito.event;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;
import org.kie.kogito.event.cloudevents.CloudEventExtensionConstants;

import static org.assertj.core.api.Assertions.assertThat;

class CloudEventExtensionConstantsTest {

    @Test
    void verifyKeysNamingConvention() {
        final Pattern nameValidation = Pattern.compile("^[a-z0-9]{1,20}$");
        assertThat(CloudEventExtensionConstants.ADDONS).matches(nameValidation);
        assertThat(CloudEventExtensionConstants.PMML_FULL_RESULT).matches(nameValidation);
        assertThat(CloudEventExtensionConstants.PMML_MODEL_NAME).matches(nameValidation);
        assertThat(CloudEventExtensionConstants.PROCESS_ID).matches(nameValidation);
        assertThat(CloudEventExtensionConstants.PROCESS_TYPE).matches(nameValidation);
        assertThat(CloudEventExtensionConstants.PROCESS_PARENT_PROCESS_INSTANCE_ID).matches(nameValidation);
        assertThat(CloudEventExtensionConstants.PROCESS_INSTANCE_ID).matches(nameValidation);
        assertThat(CloudEventExtensionConstants.PROCESS_INSTANCE_VERSION).matches(nameValidation);
        assertThat(CloudEventExtensionConstants.PROCESS_INSTANCE_STATE).matches(nameValidation);
        assertThat(CloudEventExtensionConstants.PROCESS_REFERENCE_ID).matches(nameValidation);
        assertThat(CloudEventExtensionConstants.PROCESS_ROOT_PROCESS_ID).matches(nameValidation);
        assertThat(CloudEventExtensionConstants.PROCESS_ROOT_PROCESS_INSTANCE_ID).matches(nameValidation);
        assertThat(CloudEventExtensionConstants.PROCESS_START_FROM_NODE).matches(nameValidation);
        assertThat(CloudEventExtensionConstants.PROCESS_USER_TASK_INSTANCE_ID).matches(nameValidation);
        assertThat(CloudEventExtensionConstants.PROCESS_USER_TASK_INSTANCE_STATE).matches(nameValidation);
        assertThat(CloudEventExtensionConstants.RULE_UNIT_ID).matches(nameValidation);
        assertThat(CloudEventExtensionConstants.RULE_UNIT_QUERY).matches(nameValidation);
    }

}