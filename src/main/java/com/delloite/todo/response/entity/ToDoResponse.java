package com.delloite.todo.response.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
public class ToDoResponse extends GenericResponse {
	private long id;
	
	public ToDoResponse(int resCode, String message, long id) {
		super(resCode, message);
		this.id = id;
	}
	
	ToDoResponse() {
		super();
	}
	
	public long getId() {
		return id;
	}
}
