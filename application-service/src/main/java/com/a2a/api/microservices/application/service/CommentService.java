package com.a2a.api.microservices.application.service;

import com.a2a.api.microservices.application.domaine.Comment;
import com.a2a.api.microservices.application.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    // initier la table personne de la base
    public void init(){
        commentRepository.save(new Comment("Bon service"));
        commentRepository.save(new Comment("personnel non disponible"));
    }

    //récupérer tous les commentaires enregistrées dans la base
    public List<Comment> findAllCommentaires(){
        List<Comment> comments = new ArrayList<>();
        // ajouter chaque élement dans la liste
        Iterable<Comment> iterator = commentRepository.findAll();
        iterator.iterator().forEachRemaining(comments::add);
         return comments;
    }

    // ajouter un commentaire
    public int ajouterCommentaire(Comment p){
        return commentRepository.save(p).getId();
    }

}
