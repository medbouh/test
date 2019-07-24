package com.a2a.api.microservices.agent.client;

import com.a2a.api.microservices.agent.domaine.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Client pour le service department-service
 *
 * @author mohamed
 */
@FeignClient(name = "department-service")
public interface DepartmentClient {

    @GetMapping(value = "/department/{departmentId}")
    Department findDepartmetById(@PathVariable("departmentId") int departmentId);

    @GetMapping(value = "/departments")
    List<Department> findAllepartmets();

}
