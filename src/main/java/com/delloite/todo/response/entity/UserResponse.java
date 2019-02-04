package com.delloite.todo.response.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
public class UserResponse extends GenericResponse {
	private String authToken;
	
	public UserResponse(int resCode, String message, String authToken) {
		super(resCode, message);
		this.authToken = authToken;
	}
	
	UserResponse() {
		super();
	}
	
	public String getAuthToken() {
		return authToken;
	}
}
