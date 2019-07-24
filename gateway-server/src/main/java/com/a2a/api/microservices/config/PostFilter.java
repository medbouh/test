package com.a2a.api.microservices.config;

import com.google.common.io.CharStreams;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;


public class PostFilter extends ZuulFilter {

    Logger logger = LoggerFactory.getLogger(PostFilter.class);

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        int responseStatus = ctx.getResponseStatusCode() ;
         String responseData="";
        try (final InputStream responseDataStream = ctx.getResponseDataStream()) {
            //ctx.getResponse().getHeaderNames().stream().map(String::toString);
             responseData = CharStreams.toString(new InputStreamReader(responseDataStream, "UTF-8"));
            ctx.setResponseBody(responseData);
        } catch (IOException e) {
        }

        HttpServletResponse response = ctx.getResponse();
        response.setHeader("response-time", LocalDateTime.now().toString());
        Collection<String> headerNames = response.getHeaderNames();
        /*headerNames.forEach((name) -> {
            logger.info("POST_HEADER_NAMES:{} HEADER:{}", name, response.getHeader(name));
        });*/
        Map<String, String> zuulRequestHeaders = ctx.getZuulRequestHeaders();
        /*zuulRequestHeaders.forEach((k, v) -> {
            logger.info("POST_ZuulRequestHeaders K:{} V:{}", k, v);
        });
        */
         String responseTime = response.getHeader("response-time");
         String requestTime =  zuulRequestHeaders.get("request-time");
        logger.info("ANALITIC requestTime: {}  httpCode: {} responseBody: {} responseTime: {}",requestTime, responseStatus, responseData, responseTime);
        return null;
    }
}
