package org.kie.kogito.svg.service;

import java.util.Optional;

import org.kie.kogito.svg.AbstractProcessSvgService;
import org.kie.kogito.svg.dataindex.DataIndexClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SpringBootProcessSvgService extends AbstractProcessSvgService {

    @Autowired
    public SpringBootProcessSvgService(@Autowired(required = false) DataIndexClient dataIndexClient,
            @Value("${kogito.svg.folder.path:#{null}}") Optional<String> svgResourcesPath,
            @Value("${kogito.svg.color.completed:" + DEFAULT_COMPLETED_COLOR + "}") String completedColor,
            @Value("${kogito.svg.color.completed.border:" + DEFAULT_COMPLETED_BORDER_COLOR + "}") String completedBorderColor,
            @Value("${kogito.svg.color.active.border:" + DEFAULT_ACTIVE_BORDER_COLOR + "}") String activeBorderColor) {
        super(dataIndexClient, svgResourcesPath, completedColor, completedBorderColor, activeBorderColor);
    }
}
