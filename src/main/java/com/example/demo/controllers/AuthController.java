package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.ERole;
import com.example.demo.models.Project;
import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.payload.response.JwtResponse;
import com.example.demo.payload.response.LoginResponse;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.dao.ProjectRepository;
import com.example.demo.dao.RoleRepository;
import com.example.demo.dao.UserRepository;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.services.UserDetailsImpl;
import com.example.demo.services.UserService;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserService userService;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		User user = userService.getUserById(userDetails.getUserId());
		
		return ResponseEntity.ok(new LoginResponse(user, jwt));

/*
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getUserId(), 
												 userDetails.getFirstname(),
												 userDetails.getLastname(),
												 userDetails.getUsername(), 
												 userDetails.getEmail(),
												 userDetails.getJiraUsername(),
												 userDetails.getPhone(),
												 userDetails.getUserCode(),
												 roles.get(0)));	*/
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		
		if (userRepository.existsByJiraUsername(signUpRequest.getJiraUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Jira username is already taken!"));
		}

		if (userRepository.existsByUserCode(signUpRequest.getUserCode())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: User code is already taken!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getFirstname(),
							 signUpRequest.getLastname(),
							 signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()),
							 signUpRequest.getPhone(),
							 signUpRequest.getJiraUsername(),
							 signUpRequest.getUserCode());

		String roleReq = signUpRequest.getRole();

		if (roleReq == null) {
			Role junRole = roleRepository.findByName(ERole.ROLE_JUNIOR)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			user.setRole(junRole);
		} else {
				switch (roleReq) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					user.setRole(adminRole);
					
					break;
				case "supervisor":
					Role supRole = roleRepository.findByName(ERole.ROLE_SUPERVISOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					user.setRole(supRole);

					break;
				case "tl":
					Role tlRole = roleRepository.findByName(ERole.ROLE_TEAMLEADER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					user.setRole(tlRole);

					break;
				case "senior":
					Role senRole = roleRepository.findByName(ERole.ROLE_SENIOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					user.setRole(senRole);

					break;
				case "developer":
					Role devRole = roleRepository.findByName(ERole.ROLE_DEVELOPER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					user.setRole(devRole);

					break;
				default:
					Role junRole = roleRepository.findByName(ERole.ROLE_JUNIOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					user.setRole(junRole);
				};
			};
		
		List<Project> projects = new ArrayList<Project>();
		Set<Integer> projectIds = signUpRequest.getProjectIds();
		if(projectIds != null) {
			for (Integer id : projectIds) {
				Project proj = projectRepository.findById(id)
						.orElseThrow(() -> new RuntimeException("Error: Project is not found."));
				projects.add(proj);
			}
			user.setProjects(projects);		
		}
		
		userRepository.save(user);
		

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}