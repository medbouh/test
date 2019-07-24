package com.a2a.api.microservices.security;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collection;
import java.util.Objects;


public class AuthenticatedUser {

    private final String token;

    private final String login;

    private final Collection<String> authorities;

    public AuthenticatedUser(String token, String login, Collection<String> authorities) {
        this.token = toBearer(token);
        this.login = login;
        this.authorities = authorities;
    }

    public String getToken() {
        return token;
    }

    public String getLogin() {
        return login;
    }

    public Collection<String> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AuthenticatedUser)) {
            return false;
        }
        AuthenticatedUser that = (AuthenticatedUser) o;
        return Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("login", login)
                .append("authorities", authorities)
                .toString();
    }

    private String toBearer(String token) {
        return SecurityConstants.BEARER_PREFIX + token;
    }

}
