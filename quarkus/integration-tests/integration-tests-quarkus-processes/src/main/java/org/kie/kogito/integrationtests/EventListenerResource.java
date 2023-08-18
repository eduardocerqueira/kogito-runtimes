package org.kie.kogito.integrationtests;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/events")
@Produces(MediaType.APPLICATION_JSON)
public class EventListenerResource {

    @Inject
    UnitOfWorkTestEventListener listener;

    @DELETE
    public void reset() {
        listener.reset();
    }

    @GET
    public Map<String, Integer> events() {
        Map<String, Integer> events = new HashMap<>();
        events.put("start", listener.getStartEvents().size());
        events.put("end", listener.getEndEvents().size());
        events.put("abort", listener.getAbortEvents().size());
        return events;
    }

}
