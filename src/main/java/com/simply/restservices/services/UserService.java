package com.simply.restservices.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.simply.restservices.entities.User;
import com.simply.restservices.exceptions.UserExistsException;
import com.simply.restservices.exceptions.UserNotFoundException;
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
	public User createuser(User user) throws UserExistsException {
		// if user exist using username
		User existinguser = UserRepository.findByUsername(user.getUsername());
		if(existinguser != null) {
			throw new UserExistsException("User Is Exists in User Repository");
		}
		return UserRepository.save(user);
	}
	
	
	//getuserById
	public Optional<User> getUserById(Long id) throws UserNotFoundException{
		Optional<User> user = UserRepository.findById(id);
		if(!user.isPresent()) {
			throw new UserNotFoundException("User Not found In User Repository");
		}
		return user;
	}
	
	//updateuserbyid	
	public User updateUserById(Long id,User user) throws UserNotFoundException{	
		Optional<User> optionaluser = UserRepository.findById(id);
		if(!optionaluser.isPresent()) {
			throw new UserNotFoundException("User Not found in user Repository, provide the correct user id");
		}
		user.setId(id);
		return UserRepository.save(user);
	}
	
	//delete user method
	public void deleteUserById(Long id) {
		/* if(!UserRepository.findById(id).isPresent()) {
		UserRepository.deleteById(id);
		} */		
		Optional<User> optionaluser = UserRepository.findById(id);
		if(!optionaluser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Not found in user Repository, provide the correct user id");
		}
		UserRepository.deleteById(id);				
	}
	
	//getuserByUserName
	public User getUserByUserName(String username) {
		return UserRepository.findByUsername(username);
		
	}
	
}