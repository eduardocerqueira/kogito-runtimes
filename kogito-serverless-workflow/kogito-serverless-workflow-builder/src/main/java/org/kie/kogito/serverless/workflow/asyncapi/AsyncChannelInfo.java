package org.kie.kogito.serverless.workflow.asyncapi;

public class AsyncChannelInfo {
    private final String name;
    private final boolean isPublish;

    public AsyncChannelInfo(String name, boolean isPublish) {
        this.name = name;
        this.isPublish = isPublish;
    }

    public String getName() {
        return name;
    }

    public boolean isPublish() {
        return isPublish;
    }

    @Override
    public String toString() {
        return "AsyncChannelInfo [name=" + name + ", isPublish=" + isPublish + "]";
    }
}
