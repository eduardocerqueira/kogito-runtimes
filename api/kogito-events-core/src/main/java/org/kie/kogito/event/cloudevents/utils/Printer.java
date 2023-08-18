package org.kie.kogito.event.cloudevents.utils;

import java.util.Objects;

import io.cloudevents.CloudEvent;

/**
 * Utility class to print CloudEvents in logs
 */
public final class Printer {
    private Printer() {

    }

    public static String beautify(CloudEvent event) {
        if (event == null) {
            return "";
        }
        return "\n☁ ️cloudevents.Event\n" +
                "Context Attributes,\n" +
                "\tspecversion: " + event.getSpecVersion() + "\n" +
                "\ttype: " + event.getType() + "\n" +
                "\tsource: " + event.getSource() + "\n" +
                "\tid: " + event.getId() + "\n" +
                "\tdatatype: " + event.getDataContentType() + "\n" +
                "Extensions," + beautifyExtensions(event) +
                "Data,\n\t" + Objects.toString(event.getData(), "");
    }

    private static String beautifyExtensions(CloudEvent event) {
        if (event.getExtensionNames().isEmpty()) {
            return "\n";
        }
        final StringBuilder sb = new StringBuilder();
        event.getExtensionNames().forEach(e -> sb.append("\t").append(e).append(": ").append(event.getExtension(e)).append("\n"));
        return sb.toString();
    }

}
