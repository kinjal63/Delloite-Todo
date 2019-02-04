package com.delloite.todo.response.entity;

import java.util.List;

import com.delloite.todo.domain.Todo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value=Include.NON_NULL)
public class ToDoListResponse extends GenericResponse {
	private List<Todo> todos;
	
	public ToDoListResponse(int resCode, String message, List<Todo> todos) {
		super(resCode, message);
		this.todos = todos;
	}
	
	ToDoListResponse() {
		super();
	}
	
	public List<Todo> getTodos() {
		return todos;
	}
}
