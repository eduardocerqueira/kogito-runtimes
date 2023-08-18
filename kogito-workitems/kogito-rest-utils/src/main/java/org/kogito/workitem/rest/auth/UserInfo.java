package org.kogito.workitem.rest.auth;

import java.util.Objects;

class UserInfo {

    private final String user;
    private final String password;

    public UserInfo(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, user);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof UserInfo))
            return false;
        UserInfo other = (UserInfo) obj;
        return Objects.equals(password, other.password) && Objects.equals(user, other.user);
    }

}
