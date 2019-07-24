package com.a2a.api.microservices.waitqueue.client;

import com.a2a.api.microservices.waitqueue.domaine.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Client pour le service department-service
 *
 * @author mohamed
 */

@Component
@FeignClient(name = "DEPARTMENT-SERVICE", url = "")
public interface DepartmentClient {

    @GetMapping(value = ClientConstants.DEPARTMENT_BY_ID)
    Department findDepartmentById(@PathVariable(ClientConstants.DEPARTMENT_ID) int departmentId);

}
