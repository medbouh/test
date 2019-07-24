package com.a2a.api.microservices.application.repository;

import com.a2a.api.microservices.application.domaine.Statut;
import org.springframework.data.repository.CrudRepository;

public interface StatutRepository extends CrudRepository<Statut, Integer> {
}
