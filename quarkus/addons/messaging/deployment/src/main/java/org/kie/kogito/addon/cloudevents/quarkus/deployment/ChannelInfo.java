package org.kie.kogito.addon.cloudevents.quarkus.deployment;

import java.util.Collection;
import java.util.Optional;

public class ChannelInfo {

    private final String channelName;
    private final String className;
    private final Collection<String> triggers;

    private final boolean isInput;
    private final boolean isDefault;

    private final Optional<String> marshaller;
    private final Optional<OnOverflowInfo> onOverflow;

    protected ChannelInfo(String channelName, Collection<String> triggers, String className, boolean isInput, boolean isDefault, Optional<String> marshaller, Optional<OnOverflowInfo> onOverflow) {
        this.className = className;
        this.channelName = channelName;
        this.isInput = isInput;
        this.isDefault = isDefault;
        this.triggers = triggers;
        this.marshaller = marshaller;
        this.onOverflow = onOverflow;
    }

    public Collection<String> getTriggers() {
        return triggers;
    }

    public String getChannelName() {
        return channelName;
    }

    public String getClassName() {
        return className;
    }

    public boolean isInput() {
        return isInput;
    }

    @Override
    public int hashCode() {
        return channelName.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof ChannelInfo))
            return false;
        return channelName.equals(((ChannelInfo) obj).getChannelName());
    }

    public boolean isDefault() {
        return isDefault;
    }

    public boolean isInputDefault() {
        return isInput && isDefault;
    }

    public boolean isOutputDefault() {
        return !isInput && isDefault;
    }

    public Optional<String> getMarshaller() {
        return marshaller;
    }

    public Optional<OnOverflowInfo> getOnOverflow() {
        return onOverflow;
    }

    @Override
    public String toString() {
        return "ChannelInfo [channelName=" + channelName + ", className=" + className + ", triggers=" + triggers
                + ", isInput=" + isInput + ", isDefault=" + isDefault + ", marshaller=" + marshaller + "]";
    }
}
