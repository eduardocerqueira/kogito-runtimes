package org.kie.kogito.legacy.rules;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.*;

import org.kie.api.runtime.KieRuntimeBuilder;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

@Path("/test-tms")
public class TmsEndpoint {
    @Inject
    KieRuntimeBuilder kieRuntimeBuilder;

    private KieSession session;

    private final Map<String, FactHandle> map = new HashMap<>();

    @PostConstruct
    void init() {
        this.session = kieRuntimeBuilder.newKieSession();
    }

    @GET()
    public int executeQuery() {
        return session.getObjects(Integer.class::isInstance).stream().map(Integer.class::cast).mapToInt(Integer::intValue).findFirst().orElse(-1);
    }

    @POST
    public int insert(@QueryParam("string") String string) {
        map.put(string, session.insert(string));
        return session.fireAllRules();
    }

    @DELETE
    public int delete(@QueryParam("string") String string) {
        session.delete(map.get(string));
        return session.fireAllRules();
    }
}
