package com.example.jomiagique.Controller;

import com.example.jomiagique.Service.DelegationService;
import com.example.jomiagique.Service.OrganisateurService;
import com.example.jomiagique.model.Delegation;
import com.example.jomiagique.model.Organisateur;
import com.example.jomiagique.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DelegationController {

    @Autowired
    public DelegationService delegationService;
    @Autowired
    public OrganisateurService organisateurService;


    @RequestMapping("/getDelegation/{id}")
    public Delegation getDelegation(@PathVariable long id) {return delegationService.getDelegation(id);}

    @RequestMapping("/getDelegations")
    public List<Delegation> getDelegations(){return delegationService.getDelegations();}

    @RequestMapping(method = RequestMethod.DELETE,value = "/deleteDelegation/{id}/{idOrganisateur}" )
    public void deleteDelegation(@PathVariable long id, @PathVariable long idOrganisateur){
        Organisateur organisateur = organisateurService.getOrganisateur(idOrganisateur);
        if (organisateur != null) {
            if (organisateur.getRole() == Organisateur.role.organisateur) {
                delegationService.deleteDelegation(id);
                ResponseEntity.ok("la délégation a été supprimée");
            }
        }
    }
    @RequestMapping(method = RequestMethod.POST, value = "/addDelegation/{idOrganisateur}")
    public void addDelegation(@RequestBody Delegation delegation,@PathVariable long idOrganisateur){
        Organisateur organisateur = organisateurService.getOrganisateur(idOrganisateur);
        if (organisateur != null){
            if(organisateur.getRole() == Organisateur.role.organisateur){
                delegationService.addDelegation(delegation);
                ResponseEntity.ok("la délégation a été ajoutée");
            }
        }
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/updateDelegation/{id}/{idOrganisateur}")
    public void updateDelegation(@RequestBody Delegation delegation, @PathVariable long id){
        delegationService.updateDelegation(delegation, id);
    }



    @RequestMapping(method = RequestMethod.GET, value = "/getParticipantDansDelegation/{id}")
    public List<Participant> getParticipantDansDelegation(@PathVariable long id){
        return delegationService.getParticipantDansDelegation(id);
    }
}
