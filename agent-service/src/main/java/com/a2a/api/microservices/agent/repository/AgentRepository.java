package com.a2a.api.microservices.agent.repository;

import com.a2a.api.microservices.agent.domaine.Agent;
import org.springframework.data.repository.CrudRepository;

public interface AgentRepository extends CrudRepository<Agent, Integer> {
}
