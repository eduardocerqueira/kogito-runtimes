package org.kie.kogito.jobs.service.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface HasPayload<T extends PayloadData> {

    @JsonIgnore
    T getPayload();
}
