package com.simply.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.simply.restservices.entities.User;
import com.simply.restservices.repositories.UserRepository;

//service
@Service
public class UserService {
	
	//Autowired the UserRepository
	@Autowired
	private UserRepository UserRepository;

	//getAllusers Method
	public List<User> getAllUsers(){
		
		return UserRepository.findAll();
	}
	
	//create user Method
	public User createuser(User user) {
		
		return UserRepository.save(user);
	}
	//getuserById
	public Optional<User> getUserById(Long id) {
		Optional<User> user = UserRepository.findById(id);
		return user;
	}
	
	//updateuserbyid	
	public User updateUserById(Long id,User user) {		
		user.setId(id);
		return UserRepository.save(user);		
	}
	
	//delete user method
	public void deleteUserById(Long id) {
		UserRepository.deleteById(id);
		System.out.println("User id = "+id);
	}
	
	//getuserByUserName
	public User getUserByUserName(String username) {
		return UserRepository.findByUsername(username);
		
	}
	
}