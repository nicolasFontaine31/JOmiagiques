package com.example.jomiagique.Service;

import com.example.jomiagique.model.Delegation;
import com.example.jomiagique.model.Epreuve;
import com.example.jomiagique.model.Infrastructure;
import com.example.jomiagique.model.Participant;
import com.example.jomiagique.repository.DelegationRepository;
import com.example.jomiagique.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DelegationService {

    @Autowired
    private DelegationRepository delegationRepository;
    @Autowired
    private ParticipantRepository participantRepository;

    public Delegation getDelegation(long id){
        return delegationRepository.findById(id).orElse(null);
    }
    public List<Delegation> getDelegations(){
        List<Delegation> delegations = new ArrayList<>();
        delegationRepository.findAll().forEach(delegation -> {
            delegations.add(delegation);
        });
        return delegations;
    }
    public void deleteDelegation(long id) {
        delegationRepository.deleteById(id);
    }
    public void addDelegation(Delegation delegation) {
        delegationRepository.save(delegation);
    }

    //cet appel de fonction ne se fait que quand une délégation existe avec un participant depuis l'ajout d'un résultat.
    public void updateDelegationProgram(Delegation delegation){
        delegationRepository.save(delegation);
    }

    public void updateDelegation(Delegation delegation, long id) {
        Delegation delegationtemp = delegationRepository.findById(id).get();
        if (delegationtemp != null){
            delegation.setId(id);
            delegationRepository.save(delegation);
        }
    }
    public List<Participant> getParticipantDansDelegation(long id) {
        Delegation delegationtemp = delegationRepository.findById(id).get();
        if (delegationtemp != null) {
            return delegationtemp.getParticipants();
        }
        return null;
    }


    public List<Delegation> getClassementGeneral() {
        return getDelegations().stream()
                .sorted(Comparator.comparingInt(Delegation::getNbMedaillesOr)
                        .thenComparingInt(Delegation::getNbMedaillesArgent)
                        .thenComparingInt(Delegation::getNbMedaillesBronze))
                .collect(Collectors.toList());
    }
}
