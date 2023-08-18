package org.kogito.workitem.rest.auth;

import java.util.Map;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.oauth2.OAuth2FlowType;
import io.vertx.ext.auth.oauth2.OAuth2Options;

public class PasswordOAuth2AuthToken extends OAuth2AuthToken<UserInfo> {

    public static final String USER = "Username";
    public static final String PASSWORD = "Password";

    public PasswordOAuth2AuthToken(String tokenUrl, String refreshUrl) {
        super(tokenUrl, refreshUrl);
    }

    @Override
    protected OAuth2Options fillOptions(OAuth2Options options, UserInfo cacheKey) {
        return options.setFlow(OAuth2FlowType.PASSWORD);
    }

    @Override
    protected JsonObject getJsonObject(UserInfo cacheKey) {
        return new JsonObject().put("username", cacheKey.getUser()).put("password", cacheKey.getPassword());
    }

    @Override
    protected UserInfo getCacheKey(Map<String, Object> parameters) {
        return new UserInfo((String) parameters.get(USER), (String) parameters.get(PASSWORD));
    }
}
