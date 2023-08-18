package org.kie.kogito.jobs.management.quarkus;

import java.net.URI;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.kie.kogito.jobs.ProcessInstanceJobDescription;
import org.kie.kogito.jobs.ProcessJobDescription;
import org.kie.kogito.jobs.management.RestJobsService;
import org.kie.kogito.jobs.service.api.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import io.vertx.core.Vertx;
import io.vertx.core.json.jackson.DatabindCodec;
import io.vertx.ext.web.client.WebClient;
import io.vertx.ext.web.client.WebClientOptions;

import static org.kie.kogito.jobs.service.api.serlialization.SerializationUtils.registerDescriptors;

@ApplicationScoped
public class VertxJobsService extends RestJobsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(VertxJobsService.class);

    private Vertx vertx;

    private Instance<WebClient> providedWebClient;

    private WebClient client;

    @Inject
    public VertxJobsService(@ConfigProperty(name = "kogito.jobs-service.url") String jobServiceUrl,
            @ConfigProperty(name = "kogito.service.url") String callbackEndpoint,
            Vertx vertx,
            Instance<WebClient> providedWebClient,
            ObjectMapper objectMapper) {
        super(jobServiceUrl, callbackEndpoint, objectMapper);
        this.vertx = vertx;
        this.providedWebClient = providedWebClient;
    }

    VertxJobsService() {
        this(null, null, null, null, null);
    }

    @PostConstruct
    void initialize() {
        configureMapper(DatabindCodec.mapper());
        configureMapper(DatabindCodec.prettyMapper());

        if (providedWebClient.isResolvable()) {
            this.client = providedWebClient.get();
            LOGGER.debug("Using provided web client instance");
        } else {
            final URI jobServiceURL = getJobsServiceUri();
            this.client = WebClient.create(vertx,
                    new WebClientOptions()
                            .setDefaultHost(jobServiceURL.getHost())
                            .setDefaultPort(jobServiceURL.getPort()));
            LOGGER.debug("Creating new instance of web client for host {} and port {}", jobServiceURL.getHost(), jobServiceURL.getPort());
        }
    }

    @Override
    public String scheduleProcessJob(ProcessJobDescription description) {

        throw new UnsupportedOperationException("Scheduling for process jobs is not yet implemented");
    }

    @Override
    public String scheduleProcessInstanceJob(ProcessInstanceJobDescription description) {
        String callback = getCallbackEndpoint(description);
        LOGGER.debug("Job to be scheduled {} with callback URL {}", description, callback);
        final Job job = buildJob(description, callback);
        client.post(JOBS_PATH).sendJson(job, res -> {
            int status = res.result() != null ? res.result().statusCode() : 0;
            if (res.succeeded() && status == 200) {
                LOGGER.debug("Creating of the job {} done with status code {} ", job, status);
            } else {
                LOGGER.error("Scheduling of job {} failed with response code {}", job, status, res.cause());
            }
        });
        return job.getId();
    }

    @Override
    public boolean cancelJob(String id) {
        client.delete(JOBS_PATH + "/" + id).send(res -> {
            if (res.succeeded() && (res.result().statusCode() == 200 || res.result().statusCode() == 404)) {
                LOGGER.debug("Canceling of the job {} done with status code {} ", id, res.result().statusCode());
            } else {
                LOGGER.error("Canceling of job {} failed with response code {}", id, res.result().statusCode(), res.cause());
            }
        });

        return true;
    }

    private void configureMapper(ObjectMapper mapper) {
        mapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.registerModule(new JavaTimeModule());
        registerDescriptors(mapper);
        mapper.findAndRegisterModules();
    }
}