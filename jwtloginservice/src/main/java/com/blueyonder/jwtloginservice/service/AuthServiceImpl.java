package com.blueyonder.jwtloginservice.service;

import java.util.*;

import com.blueyonder.jwtloginservice.exceptions.IncorrectPassword;
import com.blueyonder.jwtloginservice.exceptions.UserDoesNotExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blueyonder.jwtloginservice.dto.MessageResponse;
import com.blueyonder.jwtloginservice.entities.ERole;
import com.blueyonder.jwtloginservice.entities.Role;
import com.blueyonder.jwtloginservice.entities.UserCredentials;
import com.blueyonder.jwtloginservice.exceptions.UsernameAlreadyExistsException;
import com.blueyonder.jwtloginservice.repositories.RoleRepository;
import com.blueyonder.jwtloginservice.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTService jwtService;
	
	@Override
    public ResponseEntity<MessageResponse> saveUser(SignupRequest credentials) throws UsernameAlreadyExistsException {
        System.out.println(credentials);
		if (userRepository.existsByUsername(credentials.getUsername())) {
            throw new UsernameAlreadyExistsException("Error: Username is already taken!");
        }
        UserCredentials newuser= new UserCredentials(credentials.getUsername(),credentials.getEmail(),passwordEncoder.encode(credentials.getPassword()));
		Set<String> strRoles;
		if(credentials.getRole()==null)
		{
			strRoles = new HashSet<>();
			strRoles.add("user");
		}
		else
			strRoles = credentials.getRole();

		Set<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {
			switch (role) {
			case "admin":
				Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(adminRole);
				break;
			default:
				Role userRole = roleRepository.findByName(ERole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
				break;
			}
		});

		newuser.setRoles(roles);
		//saving UserEntity to the database 
		userRepository.save(newuser);
        return ResponseEntity.ok(new MessageResponse("User has been stored"));
    }

	@Override
	public String generateToken(String username,Collection<? extends GrantedAuthority> collection) {
		return jwtService.generateToken(username,collection);
	}

	@Override
	public void validateT(String token) {
		jwtService.validateToken(token);
		
	}
	
	@Override
	public ResponseEntity<Object> changePassword(String username, String oldPassword, String newPassword) throws UserDoesNotExist, IncorrectPassword {
		UserCredentials user = userRepository.findByUsername(username)
			.orElseThrow(() -> new UserDoesNotExist("User "+username+" not found!"));
		if(passwordEncoder.matches(oldPassword, user.getPassword())) {
			user.setPassword(passwordEncoder.encode(newPassword));
			userRepository.save(user);
		}
		else{
			throw new IncorrectPassword("Incorrect Password!");
		}
		HashMap<String, String> map = new HashMap<>();
		map.put("message", "Password changed Successfully");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> getAllUsers() {
		//null all passwords
		List<UserCredentials> users = userRepository.findAll();
		users.forEach(user->user.setPassword(null));
		
		return new ResponseEntity<>(users,HttpStatus.OK);
	}
}
