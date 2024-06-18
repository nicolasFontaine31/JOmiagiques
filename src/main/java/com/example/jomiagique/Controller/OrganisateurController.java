package com.example.jomiagique.Controller;

import com.example.jomiagique.Service.BilletService;
import com.example.jomiagique.Service.EpreuveService;
import com.example.jomiagique.Service.OrganisateurService;
import com.example.jomiagique.model.Billet;
import com.example.jomiagique.model.Epreuve;
import com.example.jomiagique.model.Organisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrganisateurController {

    @Autowired
    private OrganisateurService organisateurService;
    @Autowired
    private BilletService billetService;
    @Autowired
    private EpreuveService epreuveService;

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
   public ResponseEntity<String> addOrganisateur(@RequestBody Organisateur organisateur){
        organisateurService.addOrganisateur(organisateur);
       return ResponseEntity.ok("L'organisateur a été créé avec succés.");
   }
   @RequestMapping(method = RequestMethod.PUT, value = "/update/{id}")
    public void updateOrganisateur(@RequestBody Organisateur organisateur, @PathVariable long id){
        organisateurService.updateOrganisateur(organisateur, id);
   }

   @RequestMapping(method = RequestMethod.PUT, value = "/validerBillet/{idControlleur}/{idBillet}")
    public ResponseEntity<String> validerBillet(@PathVariable long idControlleur, @PathVariable long idBillet){
        Organisateur organisateur = organisateurService.getOrganisateur(idControlleur);
        if (organisateur != null && organisateur.getRole()== Organisateur.role.controlleur){
            Billet billet = billetService.getBillet(idBillet);
            //billet est payé
            if (billet != null && billet.getEtat()== Billet.Etat.payer){
                billet.setEtat(Billet.Etat.valider);
                billetService.updateBillet(billet);
                return ResponseEntity.ok("Le billet a été validé.");
            }
            else{
                // donc le billet n'est pas payé ou deja utilisé donc message erreur
                return ResponseEntity.ok("Impossible de valider le billet.L'état du billet ne correspond pas");
            }
        }
        return ResponseEntity.ok("Erreur pour la valider le billet.");
   }

    //A TESTER!!!!!!!!!!!!!!
    //pour chaque épreuve compter nombres de billets et nombre de participant
    // on retourne pour chaque épreuve le nombre de billets et le pourcentage d'occupation
    //et on retourne le nombre de partivcipants
   @RequestMapping(value = "/getStatistiques/{idOrganisateur}")
    public ResponseEntity<String> getStatistiques(@PathVariable long idOrganisateur){
       Organisateur organisateur = organisateurService.getOrganisateur(idOrganisateur);
       if (organisateur != null && organisateur.getRole()== Organisateur.role.organisateur){
           StringBuilder statistiques = new StringBuilder();
           List<Epreuve> epreuves = epreuveService.getEpreuves();
           for(Epreuve epreuve : epreuves){
               String nomEpreuve = epreuve.getNomEpreuve();
               int nbPlaces = epreuve.getBillets().size();
               int nbParticipants = epreuve.getParticipants().size();
               double tauxOccupation = (double) nbPlaces / epreuve.getNombreDePlaces() * 100.0;
               String ligneStatistique = String.format("Nom épreuve : %s, nombre de places : %d, taux d'occupation : %.2f%%, nombre de participants : %d;",
                       nomEpreuve, nbPlaces, tauxOccupation, nbParticipants);
               statistiques.append(ligneStatistique).append("\n");
           }
           return ResponseEntity.ok("voila les stats : "+ statistiques );
       }
        return ResponseEntity.ok("Les statistiques ne sont accessibles que par l'organisateur. ");
   }
}
