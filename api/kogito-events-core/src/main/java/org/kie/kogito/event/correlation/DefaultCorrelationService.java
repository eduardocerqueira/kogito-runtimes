package org.kie.kogito.event.correlation;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.kie.kogito.correlation.Correlation;
import org.kie.kogito.correlation.CorrelationEncoder;
import org.kie.kogito.correlation.CorrelationInstance;
import org.kie.kogito.correlation.CorrelationService;

public class DefaultCorrelationService implements CorrelationService {

    private static final Map<String, CorrelationInstance> correlationRepository = new ConcurrentHashMap<>();
    private static final Map<String, CorrelationInstance> correlatedRepository = new ConcurrentHashMap<>();

    private CorrelationEncoder correlationEncoder = new MD5CorrelationEncoder();

    @Override
    public CorrelationInstance create(Correlation correlation, String correlatedId) {
        String encodedCorrelationId = correlationEncoder.encode(correlation);
        CorrelationInstance correlationInstance = new CorrelationInstance(encodedCorrelationId, correlatedId, correlation);
        correlationRepository.put(encodedCorrelationId, correlationInstance);
        correlatedRepository.put(correlatedId, correlationInstance);
        return correlationInstance;
    }

    @Override
    public Optional<CorrelationInstance> find(Correlation correlation) {
        return Optional.ofNullable(correlationRepository.get(correlationEncoder.encode(correlation)));
    }

    @Override
    public Optional<CorrelationInstance> findByCorrelatedId(String correlatedId) {
        return Optional.ofNullable(correlatedRepository.get(correlatedId));
    }

    @Override
    public void delete(Correlation correlation) {
        CorrelationInstance removed = correlationRepository.remove(correlationEncoder.encode(correlation));
        correlatedRepository.remove(removed.getCorrelatedId());
    }

    public final void clear() {
        correlationRepository.clear();
        correlatedRepository.clear();
    }
}
