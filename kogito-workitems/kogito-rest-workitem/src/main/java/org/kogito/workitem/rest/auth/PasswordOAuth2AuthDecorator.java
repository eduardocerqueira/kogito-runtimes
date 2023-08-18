package org.kogito.workitem.rest.auth;

public class PasswordOAuth2AuthDecorator extends OAuth2AuthDecorator {

    public PasswordOAuth2AuthDecorator(String tokenUrl, String refreshUrl) {
        super(new PasswordOAuth2AuthToken(tokenUrl, refreshUrl));
    }
}
