package org.kie.kogito.svg.service;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.kie.kogito.svg.AbstractProcessSvgService;
import org.kie.kogito.svg.dataindex.DataIndexClient;

@ApplicationScoped
public class QuarkusProcessSvgService extends AbstractProcessSvgService {

    @Inject
    public QuarkusProcessSvgService(DataIndexClient dataIndexClient,
            @ConfigProperty(name = "kogito.svg.folder.path") Optional<String> svgResourcesPath,
            @ConfigProperty(name = "kogito.svg.color.completed", defaultValue = DEFAULT_COMPLETED_COLOR) String completedColor,
            @ConfigProperty(name = "kogito.svg.color.completed.border", defaultValue = DEFAULT_COMPLETED_BORDER_COLOR) String completedBorderColor,
            @ConfigProperty(name = "kogito.svg.color.active.border", defaultValue = DEFAULT_ACTIVE_BORDER_COLOR) String activeBorderColor) {
        super(dataIndexClient, svgResourcesPath, completedColor, completedBorderColor, activeBorderColor);
    }
}
