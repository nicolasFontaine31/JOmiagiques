package com.example.jomiagique.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Resultats {

    public enum position{
        premier, deuxieme,troisieme, forfait
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String score;
    private position position;
    //id participants
    //@ManyToMany(mappedBy = "resultats", fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "participants-resultats")
    private Participant participants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "epreuves-resultats")
    private Epreuve epreuve;

    public Resultats(){

    }

    public Resultats(long id, String score, position position, Participant participant, Epreuve epreuve) {
        super();
        this.id = id;
        this.score = score;
        this.position = position;
        this.participants = participant;
        this.epreuve = epreuve;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public position getPosition() {
        return position;
    }

    public void setPosition(position position) {
        this.position = position;
    }

    public Participant getParticipant() {
        return participants;
    }

    public void setParticipants(Participant participant) {
        this.participants = participants;
    }

    public Epreuve getEpreuves() {
        return epreuve;
    }

    public void setEpreuves(Epreuve epreuve) {
        this.epreuve = epreuve;
    }
}
