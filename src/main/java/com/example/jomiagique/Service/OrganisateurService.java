package com.example.jomiagique.Service;

import com.example.jomiagique.model.Organisateur;
import com.example.jomiagique.model.Spectateur;
import com.example.jomiagique.repository.OrganisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class OrganisateurService {
    @Autowired
    private OrganisateurRepository organisateurRepository;

    public List<Organisateur> getOrganisateurs() {
        List<Organisateur> organisateurs = new ArrayList<>();
        organisateurRepository.findAll().forEach(organisateur -> {
            organisateurs.add(organisateur);
        });
        return organisateurs;
    }

    public Organisateur getOrganisateur(long id) {
        return organisateurRepository.findById(id).orElse(null);
    }

    public void deleteOrganisateur(long id) {
        organisateurRepository.deleteById(id);
    }

    public void addOrganisateur(Organisateur organisateur) {
        organisateurRepository.save(organisateur);
    }

    public void updateOrganisateur(Organisateur organisateur, long id) {
        Organisateur organisateurtemp = organisateurRepository.findById(id).get();
        if (organisateurtemp != null) {
            organisateur.setID(id);
            organisateurRepository.save(organisateur);
        }
    }
}
