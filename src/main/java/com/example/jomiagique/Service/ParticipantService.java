package com.example.jomiagique.Service;

import com.example.jomiagique.model.Epreuve;
import com.example.jomiagique.model.Participant;
import com.example.jomiagique.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;


    public Set<Participant> getParticipants() {
        Set<Participant> participants = new HashSet<>();
        participantRepository.findAll().forEach(participant -> {
            participants.add(participant);
        });
        return participants;
    }

    public Participant getParticipant(long idParticipant) {
       return participantRepository.findById(idParticipant).orElse(null);
    }

    public void addParticipant(Participant participant) {
        participantRepository.save(participant);
    }

    public void deleteParticipant(long idParticipant) {
        participantRepository.deleteById(idParticipant);
    }

    public void updateParticipant(Participant participant) {
        participantRepository.save(participant);
    }

    public void addEpreuveToParticipant(Participant participant) {
        participantRepository.save(participant);
    }

    public void desengagerEpreuveToParticipant(Participant participant) {
        participantRepository.save(participant);
    }
}
