package com.a2a.api.microservices.security;

import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;


public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {

    @Override
    protected Map<String, Object> decode(String token) {
        Map<String, Object> decode = super.decode(token);
        decode.put(SecurityConstants.TOKEN_KEY, token);
        return decode;
    }
}
