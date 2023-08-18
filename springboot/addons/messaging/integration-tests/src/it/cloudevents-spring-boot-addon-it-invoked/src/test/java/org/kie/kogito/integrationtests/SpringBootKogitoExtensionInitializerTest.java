package org.kie.kogito.integrationtests;

import io.cloudevents.CloudEvent;
import io.cloudevents.core.provider.ExtensionProvider;
import org.junit.jupiter.api.Test;
import org.kie.kogito.app.KogitoSpringbootApplication;
import org.kie.kogito.event.cloudevents.utils.CloudEventUtils;
import org.kie.kogito.event.cloudevents.extension.KogitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = KogitoSpringbootApplication.class)
public class SpringBootKogitoExtensionInitializerTest {

    private static final String MODEL_NAME = "TestModelName";
    private static final String MODEL_NAMESPACE = "TestModelNamespace";

    @Test
    public void testKogitoExtension() {
        String eventJson = "" +
                "{\n" +
                "  \"specversion\": \"1.0\",\n" +
                "  \"id\": \"SomeEventId\",\n" +
                "  \"source\": \"SomeEventSource\",\n" +
                "  \"type\": \"SomeEventType\",\n" +
                "  \"" + KogitoExtension.KOGITO_DMN_MODEL_NAME + "\": \"" + MODEL_NAME + "\",\n" +
                "  \"" + KogitoExtension.KOGITO_DMN_MODEL_NAMESPACE + "\": \"" + MODEL_NAMESPACE + "\",\n" +
                "  \"data\": \"{}\"" +
                "}";

        CloudEvent event = CloudEventUtils.decode(eventJson).orElseThrow(IllegalStateException::new);
        KogitoExtension kogitoExtension = ExtensionProvider.getInstance().parseExtension(KogitoExtension.class, event);

        assertNotNull(kogitoExtension, "KogitoExtension not registered, please make sure " +
                "bean org.kie.kogito.addon.cloudevents.spring.SpringBootKogitoExtensionInitializer has been loaded");
        assertEquals(MODEL_NAME, kogitoExtension.getDmnModelName());
        assertEquals(MODEL_NAMESPACE, kogitoExtension.getDmnModelNamespace());
    }
}
