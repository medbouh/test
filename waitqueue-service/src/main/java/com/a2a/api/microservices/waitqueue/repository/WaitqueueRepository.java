package com.a2a.api.microservices.waitqueue.repository;

import com.a2a.api.microservices.waitqueue.domaine.Waitqueue;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface WaitqueueRepository extends CrudRepository<Waitqueue, Integer> {
    @Transactional
    @Modifying  // pour les update et delete
    @Query(value = "UPDATE WAITED_LIST u SET nb = ?2 WHERE u.department_id = ?1",nativeQuery = true)
    void decreaseNbOfApplicationInTheWaitedList(int serviceId, int nb);

    @Query(value = "SELECT nb FROM WAITED_LIST u WHERE u.department_id = ?1",nativeQuery = true)
    int findNumberOfApplicationByDepartmentIdInTheWaitedList(int serviceId);

    @Transactional
    @Modifying  // pour les update et delete
    @Query(value = "UPDATE WAITED_LIST u SET index = ?2 WHERE u.department_id = ?1",nativeQuery = true)
    void increaseIndexByDepartmentIdInTheWaitedList(int serviceId, int nb);

    @Transactional
    @Modifying  // pour les update et delete
    @Query(value = "UPDATE WAITED_LIST u SET last_numero = ?2 WHERE u.department_id = ?1",nativeQuery = true)
    void increaseLastNumeroByDepartmentIdInTheWaitedList(int serviceId, int nb);

    @Query(value = "SELECT index FROM WAITED_LIST u WHERE u.department_id = ?1",nativeQuery = true)
    int findIndexByServiceId(int serviceId);

}
