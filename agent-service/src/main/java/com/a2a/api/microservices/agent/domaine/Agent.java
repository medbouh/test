package com.a2a.api.microservices.agent.domaine;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    private String departmentId;

    public Agent() {
    }

    public Agent(String firstName, String lastName, String departmentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public Agent setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
        return this;
    }
}
