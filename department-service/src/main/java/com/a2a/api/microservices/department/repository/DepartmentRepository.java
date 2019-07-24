package com.a2a.api.microservices.department.repository;

import com.a2a.api.microservices.department.domaine.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {
}
