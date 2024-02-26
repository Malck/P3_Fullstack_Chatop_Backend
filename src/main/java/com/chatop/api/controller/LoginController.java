package com.chatop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.model.User;
//import com.chatop.api.model.UserDTO;
import com.chatop.api.service.JWTService;
import com.chatop.api.service.MyUserDetailsService;

@RestController
public class LoginController {

	@Autowired
    private MyUserDetailsService userDetailsService;

	private JWTService jwtService;

	public LoginController(JWTService jwtService) {
		this.jwtService = jwtService;
	}

	@PostMapping("/login")
    public String getToken(Authentication authentication) {
    String token = jwtService.generateToken(authentication);
    return token;

	}

	@PostMapping("/api/auth/register") 
	
	public ResponseEntity<?> saveUser(User userDTO) throws Exception {

		userDetailsService.save(userDTO);

		// Load user details
		//final CustomUserDetails userDetails = userDetailsService.loadUserByUserEmail(user.getEmail());

		// Generate token
		//final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok("hello");
	}
}




