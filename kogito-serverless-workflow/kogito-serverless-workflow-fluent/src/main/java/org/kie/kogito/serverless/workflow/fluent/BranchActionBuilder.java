package org.kie.kogito.serverless.workflow.fluent;

import java.util.ArrayList;
import java.util.List;

import io.serverlessworkflow.api.actions.Action;
import io.serverlessworkflow.api.branches.Branch;

public class BranchActionBuilder {

    private ParallelStateBuilder parent;
    private Branch branch;
    private List<Action> actions = new ArrayList<>();

    public BranchActionBuilder(ParallelStateBuilder parent, Branch branch) {
        this.parent = parent;
        this.branch = branch.withActions(actions);
    }

    public BranchActionBuilder name(String name) {
        branch.withName(name);
        return this;
    }

    public BranchActionBuilder action(ActionBuilder action) {
        action.getFunction().ifPresent(parent.getFunctions()::add);
        action.getEvent().ifPresent(parent.getEvents()::add);
        actions.add(action.build());
        return this;
    }

    public ParallelStateBuilder endBranch() {
        return parent;
    }
}
