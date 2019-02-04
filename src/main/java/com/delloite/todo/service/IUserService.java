package com.delloite.todo.service;

import com.delloite.todo.domain.User;
import com.delloite.todo.request.entity.UserRequest;

public interface IUserService {
	String login(UserRequest user);
	User findById(Long userId);
}
