package org.kie.kogito.test.resources;

import java.util.Optional;

/**
 * Condition holder to keep the constraints so a condition resource needs to be started or not.
 */
public class ConditionHolder {

    protected static final String TEST_CATEGORY_PROPERTY = "enable.resource.%s";

    private final String resourceName;
    private boolean enabled = true;

    public ConditionHolder(String resourceName) {
        this.resourceName = resourceName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void enableConditional() {
        enableIfSystemPropertyIs(String.format(TEST_CATEGORY_PROPERTY, resourceName), Boolean.TRUE.toString());
    }

    private void enableIfSystemPropertyIs(String name, String value) {
        this.enabled = Optional.ofNullable(System.getProperty(name)).map(property -> property.equalsIgnoreCase(value)).orElse(false);
    }
}
