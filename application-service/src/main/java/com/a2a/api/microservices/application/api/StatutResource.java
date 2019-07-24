package com.a2a.api.microservices.application.api;

import com.a2a.api.microservices.application.domaine.Statut;
import com.a2a.api.microservices.application.service.StatutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StatutResource {

    @Autowired
    StatutService statutService;

    @GetMapping(value = "/init-statuts")
    public String init() {
        statutService.init();
        return "Initialisation de la base a été fait avec succés";
    }

    @GetMapping(value = "/statuts")
    public List<Statut> findAllDepartments() {
        return statutService.findAllStatuts();
    }

    @DeleteMapping(value = "/statuts/{statutId}")
    public void deleteAgentBy(@PathVariable int statutId) {
        statutService.findStatutById(statutId);

    }
    @PostMapping(value = "/statuts")
    public void addDepartment(@RequestBody Statut statut) {
        statutService.addStatut(statut);
    }
}