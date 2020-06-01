package by.epam.grodno.pronych.eshop.controller;

import java.util.HashSet;
import java.util.Set;

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

import by.epam.grodno.pronych.eshop.entity.Role;
import by.epam.grodno.pronych.eshop.entity.RoleName;
import by.epam.grodno.pronych.eshop.entity.User;

import by.epam.grodno.pronych.eshop.message.JwtResponse;
import by.epam.grodno.pronych.eshop.message.LoginForm;
import by.epam.grodno.pronych.eshop.message.SignUpForm;

import by.epam.grodno.pronych.eshop.security.jwt.JwtProvider;
import by.epam.grodno.pronych.eshop.service.RoleService;
import by.epam.grodno.pronych.eshop.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthRestApi {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtProvider.generateJwtToken(authentication);
		return ResponseEntity.ok(new JwtResponse(jwt));
	}
	
	@RequestMapping("/test")
	public ResponseEntity<String> testUser(@RequestBody SignUpForm signUpRequest) {
		return ResponseEntity.ok().body("User registered successfully!");
	}
	
	@PostMapping("/signup")
	public ResponseEntity<String> registerUser(@RequestBody SignUpForm signUpRequest) {

		/*
		 * if(userDao.existsByUsername(signUpRequest.getUsername())) { return new
		 * ResponseEntity<String>("Fail -> Username is already taken!",
		 * HttpStatus.BAD_REQUEST); }
		 */

		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			strRoles = new HashSet<>();
			strRoles.add("ROLE_USER");
		};
		for (String role : strRoles) {
			//System.out.println(role);

			switch (role) {
			case "ROLE_ADMIN":
				Role adminRole = roleService.getByName(RoleName.ROLE_ADMIN);
				if (adminRole == null) {
					throw new RuntimeException("Fail! -> Cause: User Role not find.");
				}
				;
				roles.add(adminRole);
				break;
			default:
				Role userRole = roleService.getByName(RoleName.ROLE_USER);
				if (userRole == null) {
					throw new RuntimeException("Fail! -> Cause: User Role not find.");
				}
				;
				roles.add(userRole);
			}
		}

		user.setRoles(roles);
		userService.save(user);

		return ResponseEntity.ok().body("User registered successfully!");
	}
}
