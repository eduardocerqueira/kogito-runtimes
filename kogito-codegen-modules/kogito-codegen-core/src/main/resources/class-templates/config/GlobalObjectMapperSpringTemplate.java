package $Package$;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@SpringBootConfiguration
public class GlobalObjectMapper {
    
    @Autowired
    ConfigBean configBean;

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizeGlobalObjectMapper() {
        return new Jackson2ObjectMapperBuilderCustomizer() {
            @Override
            public void customize(Jackson2ObjectMapperBuilder builder) {
                if (!configBean.failOnEmptyBean()) {
                    builder.featuresToDisable (SerializationFeature.FAIL_ON_EMPTY_BEANS);
                }
                builder.dateFormat(new StdDateFormat().withColonInTimeZone(true).withTimeZone(TimeZone.getDefault()));
                builder.modulesToInstall(new JavaTimeModule());
            }
        };
    }
}