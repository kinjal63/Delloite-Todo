package com.delloite.todo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.delloite.todo.domain.Todo;
import com.delloite.todo.request.entity.ToDoRequest;
import com.delloite.todo.response.entity.GenericResponse;
import com.delloite.todo.response.entity.ResponseCode;
import com.delloite.todo.response.entity.ToDoListResponse;
import com.delloite.todo.response.entity.ToDoResponse;
import com.delloite.todo.service.TodoService;

@Controller
@RequestMapping(value="/todo")
public class ToDoController {
	@Autowired
	private TodoService toDoservice;
	
	@GetMapping(value="/getAll", produces="application/json")
	@ResponseBody
	public ResponseEntity<ToDoListResponse> getAllTodos(HttpServletRequest req) {
		Long userId = (Long) req.getAttribute("userId");
		List<Todo> todos = toDoservice.getAll(userId);
		ResponseCode resCode = ResponseCode.TODO_RETRIVED;
		if(todos == null) {
			resCode = ResponseCode.TODO_RETRIEVE_FAILED;
		}
		return new ResponseEntity<ToDoListResponse>(new ToDoListResponse(resCode.getCode(), resCode.getMessage(), todos), HttpStatus.OK);
	}

	@PostMapping(value="/addTodo", consumes="application/json", produces="application/json")
	@ResponseBody
	public ResponseEntity<ToDoResponse> addToDO(@RequestBody ToDoRequest todo, HttpServletRequest req) {
		Long userId = (Long) req.getAttribute("userId");
		todo.setUserId(userId);
		
		long id = toDoservice.addOrUpdateTodo(todo);
		ResponseCode resCode = ResponseCode.TODO_CREATED;
		if(id == -1) {
			resCode = ResponseCode.TODO_CREATION_FAILED;
		}
		return new ResponseEntity<ToDoResponse>(new ToDoResponse(resCode.getCode(), resCode.getMessage(), id), HttpStatus.CREATED);
	}
	
	@PostMapping(value="/updateTodo", consumes="application/json", produces="application/json")
	@ResponseBody
	public ResponseEntity<GenericResponse> updateToDO(@RequestBody ToDoRequest todo, HttpServletRequest req) {
		Long userId = (Long) req.getAttribute("userId");
		todo.setUserId(userId);
		
		long id = toDoservice.addOrUpdateTodo(todo);
		ResponseCode resCode = ResponseCode.TODO_UPDATED;
		if(id == -1) {
			resCode = ResponseCode.TODO_UPDATE_FAILED;
		}
		return new ResponseEntity<GenericResponse>(new GenericResponse(resCode.getCode(), resCode.getMessage()), HttpStatus.OK);
	}
	
	@PostMapping(value="/removeTodo", consumes="application/x-www-form-urlencoded", produces="application/json")
	@ResponseBody
	public ResponseEntity<GenericResponse> removeToDO(@RequestParam("todo_id") Long id) {
		boolean isRemoved = toDoservice.removeToDo(id);
		ResponseCode resCode = ResponseCode.TODO_REMOVED;
		if(!isRemoved) {
			resCode = ResponseCode.TODO_REMOVE_FAILED;
		}
		return new ResponseEntity<GenericResponse>(new GenericResponse(resCode.getCode(), resCode.getMessage()), HttpStatus.OK);
	}
	
	@RequestMapping(value="/")
	public String display() {
		return "index";
	}
}
