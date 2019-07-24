package com.a2a.api.microservices.application.domaine;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private int id;

    private int numero;

    private LocalDateTime creatDate;

    private LocalDateTime closeDate;

    private LocalDateTime treatDate;

    @Column(nullable = true)
    private int agentId;

    @Column(nullable = true)
    private int departmentId;

    @Column(nullable = true)
    private int statutId;

    @Column(nullable = true)
    private int commentId;

      public Application() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getAgentId() {
        return agentId;
    }

    public void setAgentId(Integer agentId) {
        this.agentId = agentId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getStatutId() {
        return statutId;
    }

    public void setStatutId(Integer statutId) {
        this.statutId = statutId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public LocalDateTime getCreatDate() {
        return creatDate;
    }

    public void setCreatDate(LocalDateTime creatDate) {
        this.creatDate = creatDate;
    }

    public LocalDateTime getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDateTime closeDate) {
        this.closeDate = closeDate;
    }

    public LocalDateTime getTreatDate() {
        return treatDate;
    }

    public void setTreatDate(LocalDateTime treatDate) {
        this.treatDate = treatDate;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
