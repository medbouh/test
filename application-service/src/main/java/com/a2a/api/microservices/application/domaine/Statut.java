package com.a2a.api.microservices.application.domaine;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Statut {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int code;
    private String statut;

    public Statut() {

    }

    public Statut(int code, String statut) {
        this.code = code;
        this.statut = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
