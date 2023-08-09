package com.bnta.chocolate.controllers;

import com.bnta.chocolate.models.Chocolate;
import com.bnta.chocolate.models.ChocolateDTO;
import com.bnta.chocolate.repositories.ChocolateRepository;
import com.bnta.chocolate.services.ChocolateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("chocolates")
public class ChocolateController {

    @Autowired
    ChocolateService chocolateService;

//    Handles following:
//    * GET /chocolates
//    * GET /chocolates/:id
//    * GET /chocolates?cocoaPercentage=69
//    INDEX
    @GetMapping
    public ResponseEntity<List<Chocolate>> getAllChocolatesAndFilters(
            @RequestParam(required = false, name = "cocoaPercentage") Integer cocoaPercentage
    ){
//        GET /chocolates?cocoaPercentage=69
        if(cocoaPercentage != null){
            return new ResponseEntity<>(chocolateService.findAllChocolatesOverCocoaPercentage(cocoaPercentage), HttpStatus.OK);
        }
//        GET /chocolates
        return new ResponseEntity<>(chocolateService.findAllChocolates(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Chocolate>> getChocolate(@PathVariable Long id){
        return new ResponseEntity(chocolateService.findChocolate(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Chocolate> postChocolate(@RequestBody ChocolateDTO chocolateDTO){
       Chocolate savedChocolate =  chocolateService.saveChocolate(chocolateDTO);
        return new ResponseEntity<>(savedChocolate, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")    // ADDED
    public ResponseEntity<Chocolate> updateChocolate(@RequestBody ChocolateDTO chocolateDTO, @PathVariable Long id){
       Chocolate chocolate = chocolateService.updateChocolate(chocolateDTO, id);
        return new ResponseEntity<>(chocolate, HttpStatus.OK);
    }

}
