package com.example.jomiagique.Controller;

import com.example.jomiagique.Service.BilletService;
import com.example.jomiagique.Service.SpectateurService;
import com.example.jomiagique.model.Billet;
import com.example.jomiagique.model.Spectateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
public class SpectateurController {
    @Autowired
    public SpectateurService spectateurService;
    @Autowired
    public BilletService billetService;

    @RequestMapping("/getSpectateur/{id}")
    public Spectateur getSpectateur(@PathVariable long id) {
        return spectateurService.getSpectateur(id);
    }

    @RequestMapping("/getSpectateurs")
    public List<Spectateur> getSpectateurs() {
        return spectateurService.getSpectateurs();
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteSpectateur/{id}")
    public void deleteSpectateur(@PathVariable long id) {
        spectateurService.deleteSpectateur(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addSpectateur")
    public void addSpectateur(@RequestBody Spectateur spectateur) {
        spectateurService.addSpectateur(spectateur);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateSpectateur/{id}")
    public void updateSpectateur(@RequestBody Spectateur spectateur, @PathVariable long id) {
        spectateurService.updateSpectateur(spectateur, id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/payerBillet/{idSpectateur}/{idBillet}")
    public void payerBillet(@PathVariable long idSpectateur, @PathVariable long idBillet) {
        Billet billet = billetService.getBillet(idBillet);
        Spectateur spectateur = spectateurService.getSpectateur(idSpectateur);
        if (billet != null && spectateur != null) {
            billet.setEtat(Billet.Etat.payer);
            if (billet.getIdSpectateur().getId() == idSpectateur) {
                billetService.updateBillet(billet);
            }
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/annulerBillet/{idSpectateur}/{idBillet}")
    public ResponseEntity<String> annulerBillet(@PathVariable long idSpectateur, @PathVariable long idBillet) {
        Billet billet = billetService.getBillet(idBillet);
        Spectateur spectateur = spectateurService.getSpectateur(idSpectateur);
        if (billet != null && spectateur != null) {
            if (billet.getIdSpectateur().getId() == idSpectateur) {
                billet.setEtat(Billet.Etat.annule);
                LocalDate today = LocalDate.now();
                LocalDate dateEpreuve = billet.getDateAchat().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                long daysBetween = ChronoUnit.DAYS.between(today, dateEpreuve);
                //rembourser sans frais superieur à 7
                if(daysBetween > 7){
                    billetService.updateBillet(billet);
                    return ResponseEntity.ok("Billet annulé et remboursé sans frais.");
                }
                //50% rembourser si entre 7 à 3 jours
                else if (daysBetween <= 7 && daysBetween >= 3){
                    billetService.updateBillet(billet);
                    return ResponseEntity.ok("Billet annulé avec un remboursement de 50%.");
                }
                else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Impossible d'annuler le billet, moins de 3 jours restants.");
                }
            }
        }
        return ResponseEntity.ok("Le billet est inexistant ou ne vous apprtient pas.");
    }
}