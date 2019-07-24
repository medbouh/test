package com.a2a.api.microservices.application.domaine;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String message;

    public Comment() {
    }

    public Comment(String commentaire) {
        this.message = commentaire;
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getCommentaire() {
        return message;
    }

    public void setCommentaire(String commentaire) {
        this.message = commentaire;
    }

}
