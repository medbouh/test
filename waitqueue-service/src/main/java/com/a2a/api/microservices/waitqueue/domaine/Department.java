package com.a2a.api.microservices.waitqueue.domaine;


public class Department {

    private int id;
    private String Name;

    public Department() {

    }

    public Department(int id, String name) {
        this.id = id;
        Name = name;
    }

    public Department(String statut) {
        this.Name = statut;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
}
