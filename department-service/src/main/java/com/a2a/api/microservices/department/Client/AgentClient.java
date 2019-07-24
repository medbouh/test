package com.a2a.api.microservices.department.Client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Client pour le service department-service
 *
 * @author mohamed
 */
@FeignClient(name = "DEPARTMENT-SERVICE", url = "http://test")
public interface AgentClient {

    @GetMapping(value = "/agent/{departmentId}")
    String findDepartmetById(@PathVariable("departmentId") int departmentId);

    @GetMapping(value = "/agents")
    List<String> findAllepartmets();

}
