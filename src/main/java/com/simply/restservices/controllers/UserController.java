package com.simply.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.simply.restservices.entities.User;
import com.simply.restservices.services.UserService;

//controller
@RestController
public class UserController {

	@Autowired
	private UserService UserService;
	
	//GetAll users
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return UserService.getAllUsers();
	}
	
	//create user
	//@requestbody anotation
	//@postmapping anotation
	@PostMapping("/users")
	public User creatuser(@RequestBody User user) {		
		return UserService.createuser(user);
		
	}
	
	//getUserById 
	@GetMapping("/users/{id}")
	public Optional<User> getUgetUserById(@PathVariable("id") Long id) {
		return UserService.getUserById(id);
	}
	
	//updateUserById
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		return UserService.updateUserById(id, user);
	}
	//deleteUserById
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		UserService.deleteUserById(id);
		
	}
	
	@GetMapping("/users/byusername/{username}")
	public User getUserByUserName(@PathVariable("username") String username) {
		return UserService.getUserByUserName(username);	
	}	
	
}
