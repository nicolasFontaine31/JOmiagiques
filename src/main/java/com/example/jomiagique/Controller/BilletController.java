package com.example.jomiagique.Controller;

import com.example.jomiagique.Service.BilletService;
import com.example.jomiagique.Service.EpreuveService;
import com.example.jomiagique.Service.SpectateurService;
import com.example.jomiagique.model.Billet;
import com.example.jomiagique.model.Epreuve;
import com.example.jomiagique.model.Spectateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BilletController {
    @Autowired
    private BilletService billetService;

    @Autowired
    private SpectateurService spectateurService;

    @Autowired
    private EpreuveService epreuveService;

    @RequestMapping("/getBillets")
    public List<Billet> getBillets(){
        return billetService.getBillets();
    }
    @RequestMapping("/getBillet/{id}")
    public Billet getBillet(@PathVariable long id){
        return billetService.getBillet(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteBillet/{id}")
    public void deleteBillet(@PathVariable long id){
        billetService.deleteBillet(id);
    }

    //ajouter un billet == réserver un billet pour une personne pour une épreuve
    @RequestMapping(method = RequestMethod.POST, value = "/addBillet/{idSpectateur}/{idEpreuve}")
    public ResponseEntity<String> addBillet(@RequestBody Billet billet, @PathVariable long idSpectateur, @PathVariable long idEpreuve ) {
        Spectateur spectateur = spectateurService.getSpectateur(idSpectateur);
        billet.setIdSpectateur(spectateur);
        billet.setEtat(Billet.Etat.reserver);
        Epreuve epreuve = epreuveService.getEpreuve(idEpreuve);
        if (epreuve != null && spectateur != null){
            //Si spectateur possède 4 billets interdit
            if (spectateurService.compteurBilletByEpreuve(spectateur, idEpreuve) == 4) {
                return ResponseEntity.ok("Impossible vous possédez déjà 4 billets pour cette épreuve.");
            }
            else{
                billet.setIdEpreuve(epreuve);
                if (epreuve.getBillets().size()+1<=epreuve.getNombreDePlaces()){
                    billetService.addBillet(billet);
                    return ResponseEntity.ok("Votre billet a été ajouté.");
                }
                else{
                    return ResponseEntity.ok("Il n'y a plus de place pour cette épreuve.");
                }
            }
        }
        if (epreuve != null) {
        }
        return ResponseEntity.ok("erreur");
    }


    @RequestMapping("/getBilletBySpectateur/{idSpectateur}")
    public List<Billet> getBilletBySpectateur(@PathVariable long idSpectateur){
        Spectateur spectateur = spectateurService.getSpectateur(idSpectateur);
        return spectateur.getBillets();
    }
}
