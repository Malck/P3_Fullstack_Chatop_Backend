package com.chatop.api.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.chatop.api.controller.LoginController;
import com.chatop.api.model.Rental;
import com.chatop.api.model.RentalDTO;
import com.chatop.api.model.User;
import com.chatop.api.repository.RentalRepository;

import lombok.Data;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
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

    public List<Rental> getAllRental() {
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


    /* code a tester pour la creation de rental  */
    
    public RentalService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    public Rental createRental(String name, int surface, int price, MultipartFile picture, String description) throws IOException {
        Date actualDate = new Date();
        Rental rental = new Rental();

        /* -------------   ICI ------------- */
        // LoginController.user
        // rental.setUser(null);

        

        // Autres attributs de la location...
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
        // Ajoutez ici la logique pour définir l'ID du propriétaire de la location
        // Par exemple, si vous avez l'utilisateur actuel, vous pouvez définir son ID comme propriétaire
        
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

/*
@Value("${upload.dir}")
private String uploadDir;

public Rental createRental(String name, int surface, int price, MultipartFile picture, String description) throws IOException {

    // Créer le répertoire de stockage s'il n'existe pas
    File directory = new File(uploadDir);
    if (!directory.exists()) {
        directory.mkdirs();
    }
    // Générer un nom de fichier unique pour l'image
    String fileName = UUID.randomUUID().toString() + "_" + picture.getOriginalFilename();

    // Enregistrer l'image dans le répertoire de stockage
    Path filePath = Paths.get(uploadDir, fileName);
    Files.write(filePath, picture.getBytes());

    // Construire le chemin d'accès de l'image
    String picturePath = filePath.toString();

    // Maintenant, vous pouvez créer le rental en utilisant le chemin d'accès de l'image
    Rental rental = new Rental();
    rental.setName(name);
    rental.setSurface(surface);
    rental.setPrice(price);
    rental.setPicture(picturePath);
    rental.setDescription(description);

    // Sauvegarder le rental dans la base de données
    return rentalRepository.save(rental);
} */
 


