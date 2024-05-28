package com.example.jomiagique.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Epreuve {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nomEpreuve;
    private Date date;
    private String infrastructure;
    private int nombreDePlaces;
    @OneToMany(mappedBy = "idEpreuve")
    @JsonManagedReference(value = "billets-epreuve")
    private List<Billet> billets;

    public Epreuve(){

    }

    public Epreuve(long id, String nomEpreuve, Date date, String infrastructure, int nombreDePlaces, List<Billet> billets) {
        super();
        this.id = id;
        this.nomEpreuve = nomEpreuve;
        this.date = date;
        this.infrastructure = infrastructure;
        this.nombreDePlaces = nombreDePlaces;
        this.billets = billets;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomEpreuve() {
        return nomEpreuve;
    }

    public void setNomEpreuve(String nomEpreuve) {
        this.nomEpreuve = nomEpreuve;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getInfrastructure() {
        return infrastructure;
    }

    public void setInfrastructure(String infrastructure) {
        this.infrastructure = infrastructure;
    }

    public int getNombreDePlaces() {
        return nombreDePlaces;
    }

    public void setNombreDePlaces(int nombreDePlaces) {
        this.nombreDePlaces = nombreDePlaces;
    }

    public List<Billet> getBillets() {
        return billets;
    }

    public void setBillets(List<Billet> billets) {
        this.billets = billets;
    }

    @Override
    public String toString() {
        return "Epreuve{" +
                "id=" + id +
                ", nomEpreuve='" + nomEpreuve + '\'' +
                ", date=" + date +
                ", infrastructure='" + infrastructure + '\'' +
                ", nombreDePlaces=" + nombreDePlaces +
                ", billets=" + billets +
                '}';
    }
}
