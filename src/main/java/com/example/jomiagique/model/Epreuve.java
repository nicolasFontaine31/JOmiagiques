package com.example.jomiagique.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
public class Epreuve {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nomEpreuve;
    private Date date;
    private int nombreDePlaces;
    @OneToMany(mappedBy = "idEpreuve")
    @JsonManagedReference(value = "billets-epreuve")
    private List<Billet> billets;
    @ManyToMany(mappedBy = "epreuves", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<Participant> participants;

    @ManyToOne
    @JsonBackReference(value = "Infra-Epreuve")
    private Infrastructure idInfrastructure;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "Epreuve_Resultat_Tab",
            joinColumns = {
                    @JoinColumn(name = "Epreuve_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "Resultat_id", referencedColumnName = "id")
            }
    )
    private List<Resultats> resultats;

    public Epreuve(){

    }

    public Infrastructure getInfrastructure() {
        return idInfrastructure;
    }

    public void setInfrastructure(Infrastructure infrastructure) {
        this.idInfrastructure = infrastructure;
    }

    public Epreuve(long id, String nomEpreuve, Date date, Infrastructure infrastructure, int nombreDePlaces, List<Billet> billets, Set<Participant> participants,  List<Resultats> resultats) {
        super();
        this.id = id;
        this.nomEpreuve = nomEpreuve;
        this.date = date;
        this.idInfrastructure = infrastructure;
        this.nombreDePlaces = nombreDePlaces;
        this.billets = billets;
        this.participants = participants;
        this.resultats = resultats;
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
                ", infrastructure='" + idInfrastructure + '\'' +
                ", nombreDePlaces=" + nombreDePlaces +
                ", billets=" + billets +
                '}';
    }

    public Set<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Participant> participants) {
        this.participants = participants;
    }

    public List<Resultats> getResultats() {
        return resultats;
    }

    public void setResultats(List<Resultats> resultats) {
        this.resultats = resultats;
    }
    
}
