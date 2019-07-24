package com.a2a.api.microservices.application.service;

import com.a2a.api.microservices.ApiException;
import com.a2a.api.microservices.application.client.WaitqueueClient;
import com.a2a.api.microservices.application.domaine.Agent;
import com.a2a.api.microservices.application.domaine.Application;
import com.a2a.api.microservices.application.domaine.Comment;
import com.a2a.api.microservices.application.repository.ApplicationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    Logger logger = LoggerFactory.getLogger(ApplicationService.class);

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    @Qualifier("WaitqueueClient")
    private WaitqueueClient waitqueueClient;

    /*
    public ApplicationService(ApplicationRepository applicationRepository, CommentService commentService, WaitqueueClient waitqueueClient) {
        this.applicationRepository = applicationRepository;
        this.commentService = commentService;
        this.waitqueueClient = waitqueueClient;
    }
    */

    public void init(){
        logger.info("Initialisation des demandes ");
        Application application = new Application();
        Application applicationTwo = new Application();
        application.setDepartmentId(1);
        application.setStatutId(1);
        applicationTwo.setDepartmentId(2);
        applicationTwo.setStatutId(1);
        applicationRepository.save(application);
        applicationRepository.save(applicationTwo);
    }

    public List<Application> findAllApplications(){
        logger.info("Récupération des demandes ");
        List<Application> applications = new ArrayList<>();
        Iterable<Application> iterator = applicationRepository.findAll();
        iterator.iterator().forEachRemaining(applications::add);
         return applications;
    }

    public List<Application> findAllApplicationsByDepartmentId(int departmentId){
        logger.info("Récupération les demandes du service {} ",departmentId);
        List<Application> applications = new ArrayList<>();
        Iterable<Application> iterator = applicationRepository.findAll();
        iterator.iterator().forEachRemaining(applications::add);
        return applications.stream().filter(application->application.getDepartmentId()==departmentId).collect(Collectors.toList());
    }

    public Application findAllApplicationsByDepartmentIdAndApplicationNumero(int departmentId, int applicationNumero){
        logger.info("Récupération les demandes du service {} ",departmentId);
        List<Application> applications = new ArrayList<>();
        Iterable<Application> iterator = applicationRepository.findAll();
        iterator.iterator().forEachRemaining(applications::add);
        List<Application> applicationfiltrees = applications.stream().filter(application->application.getDepartmentId()==departmentId && application.getNumero()==applicationNumero).collect(Collectors.toList());
        if(applicationfiltrees.size()==0){
            throw new ApiException("aucune demande n'est enregistrée avec ce numéro");
        }else{
            Application p = applicationfiltrees.get(0);
            return p;
        }
    }


    public boolean ifExists(Application newApplication){
        List<Application> applications = findAllApplications().stream().filter(application->{
            if(application.getId()==newApplication.getId()){
                return true;
            }else{
                return false;
            }
        }).collect(Collectors.toList());
        return applications.size()>0 ? true:false;
    }

    public Application findApplicationBydepartmentAndNumero(int departmentId, int ApplicationNumero){
        List<Application> applications = findAllApplications().stream().filter(application->{
            if(application.getNumero()==ApplicationNumero && application.getDepartmentId()==departmentId){
                return true;
            }else{
                return false;
            }
        }).collect(Collectors.toList());
        if(applications.size()>0){
            return applications.get(0);
        }else{
            throw new ApiException("la demande avec le numéro saisi n'existe pas dans ce service");
        }
    }

    public Application findApplicationById(int id){
        List<Application> applications = findAllApplications().stream().filter(application->{
            if(application.getNumero()==id){
                return true;
            }else{
                return false;
            }
        }).collect(Collectors.toList());
        if(applications.size()>0){
            return applications.get(0);
        }else{
            throw new ApiException("la demande avec le numéro saisi n'existe pas");
        }
    }

    public boolean ifExistsById(int applicationId){
        List<Application> applications = findAllApplications().stream().filter(application->{
            if(application.getId()==applicationId){
                return true;
            }else{
                return false;
            }
        }).collect(Collectors.toList());
        return applications.size()>0 ? true:false;
    }

    public void createNewApplication(Application application){
        logger.info("Création d'une nouvelle demande dans le service {}", application.getDepartmentId());
        waitqueueClient.addApplicationByDepartmentInTheWaitedList(application.getDepartmentId());
        int index = waitqueueClient.findLastNumeroByDepartmentIdInTheWaitedList(application.getDepartmentId());
        application.setNumero(index+1);
        application.setStatutId(1);
        application.setCreatDate(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        int applicationId = applicationRepository.save(application).getId();
        waitqueueClient.increaseLastNumeroByDepartmentIdInTheWaitedList(application.getDepartmentId());


    }

    public void callApplicationByAgent(int departmentId, int applicationNumero, Agent agent){
        int applicationId = findApplicationBydepartmentAndNumero(departmentId,applicationNumero).getId();
        logger.info("l'appel à demande {} dans le service {} par l'agent {}", applicationId,departmentId,agent.getLastName());
        if(ifExistsById(applicationId)){
            waitqueueClient.decreaseNbOfApplicationInTheWaitedList(departmentId);
            waitqueueClient.increaseIndexByDepartmentIdInTheWaitedList(departmentId);
        applicationRepository.callApplicationByAgent(applicationId, agent.getId(),2,LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        }else {
            throw new ApiException("la demande n'existe pas ");
        }
    }

    public void treatApplicationByAgent(int departmentId, int applicationNumero, Agent agent){
        int applicationId = findApplicationBydepartmentAndNumero(departmentId,applicationNumero).getId();
        logger.info("traitement de la demande {} dans le service {} par l'agent {} ", applicationId,departmentId,agent.getLastName());
        if(ifExistsById(applicationId)){
            applicationRepository.treatApplicationByAgent(applicationId, agent.getId(),3,LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        }else {
            throw new ApiException("la demande n'existe pas ");
        }
    }

    public void abandonApplicationByAgent(int departmentId, int applicationNumero, Agent agent){
        int applicationId = findApplicationBydepartmentAndNumero(departmentId,applicationNumero).getId();
        logger.info("la demande {} dans le service {} a été abondonnée par l'agent {}", applicationId,departmentId,agent.getLastName());
        if(ifExistsById(applicationId)){
            applicationRepository.abandonApplicationByAgent(applicationId, agent.getId(),4,LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        }else {
            throw new ApiException("la demande n'existe pas ");
        }
    }

    public void closeApplicationByAgent(int departmentId, int applicationNumero, Agent agent){
        int applicationId = findApplicationBydepartmentAndNumero(departmentId,applicationNumero).getId();
        logger.info("la demande {} dans le service {} a été cloturer par l'agent {}", applicationId,departmentId,agent.getLastName());
        if(ifExistsById(applicationId)){
            applicationRepository.closeApplicationByAgent(applicationId, agent.getId(),5,LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        }else {
            throw new ApiException("la demande n'existe pas ");
        }
    }

    public void addCommentToApplication(int departmentId, int applicationNumero, Comment comment){
        int applicationId = findApplicationBydepartmentAndNumero(departmentId,applicationNumero).getId();
        int idCommentaire = commentService.ajouterCommentaire(comment);
        applicationRepository.addCommentToApplication(applicationId, idCommentaire);
    }

}
