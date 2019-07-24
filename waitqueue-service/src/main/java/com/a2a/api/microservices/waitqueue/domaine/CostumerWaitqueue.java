package com.a2a.api.microservices.waitqueue.domaine;


public class CostumerWaitqueue {

    private String DepartmentName;

    private int suivant;

    private int nombre;

    public CostumerWaitqueue(String departmentName, int suivant, int nombre) {
        DepartmentName = departmentName;
        this.suivant = suivant;
        this.nombre = nombre;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public int getSuivant() {
        return suivant;
    }

    public void setSuivant(int suivant) {
        this.suivant = suivant;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
}
