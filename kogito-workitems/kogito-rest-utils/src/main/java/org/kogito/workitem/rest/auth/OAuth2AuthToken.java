package org.kogito.workitem.rest.auth;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.oauth2.OAuth2Options;
import io.vertx.mutiny.core.Vertx;
import io.vertx.mutiny.ext.auth.User;
import io.vertx.mutiny.ext.auth.oauth2.OAuth2Auth;

public abstract class OAuth2AuthToken<T> implements TokenRetriever {

    private static final Map<Object, User> usersCache = new ConcurrentHashMap<>();

    private final String tokenUrl;
    private final String refreshUrl;

    protected OAuth2AuthToken(String tokenUrl, String refreshUrl) {
        this.tokenUrl = tokenUrl;
        this.refreshUrl = refreshUrl;
    }

    @Override
    public String getToken(Map<String, Object> parameters) {
        User user = usersCache.compute(getCacheKey(parameters), this::getOrRefreshUser);
        return user.principal().getString("access_token");
    }

    private User getOrRefreshUser(Object c, User user) {
        T cacheKey = (T) c;
        if (user == null) {
            return createOAuth2(tokenUrl, cacheKey).authenticateAndAwait(getJsonObject(cacheKey));
        } else if (user.expired()) {
            return createOAuth2(refreshUrl != null ? refreshUrl : tokenUrl, cacheKey).refreshAndAwait(user);
        } else {
            return user;
        }
    }

    private OAuth2Auth createOAuth2(String tokenPath, T cacheKey) {
        return OAuth2Auth.create(Vertx.vertx(), fillOptions(new OAuth2Options().setTokenPath(tokenPath), cacheKey));
    }

    protected abstract OAuth2Options fillOptions(OAuth2Options setTokenPath, T cacheKey);

    protected abstract JsonObject getJsonObject(T cacheKey);

    protected abstract T getCacheKey(Map<String, Object> parameters);

}
