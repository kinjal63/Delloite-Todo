package com.delloite.todo.service;

import java.util.List;

import com.delloite.todo.domain.Todo;
import com.delloite.todo.request.entity.ToDoRequest;

public interface IToDoService {
	List<Todo> getAll(Long userId);
	Long addOrUpdateTodo(ToDoRequest todo);
	boolean removeToDo(Long id);
}
