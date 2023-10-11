package com.simply.restservices.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.server.LinkBuilder;
import org.springframework.hateoas.server.mvc.ControllerLinkRelationProvider;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.simply.restservices.entities.User;
import com.simply.restservices.exceptions.UserNotFoundException;
import com.simply.restservices.repositories.UserRepository;
import com.simply.restservices.services.UserService;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;

	// GetAll users
	@GetMapping
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	// getUserById
		//@GetMapping("/users/{id}")
	/*	@GetMapping("/{id}")
		public Optional<User> getUserById(@PathVariable("id") @Min(1) Long id) {
			try {
				Optional<User> userOptional = userService.getUserById(id);
				User user = userOptional.get();
				Long userid = user.getId();
				Link selfLink = Links.
			} catch (UserNotFoundException e) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
			}
		} */
}
