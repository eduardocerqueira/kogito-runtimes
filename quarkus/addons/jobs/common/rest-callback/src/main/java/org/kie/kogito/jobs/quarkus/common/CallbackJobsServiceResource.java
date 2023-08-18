package org.kie.kogito.jobs.quarkus.common;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.kie.kogito.Application;
import org.kie.kogito.jobs.api.JobCallbackPayload;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.Processes;
import org.kie.kogito.services.jobs.impl.TriggerJobCommand;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.kie.kogito.jobs.api.JobCallbackResourceDef.JOBS_CALLBACK_POST_URI;
import static org.kie.kogito.jobs.api.JobCallbackResourceDef.JOBS_CALLBACK_URI;
import static org.kie.kogito.jobs.api.JobCallbackResourceDef.LIMIT;
import static org.kie.kogito.jobs.api.JobCallbackResourceDef.LIMIT_DEFAULT_VALUE;
import static org.kie.kogito.jobs.api.JobCallbackResourceDef.PROCESS_ID;
import static org.kie.kogito.jobs.api.JobCallbackResourceDef.PROCESS_INSTANCE_ID;
import static org.kie.kogito.jobs.api.JobCallbackResourceDef.TIMER_ID;

@Path(JOBS_CALLBACK_URI)
public class CallbackJobsServiceResource {

    @Inject
    Instance<Processes> processes;

    @Inject
    Instance<Application> application;

    @Inject
    ObjectMapper objectMapper;

    @POST
    @Path(JOBS_CALLBACK_POST_URI)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response triggerTimer(@PathParam(PROCESS_ID) String processId,
            @PathParam(PROCESS_INSTANCE_ID) String processInstanceId,
            @PathParam(TIMER_ID) String timerId,
            @QueryParam(LIMIT) @DefaultValue(LIMIT_DEFAULT_VALUE) Integer limit,
            String payload) {
        if (processId == null || processInstanceId == null) {
            return Response.status(Status.BAD_REQUEST).entity("Process id and Process instance id must be given").build();
        }

        Process<?> process = processes.get().processById(processId);
        if (process == null) {
            return Response.status(Status.NOT_FOUND).entity("Process with id " + processId + " not found").build();
        }

        String correlationId = null;
        if (payload != null && !payload.isBlank()) {
            try {
                JobCallbackPayload jobPayload = objectMapper.readValue(payload, JobCallbackPayload.class);
                correlationId = jobPayload.getCorrelationId();
            } catch (Exception e) {
                return Response.status(Status.BAD_REQUEST).entity("Invalid payload: " + payload + ". " + e.getMessage()).build();
            }
        }

        return new TriggerJobCommand(processInstanceId, correlationId, timerId, limit, process, application.get().unitOfWorkManager()).execute()
                ? Response.status(Status.OK).build()
                : Response.status(Status.NOT_FOUND).entity("Process instance with id " + processInstanceId + " not found").build();

    }
}
