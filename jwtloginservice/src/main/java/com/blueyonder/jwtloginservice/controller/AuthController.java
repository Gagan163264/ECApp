package com.blueyonder.jwtloginservice.controller;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.blueyonder.jwtloginservice.exceptions.IncorrectPassword;
import com.blueyonder.jwtloginservice.exceptions.UserDoesNotExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blueyonder.jwtloginservice.dto.LoginRequest;
import com.blueyonder.jwtloginservice.dto.MessageResponse;
//import com.blueyonder.jwtloginservice.entities.ERole;
//import com.blueyonder.jwtloginservice.entities.Role;
import com.blueyonder.jwtloginservice.entities.UserCredentials;
import com.blueyonder.jwtloginservice.exceptions.UsernameAlreadyExistsException;
//import com.blueyonder.jwtloginservice.repositories.RoleRepository;
import com.blueyonder.jwtloginservice.service.AuthService;
import com.blueyonder.jwtloginservice.service.CustomUserDetails;
import com.blueyonder.jwtloginservice.service.SignupRequest;

import org.springframework.http.MediaType;

//@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private AuthService authService;
	
//	@Autowired
//	private RoleRepository roleRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping(value="/register",consumes =MediaType.ALL_VALUE)
	public ResponseEntity<MessageResponse> addNewUser(@RequestBody SignupRequest user) throws UsernameAlreadyExistsException {
		
		user.setRole(null);

		return authService.saveUser(user);
	}
	@PostMapping(value="/login",consumes =MediaType.ALL_VALUE)
	public ResponseEntity<Object> userLogin(@RequestBody LoginRequest userCredentials) {
		System.out.println(userCredentials);

		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredentials.getUsername(), userCredentials.getPassword()));
		if (authenticate.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(authenticate);
			CustomUserDetails userDetails = (CustomUserDetails) authenticate.getPrincipal();
			String jwt = authService.generateToken(userCredentials.getUsername(), userDetails.getAuthorities());
			HashMap<String, Object> map = new HashMap<>();
			map.put("token", jwt);
			map.put("username", userCredentials.getUsername());
			map.put("role", userDetails.getAuthorities());

			return new ResponseEntity<>(map, HttpStatus.OK);
		}
		return null;
	}
	@PostMapping(value="/registerAdmin",consumes =MediaType.ALL_VALUE)
	public ResponseEntity<MessageResponse> addNewAdmin(@RequestBody SignupRequest user) throws UsernameAlreadyExistsException {
		return authService.saveUser(user);
	}

	@PostMapping(value="/changePassword",consumes =MediaType.ALL_VALUE)
	public ResponseEntity<Object> changePassword(@RequestParam String username,@RequestParam String oldPassword,@RequestParam String newPassword) throws UserDoesNotExist, IncorrectPassword {
		return authService.changePassword(username,oldPassword,newPassword);
	}

	@GetMapping(value="/validateToken",consumes =MediaType.ALL_VALUE)
	public void validateToken(@RequestParam String token) {
		authService.validateT(token);
	}

	@GetMapping(value="/getAllUsers",consumes =MediaType.ALL_VALUE)
	public ResponseEntity<Object> getAllUsers() {
		return authService.getAllUsers();
	}

}
