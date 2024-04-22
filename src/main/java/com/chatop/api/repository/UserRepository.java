package com.chatop.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.chatop.api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	/**
     * Find users by their email.
     * @param email String User's email.
     * @return List
     */
    List<User> findByEmail(String email);

    /**
     * Find a user by its email.
     * @param email USer's email.
     * @return Optional
     */
    Optional<User> findFirstByEmail(String email);


    User findByName(String username);

    User findByUsername(String username);

}



