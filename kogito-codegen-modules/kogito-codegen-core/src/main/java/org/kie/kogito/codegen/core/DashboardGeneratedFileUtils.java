package org.kie.kogito.codegen.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.drools.codegen.common.GeneratedFile;
import org.drools.codegen.common.GeneratedFileType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DashboardGeneratedFileUtils {
    public static final GeneratedFileType DASHBOARD_TYPE = GeneratedFileType.of("DASHBOARD", GeneratedFileType.Category.STATIC_HTTP_RESOURCE);
    private static final String STATIC_RESOURCE_PATH = "monitoring/dashboards/";
    public static final String OPERATIONAL_DASHBOARD_PREFIX = "operational-dashboard-";
    public static final String DOMAIN_DASHBOARD_PREFIX = "domain-dashboard-";
    private static final String LIST_FILENAME = "list.json";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String DASHBOARD_NAME_REGEX = "[ ,\\*\\+\\#&%\\$\\^]";

    private DashboardGeneratedFileUtils() {
        // utility class
    }

    public static List<GeneratedFile> operational(String operationalDashboard, String name) {
        List<GeneratedFile> generatedFiles = new ArrayList<>();
        generatedFiles.add(new GeneratedFile(DASHBOARD_TYPE,
                STATIC_RESOURCE_PATH + OPERATIONAL_DASHBOARD_PREFIX + name.replaceAll(DASHBOARD_NAME_REGEX, ""),
                operationalDashboard));
        return generatedFiles;
    }

    public static List<GeneratedFile> domain(String domainDashboard, String name) {
        List<GeneratedFile> generatedFiles = new ArrayList<>();
        generatedFiles.add(new GeneratedFile(DASHBOARD_TYPE,
                STATIC_RESOURCE_PATH + DOMAIN_DASHBOARD_PREFIX + name.replaceAll(DASHBOARD_NAME_REGEX, ""),
                domainDashboard));
        return generatedFiles;
    }

    public static Optional<GeneratedFile> list(Collection<GeneratedFile> generatedFiles) {
        List<String> fileNames = generatedFiles.stream()
                .filter(x -> x.type().equals(DASHBOARD_TYPE))
                .map(x -> x.relativePath().substring(x.relativePath().lastIndexOf("/") + 1))
                .collect(Collectors.toList());

        if (!fileNames.isEmpty()) {
            try {
                return Optional.of(new GeneratedFile(DASHBOARD_TYPE,
                        STATIC_RESOURCE_PATH + LIST_FILENAME,
                        MAPPER.writeValueAsString(fileNames)));
            } catch (JsonProcessingException e) {
                throw new IllegalStateException("Error during json serialization", e);
            }
        }
        return Optional.empty();
    }
}
