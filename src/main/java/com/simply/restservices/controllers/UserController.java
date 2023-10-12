package com.simply.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.simply.restservices.entities.User;
import com.simply.restservices.exceptions.UserExistsException;
import com.simply.restservices.exceptions.UserNameNotFoundException;
import com.simply.restservices.exceptions.UserNotFoundException;
import com.simply.restservices.services.UserService;

//controller
@RestController
@Validated
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private UserService UserService;

	// GetAll users
	// @GetMapping("/users")
	@GetMapping
	public List<User> getAllUsers() {
		return UserService.getAllUsers();
	}

	// create user
	// @requestbody anotation
	// @postmapping anotation
	// @PostMapping("/users")
	@PostMapping
	public ResponseEntity<Object> creatuser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
		try {
			UserService.createuser(user);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(builder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<>(headers, HttpStatus.CREATED);
		} catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}

	}

	// getUserById
	// @GetMapping("/users/{id}")
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {
		try {
			return UserService.getUserById(id);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}

	// updateUserById
	// @PutMapping("/users/{id}")
	@PutMapping("/{id}")
	public User updateUserById(@PathVariable("id") Long id, @RequestBody User user) {
		try {
			return UserService.updateUserById(id, user);
		} catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	// deleteUserById
	// @DeleteMapping("/users/{id}")
	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable("id") Long id) {
		UserService.deleteUserById(id);

	}

	// @GetMapping("/users/byusername/{username}")
	@GetMapping("/byusername/{username}")
	public User getUserByUserName(@PathVariable("username") String username) throws UserNameNotFoundException {
		// return UserService.getUserByUserName(username);
		User user = UserService.getUserByUserName(username);
		if (user == null) {
			throw new UserNameNotFoundException("UserName = '" + username + " 'not found in user repository");
		}
		return user;
	}

}
