package org.kie.kogito.process.management;

import java.util.Map;

import org.kie.kogito.Model;
import org.kie.kogito.process.Process;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/version")
public class VersionRestController {

    @Autowired
    @Qualifier("approvals")
    Process<? extends Model> approvalProcess;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getVersion() {
        return ResponseEntity.ok(Map.of("version", approvalProcess.version()));
    }
}
