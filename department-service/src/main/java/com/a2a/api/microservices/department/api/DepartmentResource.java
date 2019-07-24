package com.a2a.api.microservices.department.api;

import com.a2a.api.microservices.department.domaine.Department;
import com.a2a.api.microservices.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentResource {
     @Autowired
    DepartmentService departmentService;

    @GetMapping(value = "/init-departments")
    public String init() {
        departmentService.init();
        return "Initialisation de la base a été fait avec succés";
    }

    @GetMapping(value = "/departments")
    public List<Department> findAllDepartments() {
       return departmentService.findAllDepartments();
    }

    @GetMapping(value = "/department/{departmentId}")
    public void deleteAgentBy(@PathVariable int departmentId) {
        departmentService.findDepartmentById(departmentId);

    }
    @PostMapping(value = "/department")
    public void addDepartment(@RequestBody Department department) {
        departmentService.addDepartment(department);
    }
}