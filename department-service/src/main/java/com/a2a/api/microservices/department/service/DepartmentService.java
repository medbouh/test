package com.a2a.api.microservices.department.service;

import com.a2a.api.microservices.ApiException;
import com.a2a.api.microservices.department.Client.AgentClient;
import com.a2a.api.microservices.department.domaine.Department;
import com.a2a.api.microservices.department.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    Logger logger = LoggerFactory.getLogger(DepartmentService.class);
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private AgentClient agentClient;

    public void init(){
        logger.info("Initialisation des services");
        departmentRepository.save(new Department(1, "Visa"));
        departmentRepository.save(new Department(2,"Etat civil"));
    }

    public List<Department> findAllDepartments(){
        logger.info("Récupération de tous les services");
        List<Department> Departments = new ArrayList<>();
        Iterable<Department> iterator = departmentRepository.findAll();
        iterator.iterator().forEachRemaining(Departments::add);
        return Departments;
    }

    public boolean ifExists(Department newDepartment){
        List<Department> Departments = findAllDepartments().stream().filter(Department->{
            if(Department.getName().equalsIgnoreCase(newDepartment.getName())){
                return true;
            }else{
                return false;
            }
        }).collect(Collectors.toList());
        return Departments.size()>0 ? true:false;
    }

    public boolean ifExistsById(int DepartmentId){
        List<Department> Departments = findAllDepartments().stream().filter(Department->{
            if(Department.getId()==DepartmentId){
                return true;
            }else{
                return false;
            }
        }).collect(Collectors.toList());
        return Departments.size()>0 ? true:false;
    }

    public Department findDepartmentById(int DepartmentId) throws ApiException {
        logger.info("Récupération de l'Department "+DepartmentId);
        try{
            Optional<Department> p =  departmentRepository.findById(DepartmentId);
            if(p.isPresent()) {
            return p.get();
            }else {
                throw new ApiException("Department non trouvée");
            }
        }catch (Exception ex){
            throw new ApiException("Department non trouvée");
        }
    }

    public void addDepartment(Department Department){
        logger.info("Ajout d'un nouveau Department: "+ Department.getName());
        if(ifExists(Department)){
            throw new ApiException("Il y a déja un Department enregistré avec ce nom & prénom");
        }else{
            departmentRepository.save(Department);
        }
    }


    public void deleteDepartmentById(int DepartmentId){

        logger.info("Suppression du Department: "+DepartmentId);
        if(!ifExistsById(DepartmentId)){
            throw new ApiException("Aucun n'Department n'est enregistré avec ce numéro");
        }else{
            Department p = findDepartmentById(DepartmentId);
            departmentRepository.delete(p);
        }

    }


}
