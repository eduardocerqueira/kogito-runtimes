package org.jbpm.ruleflow.core.factory;

import org.jbpm.process.core.Work;
import org.jbpm.process.core.impl.WorkImpl;
import org.jbpm.ruleflow.core.RuleFlowNodeContainerFactory;
import org.jbpm.workflow.core.NodeContainer;
import org.jbpm.workflow.core.node.HumanTaskNode;

public class HumanTaskNodeFactory<T extends RuleFlowNodeContainerFactory<T, ?>> extends AbstractWorkItemNodeFactory<HumanTaskNodeFactory<T>, T> {

    public static final String WORK_TASK_NAME = "TaskName";
    public static final String WORK_ACTOR_ID = "ActorId";
    public static final String WORK_GROUP_ID = "GroupId";
    public static final String WORK_PRIORITY = "Priority";
    public static final String WORK_COMMENT = "Comment";
    public static final String WORK_SKIPPABLE = "Skippable";
    public static final String WORK_CONTENT = "Content";

    public HumanTaskNodeFactory(T nodeContainerFactory, NodeContainer nodeContainer, long id) {
        super(nodeContainerFactory, nodeContainer, new HumanTaskNode(), id);
    }

    protected HumanTaskNode getHumanTaskNode() {
        return (HumanTaskNode) getNode();
    }

    public HumanTaskNodeFactory<T> taskName(String taskName) {
        Work work = getHumanTaskNode().getWork();
        if (work == null) {
            work = new WorkImpl();
            getHumanTaskNode().setWork(work);
        }
        work.setParameter(WORK_TASK_NAME, taskName);
        return this;
    }

    public HumanTaskNodeFactory<T> actorId(String actorId) {
        Work work = getHumanTaskNode().getWork();
        if (work == null) {
            work = new WorkImpl();
            getHumanTaskNode().setWork(work);
        }
        work.setParameter(WORK_ACTOR_ID, actorId);
        return this;
    }

    public HumanTaskNodeFactory<T> groupId(String groupId) {
        Work work = getHumanTaskNode().getWork();
        if (work == null) {
            work = new WorkImpl();
            getHumanTaskNode().setWork(work);
        }
        work.setParameter(WORK_GROUP_ID, groupId);
        return this;
    }

    public HumanTaskNodeFactory<T> priority(String priority) {
        Work work = getHumanTaskNode().getWork();
        if (work == null) {
            work = new WorkImpl();
            getHumanTaskNode().setWork(work);
        }
        work.setParameter(WORK_PRIORITY, priority);
        return this;
    }

    public HumanTaskNodeFactory<T> comment(String comment) {
        Work work = getHumanTaskNode().getWork();
        if (work == null) {
            work = new WorkImpl();
            getHumanTaskNode().setWork(work);
        }
        work.setParameter(WORK_COMMENT, comment);
        return this;
    }

    public HumanTaskNodeFactory<T> skippable(boolean skippable) {
        Work work = getHumanTaskNode().getWork();
        if (work == null) {
            work = new WorkImpl();
            getHumanTaskNode().setWork(work);
        }
        work.setParameter(WORK_SKIPPABLE, Boolean.toString(skippable));
        return this;
    }

    public HumanTaskNodeFactory<T> content(String content) {
        Work work = getHumanTaskNode().getWork();
        if (work == null) {
            work = new WorkImpl();
            getHumanTaskNode().setWork(work);
        }
        work.setParameter(WORK_CONTENT, content);
        return this;
    }

    public HumanTaskNodeFactory<T> swimlane(String swimlane) {
        getHumanTaskNode().setSwimlane(swimlane);
        return this;
    }
}
