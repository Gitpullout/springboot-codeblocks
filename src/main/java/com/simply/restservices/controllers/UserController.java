package com.simply.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.simply.restservices.entities.User;
import com.simply.restservices.exceptions.UserExistsException;
import com.simply.restservices.exceptions.UserNotFoundException;
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
	public ResponseEntity<Object> creatuser(@RequestBody User user,UriComponentsBuilder builder)  {		
		try {
			 UserService.createuser(user);
			 HttpHeaders headers = new HttpHeaders();
			 headers.setLocation(builder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
			 return new ResponseEntity<>(headers,HttpStatus.CREATED);
		} catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
		
	}
	
	//getUserById 
	@GetMapping("/users/{id}")
	public Optional<User> getUgetUserById(@PathVariable("id") Long id) {
		try {
			return UserService.getUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
		}
	}
	
	//updateUserById
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			return UserService.updateUserById(id, user);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
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
