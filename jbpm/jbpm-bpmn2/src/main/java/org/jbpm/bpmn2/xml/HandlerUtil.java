package org.jbpm.bpmn2.xml;

import java.util.HashMap;
import java.util.Map;

import org.jbpm.bpmn2.core.Collaboration;
import org.jbpm.bpmn2.core.CorrelationProperty;
import org.jbpm.bpmn2.core.CorrelationSubscription;
import org.jbpm.bpmn2.core.ItemDefinition;
import org.jbpm.bpmn2.core.Message;
import org.jbpm.compiler.xml.Parser;
import org.jbpm.compiler.xml.ProcessBuildData;
import org.jbpm.ruleflow.core.RuleFlowProcess;

@SuppressWarnings("unchecked")
public final class HandlerUtil {

    private HandlerUtil() {

    }

    public static Map<String, Message> messages(Parser parser) {
        Map<String, Message> messages = (Map<String, Message>) ((ProcessBuildData) parser.getData()).getMetaData("Messages");
        if (messages == null) {
            messages = new HashMap<>();
            ((ProcessBuildData) parser.getData()).setMetaData("Messages", messages);
        }
        return messages;
    }

    public static Map<String, ItemDefinition> definitions(Parser parser) {
        Map<String, ItemDefinition> definitions = (Map<String, ItemDefinition>) ((ProcessBuildData) parser.getData()).getMetaData("ItemDefinitions");
        if (definitions == null) {
            definitions = new HashMap<>();
            ((ProcessBuildData) parser.getData()).setMetaData("ItemDefinitions", definitions);
        }
        return definitions;
    }

    public static Map<String, CorrelationProperty> correlationProperties(Parser parser) {
        Map<String, CorrelationProperty> properties = (Map<String, CorrelationProperty>) ((ProcessBuildData) parser.getData()).getMetaData("CorrelationProperties");
        if (properties == null) {
            properties = new HashMap<>();
            ((ProcessBuildData) parser.getData()).setMetaData("CorrelationProperties", properties);
        }
        return properties;
    }

    public static Map<String, Collaboration> collaborations(Parser parser) {
        Map<String, Collaboration> collaborations = (Map<String, Collaboration>) ((ProcessBuildData) parser.getData()).getMetaData("Collaborations");
        if (collaborations == null) {
            collaborations = new HashMap<>();
            ((ProcessBuildData) parser.getData()).setMetaData("Collaborations", collaborations);
        }
        return collaborations;
    }

    public static Map<String, CorrelationSubscription> correlationSubscription(RuleFlowProcess process) {
        Map<String, CorrelationSubscription> correlationSubscription = (Map<String, CorrelationSubscription>) process.getMetaData("CorrelationSubscriptions");
        if (correlationSubscription == null) {
            correlationSubscription = new HashMap<>();
            process.setMetaData("CorrelationSubscriptions", correlationSubscription);
        }
        return correlationSubscription;
    }
}
