package com.a2a.api.microservices;

//import org.springframework.cloud.netflix.feign.FeignClient;
//import org.springframework.core.annotation.AliasFor;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Component
@Documented
@Target(ElementType.TYPE)
@Retention(RUNTIME)
@FeignClient(name = ServicesUris.WAITQUEUE_SERVICE, url = "http://test")
public @interface WaitQueueFeignClient {

    @AliasFor(annotation = FeignClient.class, attribute = "fallback")
    Class<?> fallback() default void.class;

}
