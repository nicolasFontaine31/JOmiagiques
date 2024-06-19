package com.example.jomiagique.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="participants_tab")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nom;
    private String prenom;
    private String adressemail;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "delegation-Participant")
    private Delegation idDelegation;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "particpant_epreuves_tab",
            joinColumns = @JoinColumn(name = "participant_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "epreuve_id", referencedColumnName = "id"),
            uniqueConstraints={@UniqueConstraint(columnNames={"epreuve_id", "participant_id"})})
    @JsonIgnoreProperties("participants")
    private List<Epreuve> epreuves = new ArrayList<Epreuve>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "participants")
    @JsonManagedReference(value = "participants-resultats")
    private List<Resultats> resultats;

    public Participant(){

    }

    public Participant(long id, String nom, String prenom, String adressemail, List<Epreuve> epreuves, List<Resultats> resultats, Delegation idDelegation) {
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adressemail = adressemail;
        this.epreuves = epreuves;
        this.resultats = resultats;
        this.idDelegation = idDelegation;
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

    public List<Epreuve> getEpreuves() {
        return epreuves;
    }

    public void setEpreuves(List<Epreuve> epreuves) {
        this.epreuves = epreuves;
    }

    public List<Resultats> getIdResultats() {
        return resultats;
    }

    public void setIdResultats(List<Resultats> idResultats) {
        this.resultats = idResultats;
    }

    public Delegation getIdDelegation() {
        return idDelegation;
    }

    public void setIdDelegation(Delegation idDelegation) {
        this.idDelegation = idDelegation;
    }

    public List<Resultats> getResultats() {
        return resultats;
    }

}
