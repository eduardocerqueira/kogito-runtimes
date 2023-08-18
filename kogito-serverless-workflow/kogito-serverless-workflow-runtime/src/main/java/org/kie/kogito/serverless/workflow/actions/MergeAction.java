package org.kie.kogito.serverless.workflow.actions;

import org.jbpm.process.instance.impl.Action;
import org.kie.kogito.internal.process.runtime.KogitoProcessContext;
import org.kie.kogito.jackson.utils.MergeUtils;

public class MergeAction implements Action {

    protected String inputName;
    protected String outputName;

    public MergeAction(String inputName, String outputName) {
        this.inputName = inputName;
        this.outputName = outputName;
    }

    @Override
    public void execute(KogitoProcessContext context) throws Exception {
        MergeUtils.merge(ActionUtils.getJsonNode(context, inputName), ActionUtils.getJsonNode(context, outputName));
    }
}
