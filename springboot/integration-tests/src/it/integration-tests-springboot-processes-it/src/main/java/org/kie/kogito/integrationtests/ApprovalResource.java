package org.kie.kogito.integrationtests;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import org.kie.kogito.Model;
import org.kie.kogito.process.Process;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/approvalsdetails")
public class ApprovalResource {

    @Autowired
    @Qualifier("approvals")
    Process<? extends Model> process;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getWorkflowType() {
        return ResponseEntity.ok(Map.of("type", process.type()));
    }

}
