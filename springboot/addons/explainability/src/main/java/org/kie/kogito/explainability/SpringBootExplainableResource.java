package org.kie.kogito.explainability;

import java.util.List;

import org.kie.kogito.Application;
import org.kie.kogito.explainability.model.PredictInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/predict")
public class SpringBootExplainableResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootExplainableResource.class);
    private static final ExplainabilityService explainabilityService = ExplainabilityService.INSTANCE;

    private final Application application;

    @Autowired
    public SpringBootExplainableResource(Application application) {
        this.application = application;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity predict(@RequestBody List<PredictInput> inputs) {
        try {
            return ResponseEntity.ok(explainabilityService.processRequest(application, inputs));
        } catch (Exception e) {
            LOGGER.warn("An Exception occurred processing the predict request", e);
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}