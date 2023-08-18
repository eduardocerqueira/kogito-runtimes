package com.myspace.demo;

import org.kie.kogito.process.Process;

public class $Type$Resource {

    Process<$Type$> process;

    @POST
    @Path("/{id}/$signalPath$")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public $Type$Output signal(@PathParam("id") final String id, final $signalType$ data) {
        return processService.signalProcessInstance(process, id, data, "$signalName$")
                .orElseThrow(() -> new NotFoundException());
    }
}