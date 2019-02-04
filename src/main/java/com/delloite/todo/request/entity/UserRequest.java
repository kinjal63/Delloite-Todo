package com.delloite.todo.request.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(content=Include.NON_NULL)
public class UserRequest {
	
	private String username;
	private String password;
}
