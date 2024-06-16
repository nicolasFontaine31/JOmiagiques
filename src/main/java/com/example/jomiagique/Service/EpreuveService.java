package com.example.jomiagique.Service;

import com.example.jomiagique.model.Epreuve;
import com.example.jomiagique.model.Organisateur;
import com.example.jomiagique.repository.EpreuveRepository;
import com.example.jomiagique.repository.ResultatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EpreuveService {
    @Autowired
    private EpreuveRepository epreuveRepository;

    public List<Epreuve> getEpreuves(){
        List<Epreuve> epreuves = new ArrayList<>();
        epreuveRepository.findAll().forEach(epreuve -> {
            epreuves.add(epreuve);
        });
        return epreuves;
    }


    public Epreuve getEpreuve(long id) {
        return epreuveRepository.findById(id).orElse(null);
    }

    public void deleteEpreuve(long id) {
        epreuveRepository.deleteById(id);
    }

    public void addEpreuve(Epreuve epreuve) {
        epreuveRepository.save(epreuve);
    }

    public void updateEpreuve(Epreuve epreuve) {
        epreuveRepository.save(epreuve);
    }
}
