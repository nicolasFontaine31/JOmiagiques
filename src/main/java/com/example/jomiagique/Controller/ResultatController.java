package com.example.jomiagique.Controller;

import com.example.jomiagique.Service.EpreuveService;
import com.example.jomiagique.Service.ParticipantService;
import com.example.jomiagique.Service.ResultatService;
import com.example.jomiagique.model.Epreuve;
import com.example.jomiagique.model.Participant;
import com.example.jomiagique.model.Resultats;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(method = RequestMethod.POST, value = "/addResultat")
    public void addResultat(@RequestBody Resultats resultat){
        resultatService.addResultat(resultat);
    }

    //add un res par epreuve
    //@RequestMapping(method = RequestMethod.POST,value = "/addResultatByEpreuve/{idEpreuve}")
    //public void addResultatByEpreuve(@RequestBody Resultats resultat, @PathVariable long idEpreuve){
    //    Epreuve epreuve = epreuveService.getEpreuve(idEpreuve);
    //    if (epreuve != null){
    //        resultat.setEpreuve(epreuve);
    //        resultatService.addResultat(resultat);
    //    }
    //}

    //update res par epreuve
    //@RequestMapping(method = RequestMethod.PUT,value = "/updateResultatByEpreuve/{idEpreuve}")
    //public void updateResultatByEpreuve(@RequestBody Resultats resultat, @PathVariable long idEpreuve) {
        //    Epreuve epreuve = epreuveService.getEpreuve(idEpreuve);
        //    if (epreuve != null){
                //resultat.setEpreuve(epreuve);
                //resultatService.updateResultat(resultat);
    //}
    //}

    //update res seul
    @RequestMapping(method = RequestMethod.PUT, value = "/updateResultat/{idResultat}")
    public void updateResultat(@RequestBody Resultats resultat, @PathVariable long idResultat){
        Resultats resultattemp = resultatService.getResultat(idResultat);
        if (resultattemp != null){
            resultat.setId(idResultat);
        }
        resultatService.updateResultat(resultat);
    }
    //update Res par participant



}
