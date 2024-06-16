package com.example.jomiagique.repository;

import com.example.jomiagique.model.Billet;
import com.example.jomiagique.model.Resultats;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface ResultatRepository extends CrudRepository<Resultats, Long> {
    //Optional<Resultats> findByEpreuves_IdAndParticipants_Id(long idEpreuve, long idParticipant);
}
