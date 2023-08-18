package org.jbpm.workflow.core.node;

import java.util.function.Predicate;

import org.jbpm.workflow.core.Node;
import org.kie.api.definition.process.Connection;
import org.kie.api.runtime.process.ProcessContext;

import static org.jbpm.ruleflow.core.Metadata.UNIQUE_ID;

/**
 * Default implementation of a milestone node.
 */
public class MilestoneNode extends StateBasedNode implements Constrainable {

    private static final long serialVersionUID = 510L;

    /**
     * String representation of the conditionPredicate. Not used at runtime
     */
    private String condition;
    private Predicate<ProcessContext> conditionPredicate;

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(Predicate<ProcessContext> conditionPredicate) {
        this.conditionPredicate = conditionPredicate;
    }

    public boolean canComplete(ProcessContext context) {
        return conditionPredicate == null || conditionPredicate.test(context);
    }

    @Override
    public void validateAddIncomingConnection(final String type, final Connection connection) {
        super.validateAddIncomingConnection(type, connection);
        if (!Node.CONNECTION_DEFAULT_TYPE.equals(type)) {
            throwValidationException(connection, "only accepts default incoming connection type!");
        }
        if (getFrom() != null && !Boolean.parseBoolean(System.getProperty("jbpm.enable.multi.con"))) {
            throwValidationException(connection, "cannot have more than one incoming connection!");
        }
    }

    @Override
    public void validateAddOutgoingConnection(final String type, final Connection connection) {
        super.validateAddOutgoingConnection(type, connection);
        if (!Node.CONNECTION_DEFAULT_TYPE.equals(type)) {
            throwValidationException(connection, "only accepts default outgoing connection type!");
        }
        if (getTo() != null && !Boolean.parseBoolean(System.getProperty("jbpm.enable.multi.con"))) {
            throwValidationException(connection, "cannot have more than one outgoing connection!");
        }
    }

    private static void throwValidationException(Connection connection, String msg) {
        throw new IllegalArgumentException("This type of node ["
                + connection.getFrom().getMetaData().get(UNIQUE_ID) + ", "
                + connection.getFrom().getName() + "] "
                + msg);
    }

}
