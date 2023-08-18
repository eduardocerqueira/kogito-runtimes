package org.kie.kogito.jobs.service.api;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface HasData<T> {

    @JsonIgnore
    T getData();
}
