package com.example.jomiagique.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.util.Set;

@Entity
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nom;
    private String prenom;
    private String adressemail;
    //Id delegation à faire


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Participant_Epreuve_Tab",
    joinColumns = {
            @JoinColumn(name = "Participant_id", referencedColumnName = "id")
    },
            inverseJoinColumns = {
            @JoinColumn(name = "Epreuve_id", referencedColumnName = "id")
            }
    )
    private Set<Epreuve> epreuves;

    //id Resultat à faire

    public Participant(){

    }

    public Participant(long id, String nom, String prenom, String adressemail, Set<Epreuve> epreuves) {
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adressemail = adressemail;
        this.epreuves = epreuves;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdressemail() {
        return adressemail;
    }

    public void setAdressemail(String adressemail) {
        this.adressemail = adressemail;
    }

    public Set<Epreuve> getEpreuves() {
        return epreuves;
    }

    public void setEpreuves(Set<Epreuve> epreuves) {
        this.epreuves = epreuves;
    }
}
