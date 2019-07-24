package com.a2a.api.microservices.waitqueue.service;


import com.a2a.api.microservices.ApiException;
import com.a2a.api.microservices.waitqueue.client.DepartmentClient;
import com.a2a.api.microservices.waitqueue.domaine.CostumerWaitqueue;
import com.a2a.api.microservices.waitqueue.domaine.Waitqueue;
import com.a2a.api.microservices.waitqueue.repository.WaitqueueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WaitqueueService {

    Logger logger = LoggerFactory.getLogger(WaitqueueService.class);

    @Autowired
    private WaitqueueRepository waitqueueRepository;

    @Autowired
    DepartmentClient departmentClient;

    //récupérer toutes les files d'attentes enregistrées dans la base
    public List<CostumerWaitqueue> costumerfindAllWaitedList(){
        logger.info("Récupération des files d'attentes");
        List<Waitqueue> waitedLists = new ArrayList<>();
        Iterable<Waitqueue> iterator = waitqueueRepository.findAll();
        iterator.iterator().forEachRemaining(waitedLists::add);
         return waitedLists.stream().map(d-> new CostumerWaitqueue(
                 departmentClient.findDepartmentById(d.getDepartmentId()).getName(),d.getIndex(),d.getNb())).collect(Collectors.toList());
    }

    //récupérer toutes les files d'attentes enregistrées dans la base
    public List<Waitqueue> findAllWaitedList() {
        logger.info("Récupération des files d'attentes");
        List<Waitqueue> waitedLists = new ArrayList<>();
        Iterable<Waitqueue> iterator = waitqueueRepository.findAll();
        iterator.iterator().forEachRemaining(waitedLists::add);
        return waitedLists;
    }

    //récupérer une file d'attente
    public Waitqueue findWaitedListById(int waitedListId) throws ApiException{
        logger.info("Récupération de la file d'attente {}", waitedListId);
            try{
                Optional<Waitqueue> p =  waitqueueRepository.findById(waitedListId);
                if(p.isPresent()){
                   return p.get();
                }else {
                    throw new ApiException("File d'attente non trouvée");
                }
            }catch (Exception ex){
                throw new ApiException("File d'attente non trouvée");
            }
    }

    public Waitqueue findTheWaitedListByDepartmentId(int DepartmentId) throws ApiException{
        logger.info("Récupération de la file d'attente du service {}", DepartmentId);
       List<Waitqueue> waitedLists = findAllWaitedList().stream().filter(p->p.getDepartmentId()==DepartmentId).collect(Collectors.toList());
       if(waitedLists.size()!=0){
           return waitedLists.get(0);
       }else {
           throw new ApiException("Pas de file d'attente pour ce service");
       }
    }


    public boolean ifexistsByDepartmentId(int DepartmentId) throws ApiException{
        List<Waitqueue> waitedLists = findAllWaitedList().stream().filter(p->p.getDepartmentId()==DepartmentId).collect(Collectors.toList());
        if(waitedLists.size()!=0){
            return true;
        }else {
            return false;
        }
    }


    public void addApplicationByDepartmentInTheWaitedList(int DepartmentId){
        logger.info("L'ajout d'une file d'attente pour le service {}", DepartmentId);
        int nb = findNumberOfApplicationByDepartmentIdInTheWaitedList(DepartmentId);
        if(!ifexistsByDepartmentId(DepartmentId)) {
            waitqueueRepository.save(new Waitqueue(Integer.valueOf(DepartmentId), 1, 1,0));//serviceId, nb);
        }else{
            increaseNbOfApplicationInTheWaitedList(DepartmentId);
        }
    }

    public int findNumberOfApplicationByDepartmentIdInTheWaitedList(int DepartmentId){
        int nb = 0 ;
        if(ifexistsByDepartmentId(DepartmentId)){
            nb= waitqueueRepository.findNumberOfApplicationByDepartmentIdInTheWaitedList(DepartmentId);
        }
        return nb;
    }

    public void increaseNbOfApplicationInTheWaitedList(int departmentId){
        logger.info("Incrémenter de le compteur de la file d'attente du service {}", departmentId);
        if(ifexistsByDepartmentId(departmentId)) {
            int nb = findNumberOfApplicationByDepartmentIdInTheWaitedList(departmentId) + 1;
            waitqueueRepository.decreaseNbOfApplicationInTheWaitedList(departmentId, nb);
        }
    }


    public void decreaseNbOfApplicationInTheWaitedList(int departmentId){
        logger.info("Désincrémenter de le compteur de la file d'attente du service {}", departmentId);
        if(ifexistsByDepartmentId(departmentId)) {
            int nb = findNumberOfApplicationByDepartmentIdInTheWaitedList(departmentId) - 1;
            waitqueueRepository.decreaseNbOfApplicationInTheWaitedList(departmentId, nb);
        }
    }



    public int findIndexByDepartmentIdInTheWaitedList(int departmentId){
        logger.info("Récupération de le compteur de la file d'attente du service {}", departmentId);
        if(ifexistsByDepartmentId(departmentId)){
            Waitqueue waitedList = findTheWaitedListByDepartmentId(departmentId);
            return waitedList.getIndex();
        }
        return 0;
    }

    public void increaseIndexByDepartmentIdInTheWaitedList(int departmentId){
        logger.info("Incrémenter le compteur de la file d'attente du service {}", departmentId);
        if(ifexistsByDepartmentId(departmentId)) {
            int nb = findIndexByDepartmentIdInTheWaitedList(departmentId) + 1;
            waitqueueRepository.increaseIndexByDepartmentIdInTheWaitedList(departmentId, nb);
        }
    }

    public int findLastNumeroByDepartmentIdInTheWaitedList(int departmentId){
        logger.info("Récupération le dernier numero de la file d'attente du service {}", departmentId);
        if(ifexistsByDepartmentId(departmentId)){
            Waitqueue waitedList = findTheWaitedListByDepartmentId(departmentId);
            return waitedList.getLastNumero();
        }
        return 0;
    }

    public void increaseLastNumeroByDepartmentIdInTheWaitedList(int departmentId){
        logger.info("Incrémenter le dernier numero de la file d'attente du service {}", departmentId);
        if(ifexistsByDepartmentId(departmentId)) {
            int nb = findLastNumeroByDepartmentIdInTheWaitedList(departmentId) + 1;
            waitqueueRepository.increaseLastNumeroByDepartmentIdInTheWaitedList(departmentId, nb);
        }
    }
}
