package com.a2a.api.microservices.agent.service;

import com.a2a.api.microservices.ApiException;
import com.a2a.api.microservices.agent.client.DepartmentClient;
import com.a2a.api.microservices.agent.domaine.Agent;
import com.a2a.api.microservices.agent.domaine.Department;
import com.a2a.api.microservices.agent.repository.AgentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgentService {

    Logger logger = LoggerFactory.getLogger(AgentService.class);

    @Autowired
    private AgentRepository agentRepository;

    @Autowired
    private DepartmentClient departmentClient;

    public String sayHello(String name){
        return "Hello "+name;
    }
     public void init(){
        logger.info("Initialisation des agents");
        agentRepository.save(new Agent("moha","bouha","1"));
        agentRepository.save(new Agent("marc","ojo","1"));
        agentRepository.save(new Agent("jean","machin","1"));
    }

    public List<Agent> findAllAgentsWithDepartmentName(){
        List<Agent> agents = findAllAgents();
        List<Department> departments = departmentClient.findAllepartmets();
        return agents.stream().map(agent -> agent.setDepartmentId(departments.get(0).getName())).collect(Collectors.toList());
     }

    public List<Agent> findAllAgents(){
        logger.info("Récupération de tous les agens");
        List<Agent> agents = new ArrayList<>();
        Iterable<Agent> iterator = agentRepository.findAll();
        iterator.iterator().forEachRemaining(agents::add);
        return agents;
    }


    public boolean ifExists(Agent newAgent){
    List<Agent> agents = findAllAgents()
            .stream()
            .filter(agent->{
            if(agent.getFirstName().equalsIgnoreCase(newAgent.getFirstName()) && agent.getLastName().equalsIgnoreCase(newAgent.getLastName())){
                return true;
                }else{
                    return false;
                }
            })
            .collect(Collectors.toList());
    return agents.size()>0 ? true:false;
    }

    public boolean ifExistsById(int agentId){
        List<Agent> agents = findAllAgents().stream().filter(agent->{
            if(agent.getId()==agentId){
                return true;
            }else{
                return false;
            }
        }).collect(Collectors.toList());
        return agents.size()>0 ? true:false;
    }

    public Agent findAgentById(int agentId) throws ApiException {
        logger.info("Récupération de l'agent {}",agentId);
        try{
            Optional<Agent> p =  agentRepository.findById(agentId);
            if(p.isPresent()){
                return p.get();//.get();
            }
            else {
                throw new ApiException("Agent non trouvée");
            }
        }catch (Exception ex){
            throw new ApiException("Agent non trouvée");
        }
    }

    public void addAgent(Agent agent){
        logger.info("Ajout d'un nouveau agent: {} {}", agent.getFirstName(),agent.getLastName());
        if(ifExists(agent)){
            throw new ApiException("Il y a déja un agent enregistré avec ce nom & prénom");
        }else{
            agentRepository.save(agent);
        }
    }


    public void deleteAgentById(int agentId){
        logger.info("Suppression de l'agent: {}", agentId);
        if(!ifExistsById(agentId)){
            throw new ApiException("Aucun n'agent n'est enregistré avec ce numéro");
        }else{
            Agent p = findAgentById(agentId);
            agentRepository.delete(p);
        }

    }

}
