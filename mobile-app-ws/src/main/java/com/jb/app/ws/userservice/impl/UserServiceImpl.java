package com.jb.app.ws.userservice.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jb.app.ws.ui.model.request.UserDetailsRequestModel;
import com.jb.app.ws.ui.model.response.UserRest;
import com.jb.app.ws.userservice.UserService;

@Service
public class UserServiceImpl implements UserService{

	Map<String, UserRest> users;
	@Override
	public UserRest createUser(UserDetailsRequestModel userDetails) {
		
		UserRest returnValue = new UserRest();
		returnValue.setEmail(userDetails.getEmail());
		returnValue.setFirstName(userDetails.getFirstName());
		returnValue.setLastName(userDetails.getLastName());
		returnValue.setPassword(userDetails.getPassword());
				
		String userId = UUID.randomUUID().toString(); //generate random userId
		returnValue.setUserId(userId);
		
		if(users == null) users = new HashMap<>();
		users.put(userId, returnValue);
		
		return returnValue;

	}

}
