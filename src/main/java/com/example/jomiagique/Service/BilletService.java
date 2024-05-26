package com.example.jomiagique.Service;

import com.example.jomiagique.model.Billet;
import com.example.jomiagique.repository.BilletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BilletService {
    @Autowired
    private BilletRepository billetRepository;

    public List<Billet> getBillets(){
        List<Billet> billets = new ArrayList<>();
        billetRepository.findAll().forEach(billet -> {
            billets.add(billet);
        });
        return billets;
    }

    public Billet getBillet(long id){
        return billetRepository.findById(id).orElse(null);
    }

    public void deleteBillet(long id){
        billetRepository.deleteById(id);
    }
    public void addBillet(Billet billet){
        billetRepository.save(billet);
    }


}
