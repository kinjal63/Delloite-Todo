package com.delloite.todo.response.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@JsonInclude(value=Include.NON_NULL)
@NoArgsConstructor
@RequiredArgsConstructor
public class GenericResponse {
	@Getter
	@Setter
	private @NonNull Integer resCode;
	
	@Getter
	@Setter
	private @NonNull String message;
}
