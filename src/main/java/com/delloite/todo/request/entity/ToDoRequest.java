package com.delloite.todo.request.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonInclude(value=Include.NON_NULL)
@Data
public class ToDoRequest {	
	private Long id;
	private String title;
	private String description;
	
	@JsonProperty
	private boolean isChecked;
	private Long userId;
}
