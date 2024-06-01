package com.chatop.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatop.api.model.JwtRequest;
import com.chatop.api.model.User;
import com.chatop.api.repository.UserRepository;
import com.chatop.api.service.JWTService;
import com.chatop.api.service.MyUserDetailsService;
import com.chatop.api.service.UserService;

import io.swagger.annotations.ApiOperation;

import java.security.Principal;
import java.util.Map;

@RestController
public class LoginController {

	@Autowired
    private MyUserDetailsService userDetailsService;

	private UserService userService;
 
	private JWTService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
    private UserRepository userRepository;

	public LoginController(JWTService jwtService) {
		this.jwtService = jwtService;
	}

	@PostMapping("/api/auth/register") 
	@ApiOperation(value = "Enregistre un nouvel utilisateur")
	
	public ResponseEntity<?> saveUser(@RequestBody User userDTO) throws Exception {

		userDetailsService.save(userDTO);

		// Load user details
		final UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getEmail());
		if(userDetails == null ){
			return ResponseEntity.status(502).body(null);
		}

		// Generate token
		final String token = jwtService.generateToken(userDTO.getEmail());

		return ResponseEntity.ok(Map.of("token", token));
	}

	public static User user = null;


	@PostMapping("/api/auth/login")
	@ApiOperation(value = "Log d'un utilisateur")

    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

		final User userDetails = userDetailsService.loadUserByUsername2(authenticationRequest.getEmail());

		if(userDetails == null ){
			return ResponseEntity.status(502).body(null);
		}
        
		user = userDetails;

	 	final String token = jwtService.generateToken(authenticationRequest.getEmail());

		return ResponseEntity.ok(Map.of("token", token));
	}
    
	@GetMapping("api/auth/me")
     public ResponseEntity<User> me(Principal principal) throws Exception {
		User userResponse = LoginController.user;
                  
        return ResponseEntity.ok(userResponse);
    }
	
}




