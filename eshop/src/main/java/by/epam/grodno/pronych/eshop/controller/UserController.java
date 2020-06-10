package by.epam.grodno.pronych.eshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.epam.grodno.pronych.eshop.model.dto.UserDto;
import by.epam.grodno.pronych.eshop.model.entity.User;
import by.epam.grodno.pronych.eshop.model.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

	private UserService userService;

	@Autowired
	UserController(UserService userService) {
		this.userService = userService;
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/list")
	public ResponseEntity<List<User>> list() {
		List<User> theUsers = userService.getAll();
		return ResponseEntity.ok().body(theUsers);
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@GetMapping("/depts")
	public ResponseEntity<List<UserDto>> depts() {
		List<UserDto> theUsers = userService.getAllUserDebts();
		return ResponseEntity.ok().body(theUsers);
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@RequestMapping("/user/{id}")
	public ResponseEntity<User> get(@PathVariable("id") int id) {
		User user = userService.getById(id);
		return ResponseEntity.ok().body(user);
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@RequestMapping("/getbyname")
	public ResponseEntity<UserDto> getByName(@RequestBody User userMSG) {
		UserDto user = userService.getByUserName(userMSG.getName());
		return ResponseEntity.ok().body(user);
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@RequestMapping("/update")
	public ResponseEntity<?> update(@RequestBody User user) {
		userService.update(user);
		return ResponseEntity.ok().body("User updated with id:" + user.getId());
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@RequestMapping("/isadmin")
	public ResponseEntity<?> isUserAdmin(@RequestBody UserDto userMSG) {
		User user = userService.getById((int) userMSG.getId());
		return ResponseEntity.ok().body(userService.isUserAdmin(user));
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/setToBlackList")
	public ResponseEntity<?> setToBlackList(@RequestBody UserDto userMSG) {
		userService.setToBlackList(userMSG);
		return ResponseEntity.ok().body(userMSG);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping("/removeFromBlackList")
	public ResponseEntity<?> removeFromBlackList(@RequestBody UserDto userMSG) {
		userService.removeFromBlackList(userMSG);
		return ResponseEntity.ok().body(userMSG);
	}

	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	@RequestMapping("/isUserBlocked")
	public ResponseEntity<?> isUserBlocked(@RequestBody UserDto userMsg) {
		return ResponseEntity.ok().body(isUserInBlackList(userMsg));
	}

	public boolean isUserInBlackList(UserDto userMsg) {
		return userService.isUserInBlackList(userMsg);
	}
}
