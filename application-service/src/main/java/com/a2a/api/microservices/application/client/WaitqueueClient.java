package com.a2a.api.microservices.application.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Client pour le service waitqueue-service
 *
 * @author mohamed
 */
@Component("WaitqueueClient")
@FeignClient(name = "WAITQUEUE-SERVICE", url = "http://test")
public interface WaitqueueClient {

    @RequestMapping(value = ClientConstants.ADD_TO_WAITQUEUE, method = RequestMethod.GET)
    void addApplicationByDepartmentInTheWaitedList(@PathVariable(ClientConstants.DEPARTMENT_ID) int departmentId);

    @RequestMapping(value = ClientConstants.DELETE_TO_WAITQUEUE, method = RequestMethod.GET)
    void decreaseNbOfApplicationInTheWaitedList(@PathVariable(ClientConstants.DEPARTMENT_ID) int departmentId);


    @RequestMapping(value = ClientConstants.LAST_NUMBER_OF_WAITQUEUE, method = RequestMethod.GET)
    int findLastNumeroByDepartmentIdInTheWaitedList(@PathVariable(ClientConstants.DEPARTMENT_ID) int departmentId);

    @RequestMapping(value = ClientConstants.INCREASE_LAST_NUMBER_OF_WAITQUEUE, method = RequestMethod.GET)
    void increaseLastNumeroByDepartmentIdInTheWaitedList(@PathVariable(ClientConstants.DEPARTMENT_ID) int departmentId);


    @RequestMapping(value = ClientConstants.INCREASE_INDEX_OF_WAITQUEUE, method = RequestMethod.GET)
    void increaseIndexByDepartmentIdInTheWaitedList(@PathVariable(ClientConstants.DEPARTMENT_ID) int departmentId);

}
