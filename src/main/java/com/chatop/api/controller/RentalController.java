package com.chatop.api.controller;

import com.chatop.api.model.Rental;
import com.chatop.api.service.RentalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RentalController {
    
    @Autowired
    private RentalService rentalService;

    @PostMapping(value = "/rentals")
    public ResponseEntity<String> createRentals(
            @RequestParam("name") String name,
            @RequestParam("surface") int surface,
            @RequestParam("price") int price,
            @RequestParam("picture") MultipartFile picture,
            @RequestParam("description") String description) {

        try {
            // Créer le rental en utilisant le service RentalService
            Rental rental = rentalService.createRental(name, surface, price, picture, description); 
            return ResponseEntity.ok("{\"message\":\"Rental created !\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create rental");
        }
    }

    @GetMapping("/rentals")
    public ResponseEntity<Map<String, List<Rental>>> getAllRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        Map<String, List<Rental>> ret = new HashMap();
        ret.put("rentals",rentals);
        return new ResponseEntity(ret, HttpStatus.OK);
    }

    @GetMapping("rental/{id}")
    public ResponseEntity<Optional<Rental>> rentalDetail(@PathVariable int id) {
        return ResponseEntity.ok(rentalService.getRental(id));
    }
    
    @PutMapping("rental/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Rental> updateRental(@PathVariable("id") Integer id, @RequestBody Rental updateRental) {
        Rental updatedRental = rentalService.updateRental(updateRental);
        return ResponseEntity.ok(updatedRental);
    }

}
