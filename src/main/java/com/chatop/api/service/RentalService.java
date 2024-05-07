package com.chatop.api.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.chatop.api.controller.LoginController;
import com.chatop.api.model.Rental;
import com.chatop.api.model.User;
import com.chatop.api.repository.RentalRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    public Optional<Rental> getRental(final Integer id) {
        return rentalRepository.findById(id);
    }

    /* ici pour recuperer les rentals avec le get dans rentalcontroller */
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    public Rental updateRental(Rental rental) {
        Rental updateRental = rentalRepository.save(rental);
        return updateRental;
    }

    public Rental createRental(Rental rental) {
        Rental savedRental = rentalRepository.save(rental);
        return savedRental;
    }  

    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public Rental createRental(String name, int surface, int price, MultipartFile picture, String description) throws IOException {
        Date actualDate = new Date();
        Rental rental = new Rental();
        
        User user = LoginController.user;
        rental.setUser(user);
        
        rental.setCreatedAt(actualDate);
        rental.setUpdatedAt(actualDate);
        rental.setName(name);
        rental.setSurface(surface);
        rental.setPrice(price);

        if (!picture.isEmpty()) {
            String picturePath = savePicture(picture);
            rental.setPicture(picturePath);
        }
        
        rental.setDescription(description);

        return rentalRepository.save(rental);
    }

    private String savePicture(MultipartFile picture) throws IOException {
        // Générer un nom de fichier unique
        String fileName = UUID.randomUUID().toString() + "_" + picture.getOriginalFilename();
        // Obtenir le chemin d'accès complet vers le répertoire de téléchargement
        String uploadDir = "/Files"; 
        // Enregistrer le fichier dans le répertoire de téléchargement
        Path filePath = Paths.get(uploadDir, fileName);

      try {
            File directory = new File(uploadDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            Files.copy(picture.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            return filePath.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
