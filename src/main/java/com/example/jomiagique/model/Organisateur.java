package com.example.jomiagique.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Organisateur {
    private enum role{
        organisateur, controlleur
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    private String nom;
    private String prenom;
    private String mail;
    private role role;

    public Organisateur(int ID, String nom, String prenom, String mail, role role) {
        super();
        this.ID = ID;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.role = role;
    }

    public Organisateur() {

    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public role getRole() {
        return role;
    }

    public void setRole(role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Organisateur{" +
                "ID=" + ID +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mail='" + mail + '\'' +
                ", role=" + role +
                '}';
    }
}
