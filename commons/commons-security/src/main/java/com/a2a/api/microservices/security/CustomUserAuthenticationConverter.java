package com.a2a.api.microservices.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class CustomUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

    @Override
    public Authentication extractAuthentication(Map<String, ?> map) {
        Authentication authentication = super.extractAuthentication(map);
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        //AuthenticatedUser user = new AuthenticatedUser(getToken(map), getUsername(map), getRacines(map), mapAuthorities(authorities));
        AuthenticatedUser user = new AuthenticatedUser(getToken(map), getUsername(map), mapAuthorities(authorities));
        return new UsernamePasswordAuthenticationToken(user, authentication.getCredentials(), authorities);
    }

    private String getToken(Map<String, ?> map) {
        return (String) map.get(SecurityConstants.TOKEN_KEY);
    }

    private String getUsername(Map<String, ?> map) {
        return (String) map.get(USERNAME);
    }

    private List<String> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }

}
