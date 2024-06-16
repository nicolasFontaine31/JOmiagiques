package com.example.jomiagique.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Billet {
    public Billet() {
    }

    public enum Etat{
        valider, reserver,annule, payer
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    @JsonBackReference(value = "billets-epreuve")
    private Epreuve idEpreuve;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JsonBackReference(value = "spectateur-billets")
    private Spectateur idSpectateur;
    private long prix;
    private Date dateAchat;
    private Etat etat;

    public Billet(long id, Epreuve idEpreuve, Spectateur idSpectateur, long prix,Date dateAchat, Etat etat) {
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

    public Epreuve getIdEpreuve() {
        return idEpreuve;
    }

    public void setIdEpreuve(Epreuve idEpreuve) {
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

    @Override
    public String toString() {
        return "Billet{" +
                "id=" + id +
                ", idEpreuve=" + idEpreuve +
                ", idSpectateur=" + idSpectateur +
                ", prix=" + prix +
                ", dateAchat=" + dateAchat +
                ", etat=" + etat +
                '}';
    }
}
