package org.kie.kogito.serverless.workflow.fluent;

import java.util.ArrayList;
import java.util.List;

import io.serverlessworkflow.api.branches.Branch;
import io.serverlessworkflow.api.states.DefaultState.Type;
import io.serverlessworkflow.api.states.ParallelState;
import io.serverlessworkflow.api.states.ParallelState.CompletionType;

public class ParallelStateBuilder extends StateBuilder<ParallelStateBuilder, ParallelState> {

    protected ParallelStateBuilder() {
        super(new ParallelState().withType(Type.PARALLEL));
        state.withBranches(branches);
    }

    public ParallelStateBuilder atLeast(int numCompleted) {
        state.withCompletionType(CompletionType.AT_LEAST).withNumCompleted(Integer.toString(numCompleted));
        return this;
    }

    private List<Branch> branches = new ArrayList<>();

    public BranchActionBuilder newBranch() {
        Branch branch = new Branch();
        branches.add(branch);
        return new BranchActionBuilder(this, branch);
    }
}
