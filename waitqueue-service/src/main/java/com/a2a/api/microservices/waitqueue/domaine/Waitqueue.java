package com.a2a.api.microservices.waitqueue.domaine;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Waitqueue {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int departmentId;

    private int nb;

    private int index;

    private int lastNumero;

    public Waitqueue() {
    }

    public Waitqueue(int departmentId, int nb) {
        this.departmentId = departmentId;
        this.nb = nb;
    }

    public Waitqueue(int departmentId, int nb, int index, int lastNumero) {
        this.departmentId = departmentId;
        this.nb = nb;
        this.index = index;
        this.lastNumero=lastNumero;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getLastNumero() {
        return lastNumero;
    }

    public void setLastNumero(int lastNumero) {
        this.lastNumero = lastNumero;
    }
}
