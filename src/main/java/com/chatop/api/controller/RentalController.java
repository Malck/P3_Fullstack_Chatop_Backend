package com.chatop.api.controller;

import com.chatop.api.model.Rental;
import com.chatop.api.model.RentalDTO;
//import com.chatop.api.model.Rental;
import com.chatop.api.service.RentalService;

import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.chatop.api.service.RentalService;

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
            Rental rental = rentalService.createRental(name, surface, price, picture, description);  /*------------ ici---------------- */
            
            return ResponseEntity.ok("{\"message\":\"Rental created !\"}");
        } catch (Exception e) {
            // En cas d'erreur, retourner une réponse d'erreur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create rental");
        }
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


 /*@PostMapping("/rentals")
    public ResponseEntity<List<Rental>> createRentals(Rental rental) {
          return ResponseEntity.ok(rentalService.createRental(rental));
    }
    */


    /*@Value("${upload.dir}")
    private String uploadDir;

    @PostMapping(value = "/rentals")
    public ResponseEntity<String> createRentals(
            @RequestParam("name") String name,
            @RequestParam("surface") int surface,
            @RequestParam("price") double price,
            @RequestParam("picture") MultipartFile picture,
            @RequestParam("description") String description) {
        
        if (picture.isEmpty()) {
            return ResponseEntity.badRequest().body("Picture is required");
        }

        try {
            // Générer un nom de fichier unique
            String fileName = UUID.randomUUID().toString() + "_" + picture.getOriginalFilename();
            // Créer le répertoire de stockage 
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            // Enregistrer le fichier dans le répertoire
            Path filePath = Paths.get(uploadDir, fileName);
            Files.write(filePath, picture.getBytes());
            // Obtenir le chemin d'accès de l'image
            String imagePath = filePath.toString();
            // Vous pouvez maintenant utiliser imagePath pour enregistrer le chemin d'accès dans la base de données ou pour d'autres opérations
            return ResponseEntity.ok("{\"message\":\"Rental created ! Image saved at: " + imagePath + "\"}");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save picture");
        }
    } */