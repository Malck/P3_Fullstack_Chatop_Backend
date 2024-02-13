package com.chatop.api.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import com.chatop.api.model.Rental;
import com.chatop.api.repository.RentalRepository;

import org.springframework.stereotype.Service;
import lombok.Data;

@Data
@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    public Optional<Rental> getRental(final Integer id) {
        return rentalRepository.findById(id);
    }

    public Iterable<Rental> getRentals() {
        return rentalRepository.findAll();
    }

    public void deleteRental(final Integer id) {
        rentalRepository.deleteById(id);
    }

    public Rental saveRental(Rental rental) {
        Rental savedRental = rentalRepository.save(rental);
        return savedRental;
    }  

}

