package org.kie.kogito.integrationtests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.kie.kogito.dmn.DecisionTestUtils;
import org.kie.kogito.event.cloudevents.extension.KogitoExtension;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.CloudEventUtils;
import io.cloudevents.core.provider.ExtensionProvider;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class QuarkusKogitoExtensionInitializerTest {

    @Test
    public void test() {
        String eventJson = "" +
                "{\n" +
                "  \"specversion\": \"1.0\",\n" +
                "  \"id\": \"SomeEventId\",\n" +
                "  \"source\": \"SomeEventSource\",\n" +
                "  \"type\": \"SomeEventType\",\n" +
                "  \"" + KogitoExtension.KOGITO_DMN_MODEL_NAME + "\": \"" + DecisionTestUtils.MODEL_NAME + "\",\n" +
                "  \"" + KogitoExtension.KOGITO_DMN_MODEL_NAMESPACE + "\": \"" + DecisionTestUtils.MODEL_NAMESPACE + "\",\n" +
                "  \"data\": \"{}\"" +
                "}";

        CloudEvent event = CloudEventUtils.decode(eventJson).orElseThrow(IllegalStateException::new);
        KogitoExtension kogitoExtension = ExtensionProvider.getInstance().parseExtension(KogitoExtension.class, event);

        assertNotNull(kogitoExtension, "KogitoExtension not registered, please make sure " +
                "bean org.kie.kogito.addon.quarkus.messaging.common.QuarkusKogitoExtensionInitializer has been loaded");
        assertEquals(DecisionTestUtils.MODEL_NAME, kogitoExtension.getDmnModelName());
        assertEquals(DecisionTestUtils.MODEL_NAMESPACE, kogitoExtension.getDmnModelNamespace());
    }
}
