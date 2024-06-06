package com.example.jomiagique.Service;

import com.example.jomiagique.model.Epreuve;
import com.example.jomiagique.model.Infrastructure;
import com.example.jomiagique.repository.EpreuveRepository;
import com.example.jomiagique.repository.InfrastructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InfrastructureService {

    @Autowired
    private InfrastructureRepository infrastructureRepository;
    @Autowired
    private EpreuveRepository epreuveRepository;

    public Infrastructure getInfrastructure(long id) {
        return infrastructureRepository.findById(id).orElse(null);
    }
    public List<Infrastructure> getInfrastructure() {
        List<Infrastructure> infrastructures = new ArrayList<>();
        infrastructureRepository.findAll().forEach(infrastructure -> {
            infrastructures.add(infrastructure);
        });
        return infrastructures;
    }

    public void deleteInfrastructure(long id) {
        infrastructureRepository.deleteById(id);
    }

    public void addInfrastructure(Infrastructure infrastructure) {
        infrastructureRepository.save(infrastructure);
    }

    public void updateInfrastructure(Infrastructure infrastructure, long id) {
        Infrastructure infrastructuretemp = infrastructureRepository.findById(id).get();
        if (infrastructuretemp != null){
            infrastructure.setId(id);
            infrastructureRepository.save(infrastructure);
        }
    }
    // ajouter une epreuve à la list Epreuve
    public List<Epreuve> getEpreuveDansInfrastructure(long id) {
        Infrastructure infrastructuretemp = infrastructureRepository.findById(id).get();
        if (infrastructuretemp != null) {
            return infrastructuretemp.getEpreuves();
        }
        else {
            return null;
        }
    }

    public void addEpreuveDansInfrastructure(long idInfra, long idEpreuve) {
        Infrastructure infrastructuretemp = infrastructureRepository.findById(idInfra).get();
        Epreuve epreuve = epreuveRepository.findById(idEpreuve).get();
        if (infrastructuretemp != null) {
            infrastructuretemp.getEpreuves().add(epreuve);
        }

    }
}
