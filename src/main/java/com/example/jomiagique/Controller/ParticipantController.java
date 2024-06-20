package com.example.jomiagique.Controller;

import com.example.jomiagique.Service.*;
import com.example.jomiagique.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private OrganisateurService organisateurService;
    @Autowired
    private DelegationService delegationService;

    @RequestMapping("/getParticipants")
    public Set<Participant> getParticipants(){
        return participantService.getParticipants();
    }

    @RequestMapping("/getParticipant/{idParticipant}")
    public Participant getParticipant(@PathVariable long idParticipant){
        return participantService.getParticipant(idParticipant);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addParticipant/{idDelegation}/{idOrganisateur}")
    public void addParticipant(@RequestBody Participant participant,@PathVariable long idDelegation, @PathVariable long idOrganisateur){
        Organisateur organisateur = organisateurService.getOrganisateur(idOrganisateur);
        if (organisateur != null) {
            if (organisateur.getRole() == Organisateur.role.organisateur) {
                Delegation delegation = delegationService.getDelegation(idDelegation);
                if (delegation != null){
                    participant.setIdDelegation(delegation);
                    participantService.addParticipant(participant);
                }
            }
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteParticipant/{idParticipant}/{idOrganisateur}")
    public void deleteParticipant(@PathVariable long idParticipant, @PathVariable long idOrganisateur){
        Organisateur organisateur = organisateurService.getOrganisateur(idOrganisateur);
        if (organisateur != null) {
            if (organisateur.getRole() == Organisateur.role.organisateur) {
                participantService.deleteParticipant(idParticipant);
            }
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateParticipant/{idParticipant}")
    public void updateParticipant(@RequestBody Participant participant, @PathVariable long idParticipant){
        Participant participanttemp = participantService.getParticipant(idParticipant);
        if (participanttemp != null){
            participant.setId(idParticipant);
            participantService.updateParticipant(participant);
        }
    }

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
                    List<Epreuve> epreuves = participant.getEpreuves();
                    epreuves.add(epreuve);
                    participant.setEpreuves(epreuves);
                    participantService.addEpreuveToParticipant(participant);
                }
            }
        }
    }

    //désengager avant 10 jours rien sinon noté forfait et pas possible d'être remplacé
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
                    List<Epreuve> epreuves = participant.getEpreuves();
                    epreuves.remove(epreuve);
                    participant.setEpreuves(epreuves);
                    participantService.desengagerEpreuveToParticipant(participant);
                }
            }
        }
        else {
            if (epreuve != null) {
                Participant participant = participantService.getParticipant(idParticipant);
                if (participant != null) {
                    Resultats resultat = resultatService.getResultatByEpreuveAndParticipant(idEpreuve,idParticipant);
                    resultat.setPosition(Resultats.position.forfait);
                    resultatService.updateResultat(resultat);
                }
            }
        }
    }

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

    @RequestMapping("/getClassementDelegation/{idParticipant}")
    public ResponseEntity<String> classementDelegation(@PathVariable long idParticipant){
        Participant participant  = participantService.getParticipant(idParticipant);
        int place = 1;
        long idDelegation = participant.getIdDelegation().getId();
        if(participant != null) {
            List<Delegation> delegations = delegationService.getClassementGeneral();
            for (Delegation delegation : delegations) {
                if (delegation.getId() == idDelegation) {
                    return ResponseEntity.ok("Votre délégation est à la " + place + " place");
                }
                place++;
            }
        }
        return ResponseEntity.ok("Impossible de toruver le participant");
    }

}
