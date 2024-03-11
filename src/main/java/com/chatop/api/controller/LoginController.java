package com.chatop.api.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.model.JwtRequest;
import com.chatop.api.model.User;
import com.chatop.api.model.UserDTO;
import com.chatop.api.service.JWTService;
import com.chatop.api.service.MyUserDetailsService;

@RestController
public class LoginController {

	@Autowired
    private MyUserDetailsService userDetailsService;

	private JWTService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	public LoginController(JWTService jwtService) {
		this.jwtService = jwtService;
	}

	@PostMapping("/api/auth/register") 
	
	public ResponseEntity<?> saveUser(@RequestBody User userDTO) throws Exception {

		userDetailsService.save(userDTO);

		// Load user details
		final UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getEmail());
		if(userDetails == null ){
			return ResponseEntity.status(502).body(null);
		}

		// Generate token
		final String token = jwtService.generateToken(userDTO.getEmail());

		return ResponseEntity.ok("token:{" + token + "}") ;
	}


	@PostMapping("/api/auth/login")

    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());

		if(userDetails == null ){
			return ResponseEntity.status(502).body(null);
		}

	 	final String token = jwtService.generateToken(authenticationRequest.getEmail());

	 	return ResponseEntity.ok("token:{" + token + "}");
	}

}




