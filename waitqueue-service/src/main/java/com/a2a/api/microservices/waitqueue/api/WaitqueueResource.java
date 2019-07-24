package com.a2a.api.microservices.waitqueue.api;

import com.a2a.api.microservices.waitqueue.domaine.CostumerWaitqueue;
import com.a2a.api.microservices.waitqueue.domaine.Waitqueue;
import com.a2a.api.microservices.waitqueue.service.WaitqueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WaitqueueResource {

    @Autowired
    WaitqueueService waitedListService;

    @GetMapping(value = "/waitedList")
    public List<CostumerWaitqueue> findAllWaitedList() {
       return waitedListService.costumerfindAllWaitedList();

    }

    @GetMapping(value = "/waitedList/{id}")
    public Waitqueue findWaitedListById(@PathVariable int waitedListId) {
        return waitedListService.findWaitedListById(waitedListId);

    }

    @GetMapping(value = "/waitedList/department/{id}")
    public Waitqueue findWaitedListByDepartmentId(@PathVariable int departmentId) {
        return waitedListService.findTheWaitedListByDepartmentId(departmentId);

    }

    @GetMapping(value = Constants.ADD_TO_WAITQUEUE)
    public void addApplicationByDepartmentInTheWaitedList(@PathVariable(Constants.DEPARTMENT_ID) int departmentId){
        waitedListService.addApplicationByDepartmentInTheWaitedList(departmentId);
    }

    @GetMapping(value = Constants.DELETE_TO_WAITQUEUE)
    public void decreaseNbOfApplicationInTheWaitedList(@PathVariable(Constants.DEPARTMENT_ID) int departmentId){
        waitedListService.decreaseNbOfApplicationInTheWaitedList(departmentId);
    }

    @GetMapping(value = Constants.LAST_NUMBER_OF_WAITQUEUE)
    public int findLastNumeroByDepartmentIdInTheWaitedList(@PathVariable(Constants.DEPARTMENT_ID) int departmentId){
        return waitedListService.findLastNumeroByDepartmentIdInTheWaitedList(departmentId);
    }

    @GetMapping(value = Constants.INCREASE_LAST_NUMBER_OF_WAITQUEUE)
    public void increaseLastNumeroByDepartmentIdInTheWaitedList(@PathVariable(Constants.DEPARTMENT_ID) int departmentId){
        waitedListService.increaseLastNumeroByDepartmentIdInTheWaitedList(departmentId);
    }

    @GetMapping(value = Constants.INCREASE_INDEX_OF_WAITQUEUE)
    public void increaseIndexByDepartmentIdInTheWaitedList(@PathVariable(Constants.DEPARTMENT_ID) int departmentId){
        waitedListService.increaseIndexByDepartmentIdInTheWaitedList(departmentId);
    }



}