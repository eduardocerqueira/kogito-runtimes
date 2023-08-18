package org.kie.kogito.codegen.process.persistence.proto;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Proto {

    private String syntax = "proto2";
    private String packageName;
    private String[] headers;

    private List<ProtoMessage> messages = new ArrayList<>();
    private List<ProtoEnum> enums = new ArrayList<>();

    public Proto(String packageName, String... headers) {
        this.packageName = packageName;
        this.headers = headers;
    }

    public String getSyntax() {
        return syntax;
    }

    public void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<ProtoMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ProtoMessage> messages) {
        this.messages = messages;
    }

    public void addMessage(ProtoMessage message) {
        if (!messages.contains(message)) {
            this.messages.add(message);
            this.messages.sort(Comparator.comparing(ProtoMessage::getName));
        }
    }

    public List<ProtoEnum> getEnums() {
        return enums;
    }

    public void addEnum(ProtoEnum protoEnum) {
        if (!enums.contains(protoEnum)) {
            this.enums.add(protoEnum);
            this.enums.sort(Comparator.comparing(ProtoEnum::getName));
        }
    }

    public String serialize() {
        StringBuilder headersAsString = new StringBuilder();

        for (String header : headers) {
            headersAsString.append(header).append("\n");
        }
        StringBuilder messagesAsString = new StringBuilder();

        messages.forEach(m -> messagesAsString.append(m.serialize()));
        enums.forEach(e -> messagesAsString.append(e.serialize()));

        StringBuilder builder = new StringBuilder();
        builder.append("syntax = \"").append(syntax).append("\"; \n");
        if (packageName != null) {
            builder.append("package ").append(packageName).append("; \n");
        }
        builder.append(headersAsString).append("\n").append(messagesAsString);

        return builder.toString();
    }

    @Override
    public String toString() {
        return serialize();
    }
}
