package com.delloite.todo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.delloite.todo.domain.Todo;

public interface TodoRepository extends CrudRepository<Todo, Long> {
	public List<Todo> findByUser(Long userId);
}	
