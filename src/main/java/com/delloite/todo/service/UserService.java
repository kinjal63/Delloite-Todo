package com.delloite.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.delloite.todo.domain.User;
import com.delloite.todo.repository.UserRepository;
import com.delloite.todo.request.entity.UserRequest;
import com.delloite.todo.utils.TokenUtil;

@Service
public class UserService implements IUserService {
	@Autowired
	UserRepository userRepository;
	
	public User findById(Long userId) {
		return userRepository.findById(userId).get();
	}
	
	@Override
	public String login(UserRequest user) {
		User u = userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
		if(u != null) {
			return generateToken(u);
		}
		return null;
	}
	
	private String generateToken(User u) {
		return TokenUtil.generateToken(u);
	}
}
