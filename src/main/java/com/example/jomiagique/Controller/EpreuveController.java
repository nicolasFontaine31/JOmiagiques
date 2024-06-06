package com.example.jomiagique.Controller;

import com.example.jomiagique.Service.BilletService;
import com.example.jomiagique.Service.EpreuveService;
import com.example.jomiagique.Service.SpectateurService;
import com.example.jomiagique.model.Billet;
import com.example.jomiagique.model.Epreuve;
import com.example.jomiagique.model.Participant;
import com.example.jomiagique.model.Spectateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class EpreuveController {
    @Autowired
    private EpreuveService epreuveService;

    @Autowired
    private SpectateurService spectateurService;

    @Autowired
    private BilletService billetService;

    @RequestMapping("/getEpreuves")
    public List<Epreuve> getEpreuves(){
        return epreuveService.getEpreuves();
    }

    @RequestMapping("/getEpreuve/{id}")
    public Epreuve getEpreuve(@PathVariable long id){
        return epreuveService.getEpreuve(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteEpreuve/{id}")
    public void deleteEpreuve(@PathVariable long id){
        epreuveService.deleteEpreuve(id);
    }
    //ajouter une épreuve implique que l'on ne vient que de l'organisateur
    @RequestMapping(method = RequestMethod.POST, value = "/addEpreuve")
    public void addEpreuve(@RequestBody Epreuve epreuve){
        epreuveService.addEpreuve(epreuve);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateEpreuve/{id}")
    public void updateEpreuve(@RequestBody Epreuve epreuve, @PathVariable long id){
        Epreuve epreuveTemp = epreuveService.getEpreuve(id);
        if (epreuveTemp != null){
            epreuve.setId(id);
            epreuveService.updateEpreuve(epreuve);
        }
    }

    //récupérer l'épreuve par spectateur
    @RequestMapping("/getEpreuvesBySpectateur/{idSpectateur}")
    public List<Epreuve> getEpreuveBySpectateur(@PathVariable long idSpectateur){
        Spectateur spectateur = spectateurService.getSpectateur(idSpectateur);
        List<Billet> billets = spectateur.getBillets();
        List<Epreuve> epreuves = new ArrayList<>();
        for (Billet billet : billets) {
            System.out.println("LAAAA");
            Epreuve epreuve = billet.getIdEpreuve();
            epreuves.add(epreuve);
        }
        return epreuves;
    }

    //récupérer une épreuve lié à un billet
    @RequestMapping("/getEpreuvesByIdBillet/{idBillet}")
    public Epreuve getEpreuveByIdBillet(@PathVariable long id){
        Billet billet = billetService.getBillet(id);
        if (billet != null) {
             return billet.getIdEpreuve();
        }
        return null;
    }

    //récupérer les billets pour une épreuve
    @RequestMapping("/getBilletsByIdEpreuve/{idEpreuve}")
    public List<Billet> getBilletByIdEpreuve(@PathVariable long idEpreuve){
        Epreuve epreuve = epreuveService.getEpreuve(idEpreuve);
        return epreuve.getBillets();
    }

    //récupérer les participants d'une épreuve
    @RequestMapping("/getParticpants/{idEpreuve}")
    public Set<Participant> getParticipantByIdEpreuve(@PathVariable long idEpreuve){
        Epreuve epreuve = epreuveService.getEpreuve(idEpreuve);
        return epreuve.getParticipants();
    }
}
