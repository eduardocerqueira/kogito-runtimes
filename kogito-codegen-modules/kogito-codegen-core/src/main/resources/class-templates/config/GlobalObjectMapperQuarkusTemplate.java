package $Package$;

import javax.inject.Singleton;

import java.util.TimeZone;

import javax.inject.Inject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.cloudevents.jackson.JsonFormat;
import io.quarkus.jackson.ObjectMapperCustomizer;

@Singleton
public class GlobalObjectMapper implements ObjectMapperCustomizer {
    
    @Inject
    org.kie.kogito.config.ConfigBean configBean;

    public void customize(ObjectMapper mapper) {
        if (!configBean.failOnEmptyBean()) {
            mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        }
        mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true).withTimeZone(TimeZone.getDefault()));
        mapper.registerModule(new JavaTimeModule()).registerModule(JsonFormat.getCloudEventJacksonModule());
    }
}