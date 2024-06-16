package com.example.jomiagique.Controller;

import com.example.jomiagique.Service.InfrastructureService;
import com.example.jomiagique.model.Epreuve;
import com.example.jomiagique.model.Infrastructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class InfrastrutureController {

    @Autowired
    public InfrastructureService infrastructureService;

    @RequestMapping("/getInfrastructure/{id}")
    public Infrastructure getInfrastructure(@PathVariable long id) {return infrastructureService.getInfrastructure(id);}

    @RequestMapping("/getInfrastructures")
    public List<Infrastructure> getInfrastructures(){return infrastructureService.getInfrastructure();}

    @RequestMapping(method = RequestMethod.DELETE,value = "/deleteInfrastructure/{id}" )
    public void deleteInfrastruture(@PathVariable long id){infrastructureService.deleteInfrastructure(id);}

    @RequestMapping(method = RequestMethod.POST, value = "/addInfrastructure")
    public void addInfrastruture(@RequestBody Infrastructure infrastructure){
        infrastructureService.addInfrastructure(infrastructure);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "/updateInfrastructure/{id}")
    public void updateInfrastruture(@RequestBody Infrastructure infrastructure, @PathVariable long id){
        infrastructureService.updateInfrastructure(infrastructure, id);
    }



    @RequestMapping(method = RequestMethod.GET, value = "/getEpreuveDansInfrastructure/{id}")
    public List<Epreuve> getEpreuveDansInfrastructure(@PathVariable long id){
        return infrastructureService.getEpreuveDansInfrastructure(id);
    }




}
