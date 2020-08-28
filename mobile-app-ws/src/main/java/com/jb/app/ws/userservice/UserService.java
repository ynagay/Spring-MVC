package com.jb.app.ws.userservice;

import com.jb.app.ws.ui.model.request.UserDetailsRequestModel;
import com.jb.app.ws.ui.model.response.UserRest;

public interface UserService {

	UserRest createUser(UserDetailsRequestModel userDetails);
}
