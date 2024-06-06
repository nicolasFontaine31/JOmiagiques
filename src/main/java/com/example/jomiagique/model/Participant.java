package com.example.jomiagique.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.util.List;
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
    @ManyToOne
    @JsonBackReference(value = "delegation-Participant")
    private Delegation idDelegation;



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


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Participant_Resultat_Tab",
            joinColumns = {
                    @JoinColumn(name = "Participant_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "resultat_id", referencedColumnName = "id")
            }
    )
    private List<Resultats> resultats;

    public Participant(){

    }

    public Participant(long id, String nom, String prenom, String adressemail, Set<Epreuve> epreuves, List<Resultats> resultats) {
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adressemail = adressemail;
        this.epreuves = epreuves;
        this.resultats = resultats;
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

    public List<Resultats> getIdResultats() {
        return resultats;
    }

    public void setIdResultats(List<Resultats> idResultats) {
        this.resultats = idResultats;
    }
}
