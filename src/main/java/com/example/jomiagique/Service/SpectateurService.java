package com.example.jomiagique.Service;

import com.example.jomiagique.model.Billet;
import com.example.jomiagique.model.Spectateur;
import com.example.jomiagique.repository.SpectateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SpectateurService {

    @Autowired
    private SpectateurRepository spectateurRepository;

    public Spectateur getSpectateur(long id) {
        return spectateurRepository.findById(id).orElse(null);
    }
    public List<Spectateur> getSpectateurs() {
        List<Spectateur> spectateurs = new ArrayList<>();
        spectateurRepository.findAll().forEach(spectateur -> {
            spectateurs.add(spectateur);
        });
        return spectateurs;
    }

    public void deleteSpectateur(long id) {
        spectateurRepository.deleteById(id);
    }

    public void addSpectateur(Spectateur spectateur) {
        spectateurRepository.save(spectateur);
    }

    public void updateSpectateur(Spectateur spectateur, long id) {
        Spectateur spectateurtemp = spectateurRepository.findById(id).get();
        if (spectateurtemp != null){
            spectateur.setId(id);
            spectateurRepository.save(spectateur);
        }
    }

    //compte les billets, Un spectateur ne peut dépasser 4 billets
    public int compteurBilletByEpreuve(Spectateur spectateur, long idEpreuve){
        int count = 0;
        if (spectateur.getBillets() != null){
            for (Billet billet : spectateur.getBillets()) {
                if (billet.getIdEpreuve().getId() == idEpreuve){
                    count++;
                }
            }
        }
        return count;
    }

}
