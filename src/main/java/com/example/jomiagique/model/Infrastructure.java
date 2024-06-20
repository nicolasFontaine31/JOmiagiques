package com.example.jomiagique.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitialize","handler","epreuves"})
public class Infrastructure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String adresse;
    private int capacite;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idInfrastructure")
    @JsonManagedReference(value = "infra-epreuve")
    private List<Epreuve> epreuves;

    public Infrastructure() {}

    public Infrastructure(long id, String name,String adresse, int capacite) {
        super();
        this.id = id;
        this.name = name;
        this.adresse = adresse;
        this.capacite = capacite;
        this.epreuves = epreuves;
    }


    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Epreuve> getEpreuves() {
        return epreuves;
    }

    public void setEpreuves(List<Epreuve> epreuves) {
        this.epreuves = epreuves;
    }
}

