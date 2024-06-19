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



    @PostMapping("/addBillet/{idSpectateur}/{idEpreuve}")
    public ResponseEntity<String> addBillet(@RequestBody Billet billet, @PathVariable long idSpectateur, @PathVariable long idEpreuve) {
        Spectateur spectateur = spectateurService.getSpectateur(idSpectateur);
        Epreuve epreuve = epreuveService.getEpreuve(idEpreuve);
        String response = billetService.reserverBillet(billet, spectateur, epreuve);
        return ResponseEntity.ok(response);
    }


    @RequestMapping("/getBilletBySpectateur/{idSpectateur}")
    public List<Billet> getBilletBySpectateur(@PathVariable long idSpectateur){
        Spectateur spectateur = spectateurService.getSpectateur(idSpectateur);
        return spectateur.getBillets();
    }
}
