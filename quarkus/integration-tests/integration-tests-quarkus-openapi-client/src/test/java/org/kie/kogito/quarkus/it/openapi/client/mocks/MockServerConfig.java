package org.kie.kogito.quarkus.it.openapi.client.mocks;

public class MockServerConfig {

    private int port;
    private String response;
    private String path;
    private String beanName;

    public MockServerConfig() {
    }

    public MockServerConfig(int port, String response, String path, String beanName) {
        this.setBeanName(beanName);
        this.setPath(path);
        this.setResponse(response);
        this.setPort(port);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
