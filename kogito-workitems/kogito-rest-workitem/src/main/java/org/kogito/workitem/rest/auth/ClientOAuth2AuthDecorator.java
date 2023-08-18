package org.kogito.workitem.rest.auth;

public class ClientOAuth2AuthDecorator extends OAuth2AuthDecorator {
    public static final String CLIENT_ID = "clientId";
    public static final String CLIENT_SECRET = "clientSecret";

    public ClientOAuth2AuthDecorator(String tokenUrl, String refreshUrl) {
        super(new ClientOAuth2AuthToken(tokenUrl, refreshUrl));
    }
}