package com.a2a.api.microservices.application.repository;

import com.a2a.api.microservices.application.domaine.Application;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

public interface ApplicationRepository extends CrudRepository<Application, Integer> {

    @Transactional
    @Modifying  // pour les update et delete
    @Query(value = " UPDATE APPLICATION u SET u.agent_id = ?2 , u.statut_id = ?3 WHERE u.id = ?1",nativeQuery = true)
    void callApplicationByAgent(int applicationId, int idAgent, int statutId, LocalDateTime date);

    @Transactional
    @Modifying  // pour les update et delete
    @Query(value = " UPDATE APPLICATION u SET u.agent_id = ?2 , u.statut_id = ?3, treat_date=?4 WHERE u.id = ?1",nativeQuery = true)
    void treatApplicationByAgent(int applicationId, int idAgent, int statutId, LocalDateTime date);

    @Transactional
    @Modifying  // pour les update et delete
    @Query(value = " UPDATE APPLICATION u SET u.agent_id = ?2 , u.statut_id = ?3 WHERE u.id = ?1",nativeQuery = true)
    void abandonApplicationByAgent(int applicationId, int idAgent, int statutId, LocalDateTime date);

    @Transactional
    @Modifying  // pour les update et delete
    @Query(value = " UPDATE APPLICATION u SET u.agent_id = ?2 , u.statut_id = ?3 , close_date=?4 WHERE u.id = ?1",nativeQuery = true)
    void closeApplicationByAgent(int applicationId, int idAgent, int statutId, LocalDateTime date);

    @Transactional
    @Modifying  // pour les update e  t delete
    @Query(value = " UPDATE APPLICATION u SET u.comment_id = ?2 WHERE u.id = ?1",nativeQuery = true)
    void addCommentToApplication(int applicationId, int commentId);
}
