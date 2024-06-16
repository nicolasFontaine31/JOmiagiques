package com.example.jomiagique.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Spectateur {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String nom;
    private String prenom;
    private String adressemail;
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "idSpectateur")
    @JsonManagedReference(value = "spectateur-billets")
    private List<Billet> billets;

    public Spectateur(long id, String nom, String prenom, String adressemail) {
        super();
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adressemail = adressemail;
    }
    public Spectateur() {}

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

    public List<Billet> getBillets() {
        return billets;
    }

    public void setBillets(List<Billet> billets) {
        this.billets = billets;
    }

    @Override
    public String toString() {
        return "Spectateur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", adressemail='" + adressemail + '\'' +
                ", billets=" + billets +
                '}';
    }
}
