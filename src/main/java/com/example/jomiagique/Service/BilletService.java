package com.example.jomiagique.Service;

import com.example.jomiagique.model.Billet;
import com.example.jomiagique.model.Epreuve;
import com.example.jomiagique.model.Spectateur;
import com.example.jomiagique.repository.BilletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BilletService {
    @Autowired
    private BilletRepository billetRepository;
    @Autowired
    private SpectateurService spectateurService;

    public List<Billet> getBillets(){
        List<Billet> billets = new ArrayList<>();
        billetRepository.findAll().forEach(billet -> {
            billets.add(billet);
        });
        return billets;
    }

    public Billet getBillet(long id){
        return billetRepository.findById(id).orElse(null);
    }

    public void deleteBillet(long id){
        billetRepository.deleteById(id);
    }
    public void addBillet(Billet billet){
        billetRepository.save(billet);
    }

    public void updateBillet(Billet billet){
        billetRepository.save(billet);
    }


    public String reserverBillet(Billet billet, Spectateur spectateur, Epreuve epreuve) {
        if (epreuve == null || spectateur == null) {
            return "Erreur: Epreuve ou Spectateur introuvable";
        }

        if (spectateurService.compteurBilletByEpreuve(spectateur, epreuve.getId()) >= 4) {
            return "Impossible: vous possédez déjà 4 billets pour cette épreuve.";
        }

        if (epreuve.getBillets().size() >= epreuve.getNombreDePlaces()) {
            return "Il n'y a plus de place pour cette épreuve.";
        }

        billet.setIdSpectateur(spectateur);
        billet.setIdEpreuve(epreuve);
        billet.setEtat(Billet.Etat.reserver);
        addBillet(billet);
        return "Votre billet a été ajouté.";
    }
}
