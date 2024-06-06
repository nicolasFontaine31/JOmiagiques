package com.example.jomiagique.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Delegation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private int nbMedaillesOr;
    private int nbMedaillesArgent;
    private int nbMedaillesBronze;

    @OneToMany(mappedBy = "idDelegation")
    @JsonBackReference(value = "delegation-Participant")
    private List<Participant> participants;

    public Delegation(long id, String name,int nbMedaillesOr,int nbMedaillesArgent,int nbMedaillesBronze) {
        this.id = id;
        this.name = name;
        this.nbMedaillesOr = nbMedaillesOr;
        this.nbMedaillesArgent = nbMedaillesArgent;
        this.nbMedaillesBronze = nbMedaillesBronze;
    }

    public Delegation() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbMedaillesOr() {
        return nbMedaillesOr;
    }

    public void setNbMedaillesOr(int nbMedaillesOr) {
        this.nbMedaillesOr = nbMedaillesOr;
    }

    public int getNbMedaillesArgent() {
        return nbMedaillesArgent;
    }

    public void setNbMedaillesArgent(int nbMedaillesArgent) {
        this.nbMedaillesArgent = nbMedaillesArgent;
    }

    public int getNbMedaillesBronze() {
        return nbMedaillesBronze;
    }

    public void setNbMedaillesBronze(int nbMedaillesBronze) {
        this.nbMedaillesBronze = nbMedaillesBronze;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }
}
