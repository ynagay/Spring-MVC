package com.jb.app.ws.ui.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
//import java.awt.PageAttributes.MediaType;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jb.app.ws.exeptions.UserServiceException;
import com.jb.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.jb.app.ws.ui.model.request.UserDetailsRequestModel;
import com.jb.app.ws.ui.model.response.UserRest;

@RestController
@RequestMapping("users")//http://localhost:8080/users

public class UserController {
	
	Map<String, UserRest> users;
	
	@GetMapping
	public String getUsers(@RequestParam(value="page", defaultValue = "1") int page,
			@RequestParam(value="limit", defaultValue = "50") int limit,
			@RequestParam(value="sort", defaultValue ="desc", required = false) String sort){
		
		//if(sort == null) sort = "desc"; //NullPointerExeption
		return "get user was called with page = " + page + " and limit = " + limit + " and sort = " + sort;
	}
	
	@GetMapping(path="/{userId}",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE} )
	public ResponseEntity <UserRest> getUser(@PathVariable String userId){
		
		if(true) throw new UserServiceException("A user service exception is thrown");
		
		
		if(users.containsKey(userId))
		{
			return new ResponseEntity<>(users.get(userId),HttpStatus.OK);			
		} else {
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);	
		}
		
		/*UserRest returnValue = new UserRest();
		returnValue.setEmail("test@test.ru");
		returnValue.setFirstName("Jane");
		returnValue.setLastName("Born");
		//returnValue.setUserId("123");
		 * */ //hardcoded value
		

	}

	@PostMapping (consumes = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE},
				produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
		
		UserRest returnValue = new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		returnValue.setPassword(userDetails.getPassword());
		//returnValue.setUserId("123");
		
		String userId = UUID.randomUUID().toString(); //generate random userId
		returnValue.setUserId(userId);
		
		if(users == null) users = new HashMap<>();
		users.put(userId, returnValue);

		
		return new ResponseEntity<UserRest>(returnValue,HttpStatus.OK);
	}
	
	@PutMapping(path="/{userId}",
				consumes = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE},
				produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE})
	
	public UserRest updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel userDetails) {
		
		UserRest storedUserDetails = users.get(userId);
		storedUserDetails.setFirstName(userDetails.getFirstName());
		storedUserDetails.setLastName(userDetails.getLastName());
		
		users.put(userId, storedUserDetails);
		
		return storedUserDetails;
	}
	
	@DeleteMapping(path="/{id}")
	
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		
		users.remove(id);
		
		return ResponseEntity.noContent().build();	
	}
}
