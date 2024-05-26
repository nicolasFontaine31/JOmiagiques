package com.example.jomiagique.Controller;

import com.example.jomiagique.Service.OrganisateurService;
import com.example.jomiagique.model.Organisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrganisateurController {

    @Autowired
    private OrganisateurService organisateurService;

    @RequestMapping("/getOrga/{id}")
   public Organisateur getOrganisateur(@PathVariable long id){
        return organisateurService.getOrganisateur(id);
   }
   @RequestMapping("/getOrgas")
    public List<Organisateur> getOrganisateurs(){
        return organisateurService.getOrganisateurs();
   }
   @RequestMapping(method = RequestMethod.DELETE, value ="/delete/{id}")//Delete général car peut être orga ou controleur
    public void deleteOrganisateur(@PathVariable long id){
        organisateurService.deleteOrganisateur(id);
   }

   @RequestMapping(method = RequestMethod.POST,value = "/add")
   public void addOrganisateur(@RequestBody Organisateur organisateur){
        organisateurService.addOrganisateur(organisateur);
   }
   @RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
    public void updateOrganisateur(@RequestBody Organisateur organisateur, @PathVariable long id){
        organisateurService.updateOrganisateur(organisateur, id);
   }
}
