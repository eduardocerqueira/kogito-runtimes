package org.kie.kogito.timer;

public interface Calendars {

    Calendar get(String identifier);

    void set(String identifier, Calendar value);
}
