package org.kie.kogito.core.process.incubation.quarkus.support;

import java.util.Map;

import org.kie.kogito.Model;
import org.kie.kogito.incubation.common.DataContext;
import org.kie.kogito.incubation.common.Id;
import org.kie.kogito.incubation.common.LocalUri;
import org.kie.kogito.incubation.common.MapDataContext;
import org.kie.kogito.incubation.common.objectmapper.InternalObjectMapper;
import org.kie.kogito.incubation.processes.LocalProcessId;
import org.kie.kogito.incubation.processes.services.StraightThroughProcessService;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.Processes;

class StraightThroughProcessServiceImpl implements StraightThroughProcessService {

    private final Processes processes;

    StraightThroughProcessServiceImpl(Processes processes) {
        this.processes = processes;
    }

    @Override
    public DataContext evaluate(Id processId, DataContext inputContext) {
        LocalUri processPath = processId.toLocalId().asLocalUri();
        if (processPath.startsWith(LocalProcessId.PREFIX)) {
            LocalProcessId pid = (LocalProcessId) processId;
            Process<? extends Model> process = processes.processById(pid.processId());
            MapDataContext mdc = inputContext.as(MapDataContext.class);
            Class<? extends Model> modelType = process.createModel().getClass();
            Model model = InternalObjectMapper.objectMapper().convertValue(mdc, modelType);
            ProcessInstance<? extends Model> instance = process.createInstance(model);
            instance.start();
            Map<String, Object> map = instance.variables().toMap();
            return MapDataContext.of(map);
        } else {
            throw new IllegalArgumentException("Not a valid processId " + processPath);
        }
    }

}
