package org.kie.kogito.quarkus.serverless.workflow.config;

import io.quarkus.runtime.annotations.ConfigGroup;
import io.quarkus.runtime.annotations.ConfigItem;

@ConfigGroup
public class KogitoServerlessWorkflowBuildTimeConfig {

    /**
     * Strategy for generating the configuration key of open API specifications.<br>
     * Possible values are:
     * <UL>
     * <LI>file_name. Uses the last element of the spec uri</LI>
     * <LI>full_uri. Uses the full path of the uri</LI>
     * <LI>spec_title. Uses the spec title</LI>
     * <LI>function_name. Uses the function name</LI>
     * </UL>
     */
    @ConfigItem(name = "operationIdStrategy", defaultValue = "file_name")
    public String operationIdStrategy;

    /**
     * Variable name for foreach loop
     */
    @ConfigItem(name = "states.foreach.outputVarName", defaultValue = "_swf_eval_temp")
    public String forEachOutputVarName;

}
