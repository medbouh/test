package com.a2a.api.microservices.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

@Component
public class CostumZuulFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return "ceci est la methode filterType";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        return "ceci est la methode run";
    }
}
