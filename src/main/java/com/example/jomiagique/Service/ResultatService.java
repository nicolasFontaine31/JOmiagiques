package com.example.jomiagique.Service;

import com.example.jomiagique.model.Resultats;
import com.example.jomiagique.repository.ResultatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ResultatService {
    @Autowired
    private ResultatRepository resultatRepository;

    public List<Resultats> getResultats() {
        List<Resultats> resultats = new ArrayList<>();
        resultatRepository.findAll().forEach(resultat ->{
            resultats.add(resultat);
        });
        return resultats;
    }

    public Resultats getResultat(long id){
        return resultatRepository.findById(id).orElse(null);
    }

    public void addResultat(Resultats resultat) {
        resultatRepository.save(resultat);
    }

    public void updateResultat(Resultats resultat) {
        resultatRepository.save(resultat);
    }

    //public Resultats getResultatByEpreuveAndParticipant(long idEpreuve, long idParticipant) {
    //    Optional<Resultats> optionalResultat = resultatRepository.findByEpreuves_IdAndParticipants_Id(idEpreuve, idParticipant);
    //    return optionalResultat.orElse(null);
    //}
}
