package com.bnta.chocolate.services;

import com.bnta.chocolate.models.Chocolate;
import com.bnta.chocolate.models.ChocolateDTO;
import com.bnta.chocolate.models.Estate;
import com.bnta.chocolate.repositories.ChocolateRepository;
import com.bnta.chocolate.repositories.EstateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChocolateService {

    @Autowired
    ChocolateRepository chocolateRepository;

    @Autowired
    EstateRepository estateRepository;

    public Chocolate saveChocolate(ChocolateDTO chocolateDTO){
        Chocolate chocolate = new Chocolate(chocolateDTO.getName(),chocolateDTO.getCocoaPercentage());
        for(Long estateId : chocolateDTO.getEstateIds()){
            Estate estate = estateRepository.findById(estateId).get();
            chocolate.addEstate(estate);
        }
        return chocolateRepository.save(chocolate);
    }

    public Chocolate findChocolate(Long id){
        return chocolateRepository.findById(id).get();
    }

    public List<Chocolate> findAllChocolates(){
        return chocolateRepository.findAll();
    }

    public List<Chocolate> findAllChocolatesOverCocoaPercentage(int percentage){
        return chocolateRepository.findByCocoaPercentageGreaterThan(percentage);
    }

    public Chocolate updateChocolate(ChocolateDTO chocolateDTO, Long id){ // ADDED
        Chocolate chocolateToUpdate = chocolateRepository.findById(id).get();
        chocolateToUpdate.setName(chocolateDTO.getName());
        chocolateToUpdate.setCocoaPercentage(chocolateDTO.getCocoaPercentage());
        // remove all existing estates
        chocolateToUpdate.setEstates(new ArrayList<Estate>());
        // find and add estates
        for (Long estateId : chocolateDTO.getEstateIds()){
            Estate estate = estateRepository.findById(estateId).get();
            chocolateToUpdate.addEstate(estate);
        }
        chocolateRepository.save(chocolateToUpdate);
        return chocolateToUpdate;
    }



}
