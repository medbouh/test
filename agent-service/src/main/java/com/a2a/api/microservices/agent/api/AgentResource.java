package com.a2a.api.microservices.agent.api;

import com.a2a.api.microservices.agent.domaine.Agent;
import com.a2a.api.microservices.agent.service.AgentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AgentResource {
    Logger logger = LoggerFactory.getLogger(AgentResource.class);

    @Autowired
    AgentService agentService;

    @GetMapping(value = "/hello")
    public String sayHello(@RequestParam(value="name", defaultValue="World") String name) {
        logger.trace("TRACE Message");
        logger.debug("DEBUG Message");
        logger.info("INFO Message");
        logger.warn("WARN Message");
        logger.error("ERROR Message");
       return agentService.sayHello(name);
       }

    @GetMapping(value ="/init-agents")
    public String init() {
        agentService.init();
        return "Initialisation des agents dans la base a été fait avec succés";
    }

    @GetMapping(value ="/agents")
    public List<Agent> findAllAgents() {
       return agentService.findAllAgentsWithDepartmentName();

    }

    @GetMapping(value = "/agents/{agentId}")
    public Agent findAgentById(@PathVariable int agentId) {
        return agentService.findAgentById(agentId);
    }


    @DeleteMapping(value = "/agents/{agentId}")
    public void deleteAgentBy(@PathVariable int agentId) {
        agentService.deleteAgentById(agentId);

    }

    @PostMapping(value = "/agents")
    public void addAgent(@RequestBody Agent agent) {
        agentService.addAgent(agent);
    }
}