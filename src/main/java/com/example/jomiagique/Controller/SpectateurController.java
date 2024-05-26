package com.example.jomiagique.Controller;

import com.example.jomiagique.Service.SpectateurService;
import com.example.jomiagique.model.Spectateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpectateurController {
    @Autowired
    public SpectateurService spectateurService;

    @RequestMapping("/getSpectateur/{id}")
    public Spectateur getSpectateur(@PathVariable long id) {
        return spectateurService.getSpectateur(id);
    }

    @RequestMapping("/getSpectateurs")
    public List<Spectateur> getSpectateurs(){
        return spectateurService.getSpectateurs();
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "deleteSpectateur/{id}")
    public void deleteSpectateur(@PathVariable long id){
        spectateurService.deleteSpectateur(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addSpectateur")
    public void addSpectateur(@RequestBody Spectateur spectateur){
        spectateurService.addSpectateur(spectateur);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "updateSpectateur/{id}")
    public void updateSpectateur(@RequestBody Spectateur spectateur, @PathVariable long id){
        spectateurService.updateSpectateur(spectateur, id);
    }
}
