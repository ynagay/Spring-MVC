package com.jb.app.ws.exeptions;

public class UserServiceException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5987975407329485464L;

	public UserServiceException(){};
	
	public UserServiceException(String message){
		
		super(message);
	};

}
