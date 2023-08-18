package org.kie.kogito.jobs.management;

import java.net.URI;
import java.util.Objects;

import org.kie.kogito.jobs.JobsService;
import org.kie.kogito.jobs.ProcessInstanceJobDescription;
import org.kie.kogito.jobs.api.URIBuilder;
import org.kie.kogito.jobs.service.api.Job;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.kie.kogito.jobs.api.JobCallbackResourceDef.buildCallbackPatternJob;
import static org.kie.kogito.jobs.api.JobCallbackResourceDef.buildCallbackURI;

public abstract class RestJobsService implements JobsService {

    @SuppressWarnings("squid:S1075")
    public static final String JOBS_PATH = "/v2/jobs";

    private URI jobsServiceUri;
    private String callbackEndpoint;
    private ObjectMapper objectMapper;

    public RestJobsService(String jobServiceUrl, String callbackEndpoint, ObjectMapper objectMapper) {
        this.jobsServiceUri = Objects.nonNull(jobServiceUrl) ? buildJobsServiceURI(jobServiceUrl) : null;
        this.callbackEndpoint = callbackEndpoint;
        this.objectMapper = objectMapper;
    }

    public String getCallbackEndpoint(ProcessInstanceJobDescription description) {
        return buildCallbackURI(description, callbackEndpoint);
    }

    private URI buildJobsServiceURI(String jobServiceUrl) {
        return URIBuilder.toURI(jobServiceUrl + JOBS_PATH);
    }

    public URI getJobsServiceUri() {
        return jobsServiceUri;
    }

    public Job buildJob(ProcessInstanceJobDescription description, String callback) {
        return buildCallbackPatternJob(description, callback, objectMapper);
    }
}
