package com.a2a.api.microservices.application.service;

import com.a2a.api.microservices.ApiException;
import com.a2a.api.microservices.application.domaine.Statut;
import com.a2a.api.microservices.application.repository.StatutRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatutService {

    Logger logger = LoggerFactory.getLogger(StatutService.class);

    @Autowired
    private StatutRepository statutRepository;

    public void init(){
        logger.info("Initialisation des status");
        statutRepository.save(new Statut(1,"créé"));
        statutRepository.save(new Statut(2,"en attente du citoyen"));
        statutRepository.save(new Statut(3,"en cours de traîtement"));
        statutRepository.save(new Statut(4,"abondonné"));
        statutRepository.save(new Statut(5,"traité"));
    }

    public List<Statut> findAllStatuts(){
        logger.info("Récupération de tous les status");
        List<Statut> Statuts = new ArrayList<>();
        Iterable<Statut> iterator = statutRepository.findAll();
        iterator.iterator().forEachRemaining(Statuts::add);
        return Statuts;
    }

    public boolean ifExists(Statut newStatut){
        List<Statut> Statuts = findAllStatuts().stream().filter(Statut->{
            if(Statut.getStatut().equalsIgnoreCase(newStatut.getStatut())){
                return true;
            }else{
                return false;
            }
        }).collect(Collectors.toList());
        return Statuts.size()>0 ? true:false;
    }

    public boolean ifExistsById(int statutId){
        List<Statut> Statuts = findAllStatuts().stream().filter(Statut->{
            if(Statut.getId()==statutId){
                return true;
            }else{
                return false;
            }
        }).collect(Collectors.toList());
        return Statuts.size()>0 ? true:false;
    }

    public Statut findStatutById(int statutId) throws ApiException {
        logger.info("Récupération de l'Statut "+statutId);
        try{
            Optional<Statut> p =  statutRepository.findById(statutId);
            if(p.isPresent()){
                return p.get();//.get();
            }else {
                throw new ApiException("Statut non trouvée");
            }
        }catch (Exception ex){
            throw new ApiException("Statut non trouvée");
        }
    }

    public void addStatut(Statut Statut){
        logger.info("Ajout d'un nouveau Statut: "+ Statut.getStatut());
        if(ifExists(Statut)){
            throw new ApiException("Il y a déja un Statut enregistré");
        }else{
            statutRepository.save(Statut);
        }
    }


    public void deleteStatutById(int statutId){
        logger.info("Suppression de l'Statut: "+statutId);
        if(!ifExistsById(statutId)){
            throw new ApiException("Aucun statut n'est enregistré avec ce numéro");
        }else{
            Statut p = findStatutById(statutId);
            statutRepository.delete(p);
        }

    }
}
