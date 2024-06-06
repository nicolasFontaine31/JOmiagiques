package com.example.jomiagique.Controller;

import com.example.jomiagique.Service.EpreuveService;
import com.example.jomiagique.Service.ParticipantService;
import com.example.jomiagique.Service.ResultatService;
import com.example.jomiagique.model.Epreuve;
import com.example.jomiagique.model.Participant;
import com.example.jomiagique.model.Resultats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class ParticipantController {
    @Autowired
    private ParticipantService participantService;
    @Autowired
    private EpreuveService epreuveService;
    @Autowired
    private ResultatService resultatService;

    @RequestMapping("/getParticipants")
    public Set<Participant> getParticipants(){
        return participantService.getParticipants();
    }

    @RequestMapping("/getParticipant/{idParticipant}")
    public Participant getParticipant(@PathVariable long idParticipant){
        return participantService.getParticipant(idParticipant);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addParticipant")
    public void addParticipant(@RequestBody Participant participant){
        participantService.addParticipant(participant);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteParticipant/{idParticipant}")
    public void deleteParticipant(@PathVariable long idParticipant){
        participantService.deleteParticipant(idParticipant);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateParticipant/{idParticipant}")
    public void updateParticipant(@RequestBody Participant participant, @PathVariable long idParticipant){
        Participant participanttemp = participantService.getParticipant(idParticipant);
        if (participanttemp != null){
            participant.setId(idParticipant);
            participantService.updateParticipant(participant);
        }
    }

    //ajouter une épreuve à un participant en fonction de la date de l'épreuve
    //envoyer des messages d'erreurs
    @RequestMapping(method = RequestMethod.PUT, value = "/addEpreuveToParticipant/{idParticipant}/{idEpreuve}")
    public void addEpreuveToParticipant(@PathVariable long idEpreuve, @PathVariable long idParticipant) {
        Epreuve epreuve = epreuveService.getEpreuve(idEpreuve);
        LocalDate today = LocalDate.now();
        LocalDate dateEpreuve = epreuve.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long daysBetween = ChronoUnit.DAYS.between(today, dateEpreuve);
        if (daysBetween > 10) {
            if (epreuve != null) {
                Participant participant = participantService.getParticipant(idParticipant);
                if (participant != null) {
                    Set<Epreuve> epreuves = participant.getEpreuves();
                    epreuves.add(epreuve);
                    participant.setEpreuves(epreuves);
                    participantService.addEpreuveToParticipant(participant);
                }
            }
        }
    }

    //désengager avant 10 jours rien sinon noté forfait et pas possible d'être remplacé
    //rajouter forfait quand résultat sera mappé
    @RequestMapping(method = RequestMethod.PUT, value = "/desengagerEpreuve/{idParticipant}/{idEpreuve}")
    public void desengagerEpreuveToParticipant(@PathVariable long idParticipant, @PathVariable long idEpreuve){
        Epreuve epreuve = epreuveService.getEpreuve(idEpreuve);
        LocalDate today = LocalDate.now();
        LocalDate dateEpreuve = epreuve.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        long daysBetween = ChronoUnit.DAYS.between(today, dateEpreuve);
        if (daysBetween > 10) {
            if (epreuve != null) {
                Participant participant = participantService.getParticipant(idParticipant);
                if (participant != null) {
                    Set<Epreuve> epreuves = participant.getEpreuves();
                    epreuves.remove(epreuve);
                    participant.setEpreuves(epreuves);
                    participantService.desengagerEpreuveToParticipant(participant);
                }
            }
        }
    }

    //On ajoute un participant et son numéro de position dans le body
    //ajouter le resultat au participant
    @RequestMapping(method = RequestMethod.PUT, value = "/addResultatByParticipant/{idResultat}/{idParticipant}")
    public void addResultatByParticipant(@PathVariable long idResultat, @PathVariable long idParticipant){
        Participant participant = participantService.getParticipant(idParticipant);
        if (participant != null){
            Resultats resultat = resultatService.getResultat(idResultat);
            if (resultat != null){
                List<Resultats>resultats = participant.getIdResultats();
                resultats.add(resultat);
                participant.setIdResultats(resultats);
                participantService.updateParticipant(participant);
            }
        }
    }

    //get resultat d'un participant
    @RequestMapping("/getResultatsByParticipant/{idParticipant}")
    public List<Resultats> getResultatsByParticipant(@PathVariable long idParticipant){
        Participant participant = participantService.getParticipant(idParticipant);
        if (participant != null){
            return participant.getIdResultats();
        }
        return null;
    }






}
