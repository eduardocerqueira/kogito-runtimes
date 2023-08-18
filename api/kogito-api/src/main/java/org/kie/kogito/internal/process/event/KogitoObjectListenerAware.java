package org.kie.kogito.internal.process.event;

import java.util.Collection;
import java.util.Objects;

public interface KogitoObjectListenerAware {

    default void fireEvent(String propertyName, Object oldValue, Object newValue, Runnable updater) {
        if (!Objects.equals(oldValue, newValue)) {
            if (newValue instanceof KogitoObjectListenerAware && !Objects.equals(this, newValue)) {
                ((KogitoObjectListenerAware) newValue).addKogitoObjectListener(new KogitoObjectListenerAwareListener(this, propertyName));
            }
            listeners().forEach(l -> l.beforeValueChanged(this, propertyName, oldValue, newValue));
            updater.run();
            listeners().forEach(l -> l.afterValueChanged(this, propertyName, oldValue, newValue));
        }
    }

    void addKogitoObjectListener(KogitoObjectListener listener);

    Collection<KogitoObjectListener> listeners();

    boolean isEmpty();
}
