package com.example.jomiagique.Controller;

import com.example.jomiagique.Service.DelegationService;
import com.example.jomiagique.Service.EpreuveService;
import com.example.jomiagique.Service.ParticipantService;
import com.example.jomiagique.Service.ResultatService;
import com.example.jomiagique.model.Delegation;
import com.example.jomiagique.model.Epreuve;
import com.example.jomiagique.model.Participant;
import com.example.jomiagique.model.Resultats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResultatController {
    @Autowired
    private ResultatService resultatService;
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private EpreuveService epreuveService;
    @Autowired
    private DelegationService delegationService;

    @RequestMapping("/getResultats")
    public List<Resultats> getResultats(){
        return resultatService.getResultats();
    }

    @RequestMapping("/getResultat/{idResultat}")
    public Resultats getResultat(@PathVariable long idResultat){
        return resultatService.getResultat(idResultat);
    }

    //add un res par epreuve et participant
    @RequestMapping(method = RequestMethod.POST,value = "/addResultatByEpreuve/{idEpreuve}/{idParticipant}")
    public ResponseEntity<String> addResultatByEpreuve(@RequestBody Resultats resultat, @PathVariable long idEpreuve, @PathVariable long idParticipant){
        Epreuve epreuve = epreuveService.getEpreuve(idEpreuve);
        Participant participant = participantService.getParticipant(idParticipant);
        if (epreuve != null && participant != null){
            resultat.setEpreuves(epreuve);
            resultat.setParticipants(participant);
            Delegation delegation = participant.getIdDelegation();
            if (resultat.getPosition() == Resultats.position.premier){
                delegation.setNbMedaillesOr(delegation.getNbMedaillesOr()+1);
            }
            else if (resultat.getPosition() == Resultats.position.deuxieme){
                delegation.setNbMedaillesArgent(delegation.getNbMedaillesArgent()+1);
            }
            else if (resultat.getPosition() == Resultats.position.troisieme){
                delegation.setNbMedaillesBronze(delegation.getNbMedaillesBronze()+1);
            }
            delegationService.updateDelegationProgram(delegation);
            resultatService.addResultat(resultat);
            return ResponseEntity.ok("Votre résultat a été ajouté.");
        }
        return ResponseEntity.ok("Impossible d'ajouter le résultat. Epreuve ou participant inexistant.");
    }


    @RequestMapping(method = RequestMethod.PUT,value = "/updateResultatByEpreuve/{idResultat}")
    public void updateResultatByEpreuve(@RequestBody Resultats resultat, @PathVariable long idResultat) {
        Resultats resultats = resultatService.getResultat(idResultat);
        if (resultats != null){
            Participant participant = resultats.getParticipant();
            if (participant != null){
                Delegation delegation = participant.getIdDelegation();
                if (resultat.getPosition() == Resultats.position.premier){
                    delegation.setNbMedaillesOr(delegation.getNbMedaillesOr()+1);
                }
                else if (resultat.getPosition() == Resultats.position.deuxieme){
                    delegation.setNbMedaillesArgent(delegation.getNbMedaillesArgent()+1);
                }
                else if (resultat.getPosition() == Resultats.position.troisieme){
                    delegation.setNbMedaillesBronze(delegation.getNbMedaillesBronze()+1);
                }
                delegationService.updateDelegationProgram(delegation);
                resultatService.updateResultat(resultat);
            }
        }
    }

}
