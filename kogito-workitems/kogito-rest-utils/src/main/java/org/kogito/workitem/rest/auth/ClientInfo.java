package org.kogito.workitem.rest.auth;

import java.util.Objects;

class ClientInfo {

    private final String clientId;
    private final String clientSecret;

    public ClientInfo(String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientId, clientSecret);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof ClientInfo))
            return false;
        ClientInfo other = (ClientInfo) obj;
        return Objects.equals(clientId, other.clientId) && Objects.equals(clientSecret, other.clientSecret);
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }
}
