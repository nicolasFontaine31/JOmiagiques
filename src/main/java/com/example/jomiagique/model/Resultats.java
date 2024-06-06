package com.example.jomiagique.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Resultats {

    private enum position{
        premier, deuxieme,troisieme
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String score;
    private position position;
    //id participants
    @ManyToMany(mappedBy = "resultats", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Participant> participants;

    public Resultats(){

    }

    public Resultats(long id, String score, position position, List<Participant> participants) {
        super();
        this.id = id;
        this.score = score;
        this.position = position;
        this.participants = participants;
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

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }
}
