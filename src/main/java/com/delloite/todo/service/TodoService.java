package com.delloite.todo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delloite.todo.domain.Todo;
import com.delloite.todo.domain.User;
import com.delloite.todo.repository.TodoRepository;
import com.delloite.todo.request.entity.ToDoRequest;

@Service
public class TodoService implements IToDoService {
	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public List<Todo> getAll(Long userId) {
		List<Todo> todos = new ArrayList<Todo>();
		
		User user = userService.findById(userId);
		todos.addAll(user.getTodoList());
		return todos;
	}
	
	@Override
	public Long addOrUpdateTodo(ToDoRequest todo) {
		Todo td = new Todo(todo.getTitle(), todo.getDescription(), todo.isChecked(), new Date());
		if(todo.getId() != null) {
			td.setId(todo.getId());
		}
		td.setUser(userService.findById(todo.getUserId()));
		Todo todoObj = todoRepository.save(td);
		return todoObj != null ? todoObj.getId() : -1;
	}

	@Override
	public boolean removeToDo(Long id) {
		if(!todoRepository.existsById(id)) {
			return false;
		}
		todoRepository.deleteById(id);
		return true;
	}	
}
