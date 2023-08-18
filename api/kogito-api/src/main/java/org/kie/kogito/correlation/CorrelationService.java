package org.kie.kogito.correlation;

import java.util.Optional;

public interface CorrelationService {

    CorrelationInstance create(Correlation correlation, String correlatedId);

    Optional<CorrelationInstance> find(Correlation correlation);

    Optional<CorrelationInstance> findByCorrelatedId(String correlatedId);

    void delete(Correlation correlation);

}
