package com.example.jomiagique.Controller;

import com.example.jomiagique.Service.EpreuveService;
import com.example.jomiagique.Service.ParticipantService;
import com.example.jomiagique.Service.ResultatService;
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
            resultatService.addResultat(resultat);
            return ResponseEntity.ok("Votre résultat a été ajouté.");
        }
        return ResponseEntity.ok("Impossible d'ajouter le résultat. Epreuve ou participant inexistant.");
    }


    @RequestMapping(method = RequestMethod.PUT,value = "/updateResultatByEpreuve/{idResultat}")
    public void updateResultatByEpreuve(@RequestBody Resultats resultat, @PathVariable long idResultat) {
        Resultats resultats = resultatService.getResultat(idResultat);
        if (resultats != null){
            resultatService.updateResultat(resultat);
        }
    }

    //update Res par participant


    //avoir les résultats par rapport à un participant et une epreuve
    //@GetMapping("/getresultatsByIdEpreuveAndIdParticipant/{idEpreuve}/{idParticipant}")
    //public Resultats getResultatByEpreuveAndParticipant(@PathVariable long idEpreuve, @PathVariable long idParticipant) {
    //    return resultatService.getResultatByEpreuveAndParticipant(idEpreuve, idParticipant);
    //}



}
