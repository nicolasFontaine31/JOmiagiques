package com.example.jomiagique.Controller;

import com.example.jomiagique.Service.*;
import com.example.jomiagique.model.*;
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
    private InfrastructureService infrastructureService;
    @Autowired
    private BilletService billetService;
    @Autowired
    private ResultatService resultatService;
    @Autowired
    private OrganisateurService organisateurService;

    //peut être faire que les epreuves dispo ? en fonction de la date ? à vérifier
    @RequestMapping("/getEpreuves")
    public List<Epreuve> getEpreuves(){
        return epreuveService.getEpreuves();
    }

    @RequestMapping("/getEpreuve/{id}")
    public Epreuve getEpreuve(@PathVariable long id){
        return epreuveService.getEpreuve(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteEpreuve/{idEpreuve}/{idOrganisateur}")
    public void deleteEpreuve(@PathVariable long idEpreuve, @PathVariable long idOrganisateur){
        Organisateur organisateur = organisateurService.getOrganisateur(idOrganisateur);
        if(organisateur != null && organisateur.getRole()== Organisateur.role.organisateur){
            epreuveService.deleteEpreuve(idEpreuve);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addEpreuve/{idInfrastructure}/{idOrganisateur}")
    public void addEpreuve(@RequestBody Epreuve epreuve,@PathVariable long idInfrastructure, @PathVariable long idOrganisateur){
        Infrastructure infrastructure = infrastructureService.getInfrastructure(idInfrastructure);
        if(infrastructure != null){
            Organisateur organisateur = organisateurService.getOrganisateur(idOrganisateur);
            if(organisateur != null && organisateur.getRole()== Organisateur.role.organisateur){
                epreuve.setInfrastructure(infrastructure);
                epreuveService.addEpreuve(epreuve);
            }
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateEpreuve/{idEpreuve}/{idOrganisateur}")
    public void updateEpreuve(@RequestBody Epreuve epreuve, @PathVariable long idEpreuve, @PathVariable long idOrganisateur){
        Epreuve epreuveTemp = epreuveService.getEpreuve(idEpreuve);
        if (epreuveTemp != null){
            Organisateur organisateur = organisateurService.getOrganisateur(idOrganisateur);
            if(organisateur != null && organisateur.getRole()== Organisateur.role.organisateur) {
                epreuve.setId(idEpreuve);
                epreuveService.updateEpreuve(epreuve);
            }
        }
    }

    //récupérer l'épreuve par spectateur
    @RequestMapping("/getEpreuvesBySpectateur/{idSpectateur}")
    public List<Epreuve> getEpreuveBySpectateur(@PathVariable long idSpectateur){
        Spectateur spectateur = spectateurService.getSpectateur(idSpectateur);
        List<Billet> billets = spectateur.getBillets();
        List<Epreuve> epreuves = new ArrayList<>();
        for (Billet billet : billets) {
            Epreuve epreuve = billet.getIdEpreuve();
            epreuves.add(epreuve);
        }
        return epreuves;
    }

    //récupérer une épreuve lié à un billet
    @RequestMapping("/getEpreuvesByIdBillet/{idBillet}")
    public Epreuve getEpreuveByIdBillet(@PathVariable long idBillet){
        Billet billet = billetService.getBillet(idBillet);
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
    public List<Participant> getParticipantByIdEpreuve(@PathVariable long idEpreuve){
        Epreuve epreuve = epreuveService.getEpreuve(idEpreuve);
        return epreuve.getParticipants();
    }

    @RequestMapping("/addResultatByEpreuve/{idEpreuve}/{idResultat}")
    public void addResultatByEpreuve(@PathVariable long idResultat, @PathVariable long idEpreuve){
        Resultats resultat = resultatService.getResultat(idResultat);
        if(resultat != null){
            Epreuve epreuve = epreuveService.getEpreuve(idEpreuve);
            if(epreuve != null){
                List<Resultats> resultats = epreuve.getResultats();
                resultats.add(resultat);
                epreuve.setResultats(resultats);
                epreuveService.updateEpreuve(epreuve);
            }
        }
    }


}
