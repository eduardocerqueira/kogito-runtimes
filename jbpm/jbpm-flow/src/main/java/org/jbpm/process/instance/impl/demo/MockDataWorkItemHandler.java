package org.jbpm.process.instance.impl.demo;

import java.util.Map;
import java.util.function.Function;

import org.kie.kogito.internal.process.runtime.KogitoWorkItem;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemHandler;
import org.kie.kogito.internal.process.runtime.KogitoWorkItemManager;

/**
 * Simple work item handler that allows to provide output data or supplier
 * that can provide data based on supplied function. It can reason on top
 * of provided input data.
 *
 */
public class MockDataWorkItemHandler implements KogitoWorkItemHandler {
    private Function<Map<String, Object>, Map<String, Object>> outputDataSupplier;

    /**
     * Create handler that will always complete work items with exact same map of data.
     *
     * @param outputData data to be used when completing work items
     */
    public MockDataWorkItemHandler(Map<String, Object> outputData) {
        this.outputDataSupplier = inputData -> outputData;
    }

    /**
     * Create handler with custom function that will supply output data. It can use
     * input data to change the output data returned if needed.
     *
     * @param outputDataSupplier function responsible to provide output data
     */
    public MockDataWorkItemHandler(Function<Map<String, Object>, Map<String, Object>> outputDataSupplier) {
        this.outputDataSupplier = outputDataSupplier;
    }

    @Override
    public void executeWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
        manager.completeWorkItem(workItem.getStringId(), outputDataSupplier.apply(workItem.getParameters()));
    }

    @Override
    public void abortWorkItem(KogitoWorkItem workItem, KogitoWorkItemManager manager) {
    }

}
