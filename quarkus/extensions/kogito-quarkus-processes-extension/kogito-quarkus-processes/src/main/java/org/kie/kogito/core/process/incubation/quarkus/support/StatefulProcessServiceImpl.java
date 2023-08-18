package org.kie.kogito.core.process.incubation.quarkus.support;

import java.util.Map;
import java.util.Optional;

import org.kie.kogito.MappableToModel;
import org.kie.kogito.Model;
import org.kie.kogito.incubation.common.*;
import org.kie.kogito.incubation.common.objectmapper.InternalObjectMapper;
import org.kie.kogito.incubation.processes.LocalProcessId;
import org.kie.kogito.incubation.processes.ProcessIdParser;
import org.kie.kogito.incubation.processes.ProcessInstanceId;
import org.kie.kogito.incubation.processes.SignalId;
import org.kie.kogito.incubation.processes.services.StatefulProcessService;
import org.kie.kogito.incubation.processes.services.contexts.ProcessMetaDataContext;
import org.kie.kogito.process.Process;
import org.kie.kogito.process.ProcessInstance;
import org.kie.kogito.process.ProcessService;
import org.kie.kogito.process.Processes;

class StatefulProcessServiceImpl implements StatefulProcessService {

    private final ProcessService svc;
    private final Processes processes;

    StatefulProcessServiceImpl(ProcessService svc, Processes processes) {
        this.svc = svc;
        this.processes = processes;
    }

    @Override
    public ExtendedDataContext create(LocalId localId, DataContext inputContext) {
        LocalProcessId processId = ProcessIdParser.select(localId, LocalProcessId.class);
        ExtendedDataContext extendedDataContext = inputContext.as(ExtendedDataContext.class);
        ProcessMetaDataContext inputMeta = inputContext.as(ProcessMetaDataContext.class);

        Process<Model> process = (Process<Model>) (Object) parseProcess(processId);

        String businessKey = inputMeta.businessKey();
        Model model = toModel(extendedDataContext.data(), process);
        String startFromNodeId = inputMeta.startFrom();

        ProcessInstance<Model> instance =
                svc.createProcessInstance(
                        process, businessKey, model, startFromNodeId);
        MapDataContext data = MapDataContext.from(instance.variables().toMap());

        ProcessMetaDataContext meta = ProcessMetaDataContext.of(processId.instances().get(instance.id()));

        return ExtendedDataContext.of(meta, data);
    }

    @Override
    public ExtendedDataContext signal(LocalId localId, DataContext dataContext) {
        SignalId signalId = ProcessIdParser.select(localId, SignalId.class);
        Process<MappableToModel<Model>> process = parseProcess(signalId.processInstanceId().processId());
        Model m = svc.signalProcessInstance(
                process,
                signalId.processInstanceId().processInstanceId(),
                dataContext,
                signalId.signalId()).orElseThrow();
        MapDataContext data = MapDataContext.from(m.toMap());
        ProcessMetaDataContext meta = ProcessMetaDataContext.of(signalId.processInstanceId());

        return ExtendedDataContext.of(meta, data);
    }

    @Override
    public ExtendedDataContext update(LocalId localId, DataContext dataContext) {
        ProcessInstanceId instanceId = ProcessIdParser.select(localId, ProcessInstanceId.class);
        Process<MappableToModel<Model>> process = parseProcess(instanceId.processId());
        MappableToModel<Model> model = (MappableToModel<Model>) toModel(dataContext, process);
        Optional<Model> optionalModel = svc.update(process, instanceId.processInstanceId(), model);
        Model m = optionalModel.orElseThrow();
        Map<String, Object> map = m.toMap();

        return ExtendedDataContext.ofData(MapDataContext.from(map));
    }

    @Override
    public ExtendedDataContext abort(LocalId localId) {
        ProcessInstanceId processInstanceId = ProcessIdParser.select(localId, ProcessInstanceId.class);
        Process<MappableToModel<Model>> process = parseProcess(processInstanceId.processId());
        Model m = svc.delete(process, processInstanceId.processInstanceId()).orElseThrow();
        ProcessMetaDataContext meta = ProcessMetaDataContext.of(localId);
        MapDataContext data = MapDataContext.of(m.toMap());
        return ExtendedDataContext.of(meta, data);
    }

    @Override
    public ExtendedDataContext get(LocalId localId) {
        ProcessInstanceId processInstanceId = ProcessIdParser.select(localId, ProcessInstanceId.class);
        Process<MappableToModel<Model>> process = parseProcess(processInstanceId.processId());
        Model m = svc.findById(process, processInstanceId.processInstanceId()).orElseThrow();
        ProcessMetaDataContext meta = ProcessMetaDataContext.of(localId);
        MapDataContext data = MapDataContext.of(m.toMap());
        return ExtendedDataContext.of(meta, data);
    }

    private Process<MappableToModel<Model>> parseProcess(LocalProcessId processId) {
        return (Process<MappableToModel<Model>>) processes.processById(processId.processId());
    }

    private Model toModel(DataContext inputContext, Process<? extends Model> process) {
        MapDataContext mdc = inputContext.as(MapDataContext.class);
        Class<? extends Model> modelType = process.createModel().getClass();
        return InternalObjectMapper.objectMapper().convertValue(mdc, modelType);
    }
}
