package com.example.jomiagique.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Billet {
    public Billet() {
    }

    enum Etat{
        valider, reserver,annule
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long idEpreuve;
    @ManyToOne
    @JsonBackReference
    private Spectateur idSpectateur;
    private long prix;
    private Date dateAchat;
    private Etat etat;

    public Billet(long id, long idEpreuve, Spectateur idSpectateur, long prix,Date dateAchat, Etat etat) {
        super();
        this.id = id;
        this.idEpreuve = idEpreuve;
        this.idSpectateur = idSpectateur;
        this.prix = prix;
        this.dateAchat = dateAchat;
        this.etat = etat;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdEpreuve() {
        return idEpreuve;
    }

    public void setIdEpreuve(long idEpreuve) {
        this.idEpreuve = idEpreuve;
    }

    public Spectateur getIdSpectateur() {
        return idSpectateur;
    }

    public void setIdSpectateur(Spectateur idSpectateur) {
        this.idSpectateur = idSpectateur;
    }

    public long getPrix() {
        return prix;
    }

    public void setPrix(long prix) {
        this.prix = prix;
    }

    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.etat = etat;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }
}
