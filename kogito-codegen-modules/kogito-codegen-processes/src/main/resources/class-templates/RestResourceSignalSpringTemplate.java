package com.myspace.demo;

import java.util.List;

import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.impl.Sig;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public class $Type$Resource {

    Process<$Type$> process;

    @PostMapping(value = "/{id}/$signalPath$", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public $Type$Output signal(@PathVariable("id") final String id, final @RequestBody(required = false) $signalType$ data) {
        return processService.signalProcessInstance(process, id, data, "$signalName$")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}