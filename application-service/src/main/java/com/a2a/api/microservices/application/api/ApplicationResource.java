package com.a2a.api.microservices.application.api;

import com.a2a.api.microservices.application.domaine.Agent;
import com.a2a.api.microservices.application.domaine.Application;
import com.a2a.api.microservices.application.domaine.Comment;
import com.a2a.api.microservices.application.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApplicationResource {

    @Autowired
    ApplicationService applicationService;

    @GetMapping(value ="/init-demandes")
    public String init() {
        applicationService.init();
        return "Initialisation de la base a été fait avec succés";
    }

    @GetMapping(value ="/applications")
    public List<Application> findAllDemandes() {
       return applicationService.findAllApplications();

    }

    @GetMapping(value ="/applications/{departmentId}")
    public List<Application> findAllApplicationsByDepartmentId(@PathVariable int departmentId) {
        return applicationService.findAllApplicationsByDepartmentId(departmentId);

    }
    @GetMapping(value = "/applications/{departmentId}/{applicationNumero}")
    public Application findAllApplicationsByDepartmentIdAndApplicationNumero(@PathVariable int departmentId, @PathVariable int applicationNumero) {
        return applicationService.findAllApplicationsByDepartmentIdAndApplicationNumero(departmentId,applicationNumero);

    }

    @PostMapping(value = "/creat")
    public void createNewApplication(@RequestBody Application application) {
        applicationService.createNewApplication(application);
    }

    @PutMapping(value = "/call/{departmentId}/{applicationNumero}")
    public void callApplicationByAgent(@PathVariable int departmentId, @PathVariable int applicationNumero, @RequestBody Agent agent) {
         applicationService.callApplicationByAgent(departmentId,applicationNumero, agent);
    }

    @PutMapping(value = "/treat/{departmentId}/{applicationNumero}")
    public void treatApplicationByAgent(@PathVariable int departmentId, @PathVariable int applicationNumero,@RequestBody Agent agent) {
        applicationService.treatApplicationByAgent(departmentId,applicationNumero, agent);
    }
    @PutMapping(value = "/abandon/{departmentId}/{applicationId}")
    public void updateDeabandonApplicationByAgent(@PathVariable int departmentId, @PathVariable int applicationNumero,@RequestBody Agent agent) {
        applicationService.abandonApplicationByAgent(departmentId,applicationNumero, agent);
    }

    @PutMapping(value = "/close/{departmentId}/{applicationNumero}")
    public void closeApplicationByAgent(@PathVariable int departmentId, @PathVariable int applicationNumero, @RequestBody Agent agent) {
        applicationService.closeApplicationByAgent(departmentId,applicationNumero, agent);
    }

    @PutMapping(value = "/addComment/{departmentId}/{applicationId}")
    public void addComment(@PathVariable int departmentId, @PathVariable int applicationNumero,@RequestBody Comment comment) {
        applicationService.addCommentToApplication(departmentId,applicationNumero, comment);
    }

}