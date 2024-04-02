package com.chatop.api.controller;

import com.chatop.api.model.Rental;
import com.chatop.api.model.RentalDTO;
//import com.chatop.api.model.Rental;
import com.chatop.api.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.chatop.api.service.RentalService;

@RestController
@RequestMapping("/api")
public class RentalController {

    @Autowired
    private RentalService rentalService;

//@Autowired
    @GetMapping("/rentals")
    public ResponseEntity<List<Rental>> getRentals() {
        return new ResponseEntity<>(rentalService.getAllRental(), HttpStatus.OK);
    }

    //  @PostMapping("/rentals")
    // public ResponseEntity<List<Rental>> createRentals(Rental rental) {
    //      return new ResponseEntity<>(rentalService.createRental(rental), HttpStatus.OK);
    // }

    @GetMapping("rental/{id}")
    public ResponseEntity<Optional<Rental>> rentalDetail(@PathVariable int id) {
        return ResponseEntity.ok(rentalService.getRental(id));
    }
    
    /* 
    @PutMapping("rental/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Rental> updateRental(@RequestBody Rental updateRental) {
        return ResponseEntity<>(rentalService.updateRental(updateRental), HttpStatus.CREATED);
    }
    */


}
