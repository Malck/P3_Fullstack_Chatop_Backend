package com.chatop.api.service;

//import java.util.Optional;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.chatop.api.model.Rental;

//import com.chatop.api.repository.RentalRepository;

//import org.springframework.stereotype.Service;
//import lombok.Data;
import java.util.List;
import java.util.Optional;

import com.chatop.api.model.Rental;
import com.chatop.api.model.RentalDTO;
import com.chatop.api.repository.RentalRepository;

import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}


 


