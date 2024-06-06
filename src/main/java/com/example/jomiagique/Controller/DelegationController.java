package com.example.jomiagique.Controller;

import com.example.jomiagique.Service.DelegationService;
import com.example.jomiagique.model.Delegation;
import com.example.jomiagique.model.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DelegationController {

    @Autowired
    public DelegationService delegationService;


    @RequestMapping("/getDelegation/{id}")
    public Delegation getDelegation(@PathVariable long id) {return delegationService.getDelegation(id);}

    @RequestMapping("/getDelegations")
    public List<Delegation> getDelegations(){return delegationService.getDelegations();}

    @RequestMapping(method = RequestMethod.DELETE,value = "deleteDelegation/{id}" )
    public void deleteDelegation(@PathVariable long id){delegationService.deleteDelegation(id);}

    @RequestMapping(method = RequestMethod.POST, value = "/addDelegation")
    public void addDelegation(@RequestBody Delegation delegation){
        delegationService.addDelegation(delegation);
    }
    @RequestMapping(method = RequestMethod.PUT, value = "updateDelegation/{id}")
    public void updateDelegation(@RequestBody Delegation delegation, @PathVariable long id){
        delegationService.updateDelegation(delegation, id);
    }



    @RequestMapping(method = RequestMethod.GET, value = "getParticipantDansDelegation/{id}")
    public List<Participant> getParticipantDansDelegation(@PathVariable long id){
        return delegationService.getParticipantDansDelegation(id);
    }
}
