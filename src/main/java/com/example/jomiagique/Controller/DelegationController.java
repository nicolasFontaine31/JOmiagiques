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
    public ResponseEntity<String> deleteDelegation(@PathVariable long id, @PathVariable long idOrganisateur){
        Organisateur organisateur = organisateurService.getOrganisateur(idOrganisateur);
        if (organisateur != null && organisateur.getRole() == Organisateur.role.organisateur) {
            delegationService.deleteDelegation(id);
            return ResponseEntity.ok("la délégation a été supprimée");
        }
        return ResponseEntity.status(403).body("Permission refusée");
    }
    @RequestMapping(method = RequestMethod.POST, value = "/addDelegation/{idOrganisateur}")
    public ResponseEntity<String> addDelegation(@RequestBody Delegation delegation,@PathVariable long idOrganisateur){
        Organisateur organisateur = organisateurService.getOrganisateur(idOrganisateur);
        if (organisateur != null && organisateur.getRole() == Organisateur.role.organisateur){
            delegationService.addDelegation(delegation);
            return ResponseEntity.ok("la délégation a été ajoutée");
        }
        return ResponseEntity.status(403).body("Permission refusée");
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/updateDelegation/{id}/{idOrganisateur}")
    public ResponseEntity<String> updateDelegation(@RequestBody Delegation delegation, @PathVariable long id, @PathVariable long idOrganisateur){
        Organisateur organisateur = organisateurService.getOrganisateur(idOrganisateur);
        if (organisateur != null && organisateur.getRole() == Organisateur.role.organisateur) {
            delegationService.updateDelegation(delegation, id);
            return ResponseEntity.ok("la délégation a été ajoutée");
        }
        return ResponseEntity.status(403).body("Permission refusée");
    }



    @RequestMapping(method = RequestMethod.GET, value = "/getParticipantDansDelegation/{id}")
    public List<Participant> getParticipantDansDelegation(@PathVariable long id){
        return delegationService.getParticipantDansDelegation(id);
    }
}
