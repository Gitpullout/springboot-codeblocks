package com.simply.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simply.restservices.entities.Order;
import com.simply.restservices.entities.User;
import com.simply.restservices.exceptions.OrderNotFoundException;
import com.simply.restservices.exceptions.UserNotFoundException;
import com.simply.restservices.repositories.OrderRepository;
import com.simply.restservices.repositories.UserRepository;

@RestController
@RequestMapping(value = "/hateoas/users")
public class OrderHateoasController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	OrderRepository orderRepository;

	// get all orders for a user
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userid);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found =" + userid);
		}
		return userOptional.get().getOrders();
	}
	
	@GetMapping("/{userId}/orders/{orderId}")
	public Order getOrderById(@PathVariable() Long userId, @PathVariable() Long orderId)throws UserNotFoundException, OrderNotFoundException{
	    Optional<User> userOptional = userRepository.findById(userId);
	    if(!userOptional.isPresent())
	        throw new UserNotFoundException("Invalid user");
	    Optional<Order> optionalOrder = orderRepository.findById(orderId);
	    if(!optionalOrder.isPresent())
	        throw new OrderNotFoundException("Invalid order id");
	    return optionalOrder.get();
	}

}
