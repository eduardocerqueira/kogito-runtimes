package org.kie.kogito.process;

import java.util.Optional;
import java.util.stream.Stream;

public interface ProcessInstances<T> {

    default Optional<ProcessInstance<T>> findById(String id) {
        return findById(id, ProcessInstanceReadMode.MUTABLE);
    }

    Optional<ProcessInstance<T>> findById(String id, ProcessInstanceReadMode mode);

    Stream<ProcessInstance<T>> stream(ProcessInstanceReadMode mode);

    default Stream<ProcessInstance<T>> stream() {
        return stream(ProcessInstanceReadMode.READ_ONLY);
    }
}
