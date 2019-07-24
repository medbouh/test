package com.a2a.api.microservices;

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
public interface DepartmentFeignClient {

    @GetMapping(value = "/department/{departmentId}")
    String findDepartmetById(@PathVariable("departmentId") int departmentId);

    @GetMapping(value = "/departments")
    List<String> findAllepartmets();

}
