package com.example.jomiagique.Controller;

import com.example.jomiagique.Service.BilletService;
import com.example.jomiagique.Service.SpectateurService;
import com.example.jomiagique.model.Billet;
import com.example.jomiagique.model.Spectateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BilletController {
    @Autowired
    private BilletService billetService;

    @Autowired
    private SpectateurService spectateurService;

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
    //ajouter un billet == acheter un billet pour une personne
    @RequestMapping(method = RequestMethod.POST, value = "/addBillet/{idSpectateur}")
    public void addBillet(@RequestBody Billet billet,@PathVariable long idSpectateur ){
        Spectateur spectateur = spectateurService.getSpectateur(idSpectateur);
        billet.setIdSpectateur(spectateur);
        billetService.addBillet(billet);
    }

    @RequestMapping("getBilletBySpectateur/{idSpectateur}")
    public List<Billet> getBilletBySpectateur(@PathVariable long idSpectateur){
        Spectateur spectateur = spectateurService.getSpectateur(idSpectateur);
        return spectateur.getBillets();
    }
}
