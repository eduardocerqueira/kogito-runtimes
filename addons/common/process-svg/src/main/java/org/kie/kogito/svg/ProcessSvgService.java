package org.kie.kogito.svg;

import java.util.Optional;

public interface ProcessSvgService {

    Optional<String> getProcessInstanceSvg(String processId, String processInstanceId, String authHeader);

    Optional<String> getProcessSvg(String processId);
}