package org.kie.kogito.internal.process.event;

class KogitoObjectListenerAwareListener implements KogitoObjectListener {

    private final KogitoObjectListenerAware container;
    private final String prefix;

    public KogitoObjectListenerAwareListener(KogitoObjectListenerAware container, String prefix) {
        this.container = container;
        this.prefix = prefix;
    }

    private String concat(String property) {
        return prefix.isBlank() ? property : prefix + "." + property;
    }

    @Override
    public void beforeValueChanged(Object node, String property, Object oldValue, Object newValue) {
        container.listeners().forEach(l -> l.beforeValueChanged(container, concat(property), oldValue, newValue));
    }

    @Override
    public void afterValueChanged(Object node, String property, Object oldValue, Object newValue) {
        container.listeners().forEach(l -> l.afterValueChanged(container, concat(property), oldValue, newValue));
    }
}
