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

import by.epam.grodno.pronych.eshop.model.dto.JwtResponseDto;
import by.epam.grodno.pronych.eshop.model.dto.LoginFormDto;
import by.epam.grodno.pronych.eshop.model.dto.SignUpFormDto;
import by.epam.grodno.pronych.eshop.model.entity.Role;
import by.epam.grodno.pronych.eshop.model.entity.RoleName;
import by.epam.grodno.pronych.eshop.model.entity.User;
import by.epam.grodno.pronych.eshop.model.service.RoleService;
import by.epam.grodno.pronych.eshop.model.service.UserService;
import by.epam.grodno.pronych.eshop.security.jwt.JwtProvider;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthRestApi {
	private AuthenticationManager authenticationManager;
	private UserService userService;
	private RoleService roleService;
	private PasswordEncoder encoder;
	private JwtProvider jwtProvider;

	@Autowired
	AuthRestApi(AuthenticationManager authenticationManager, UserService userService, RoleService roleService,
			PasswordEncoder encoder, JwtProvider jwtProvider) {
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.roleService = roleService;
		this.encoder = encoder;
		this.jwtProvider = jwtProvider;
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginFormDto loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		return ResponseEntity.ok(new JwtResponseDto(jwt));
	}

	@PostMapping("/signup")
	public ResponseEntity<String> registerUser(@RequestBody SignUpFormDto signUpRequest) {

		// Creating user's account
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			strRoles = new HashSet<>();
			strRoles.add("ROLE_USER");
		}
		;
		for (String role : strRoles) {

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
