package by.epam.grodno.pronych.eshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.epam.grodno.pronych.eshop.entity.User;
import by.epam.grodno.pronych.eshop.service.UserService;
import by.epam.grodno.pronych.eshop.service.dts.UserMsg;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<User>> list() {
    	List < User > theUsers = userService.getAll();
        return ResponseEntity.ok().body(theUsers);
    }
    
    @GetMapping("/depts")
    public ResponseEntity<List<UserMsg>> depts() {
    	List < UserMsg > theUsers = userService.getAllDebts();
        return ResponseEntity.ok().body(theUsers);
    }
    
	@RequestMapping("/user/{id}")
	public ResponseEntity<User> get(@PathVariable("id") int id) {
		User user = userService.getById(id);		
		return ResponseEntity.ok().body(user); 
	}
	
		
	@RequestMapping("/getbyname")
	public ResponseEntity<User> getByName(@RequestBody User userMSG) {
		User user = userService.getByUserName(userMSG.getName());		
		return ResponseEntity.ok().body(user); 
	}
	
	@RequestMapping("/update")
	public ResponseEntity<?> update(@RequestBody User user) {
		userService.update(user);
		return ResponseEntity.ok().body("User updated with id:"+user.getId()); 
	}
	    
	@RequestMapping("/isadmin")
	public ResponseEntity<?> isUserAdmin(@RequestBody User userMSG) {
		User user = userService.getByUserName(userMSG.getUsername());
		return ResponseEntity.ok().body(userService.isUserAdmin(user)); 
	}

	@RequestMapping("/setToBlackList")
	public ResponseEntity<?> setToBlackList(@RequestBody UserMsg userMSG) {
		userService.setToBlackList(userMSG);
		return ResponseEntity.ok().body("ok"); 
	}

	@RequestMapping("/removeFromBlackList")
	public ResponseEntity<?> removeFromBlackList(@RequestBody UserMsg userMSG) {
		userService.removeFromBlackList(userMSG);
		return ResponseEntity.ok().body("ok"); 
	}

	@RequestMapping("/isUserBlocked")
	public ResponseEntity<?> isUserBlocked(@RequestBody UserMsg userMsg) {
		return ResponseEntity.ok().body(isUserInBlackList(userMsg)); 
	}
	
	public boolean isUserInBlackList(UserMsg userMsg) {
		return userService.isUserInBlackList(userMsg);
	}
}

