package com.delloite.todo.response.entity;

public enum ResponseCode {
	USER_LOGIN_SUCCESS(1001, "User is logged in."),
	TODO_CREATED(1002, "To do is created."),
	TODO_REMOVED(1003, "To do removed."),
	TODO_UPDATED(1004, "To do updated."),
	TODO_RETRIVED(1005, "To do retrieved."),
	
	USER_LOGIN_FAIL(5001, "Invalid username/password."),
	TODO_CREATION_FAILED(5002, "Error in creating to do."),
	TODO_REMOVE_FAILED(5003, "Error in removing todo."),
	TODO_UPDATE_FAILED(5004, "To do update failed."),
	TODO_RETRIEVE_FAILED(5005, "Error in retrieving todos.");

	private int code;
	private String message;
	
	ResponseCode(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getMessage() {
		return this.message;
	}
 }
