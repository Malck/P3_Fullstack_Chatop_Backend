package com.chatop.api.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.chatop.api.model.User;
import com.chatop.api.model.UserDTO;

/**
 * User service interface.
 */
public interface UserService {

    /**
     * Create a user.
     * @param newUserDTO NewUSerDTO to create.
     * @return UserDTO
     */
    UserDTO createUser(User newUserDTO) throws Exception;

    /**
     * Get a user by its id.
     * @param id User's id.
     * @return UserDTO
     */
    UserDTO getUserById(long id) throws Exception;

    /**
     * Get a user by its email.
     * @param email User's email.
     * @return UserDTO
     */
    UserDTO getUserByEmail(String email) throws Exception;

    
}
