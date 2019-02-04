package com.delloite.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.delloite.todo.domain.User;
import com.delloite.todo.repository.UserRepository;

@Component
public class ToDoBootRunner implements CommandLineRunner {
	@Autowired
	private UserRepository userRepository;
	
	public void run(String... args) throws Exception {
		User user = new User("test", "password");
		userRepository.save(user);
		
		User user1 = new User("test12", "password12");
		userRepository.save(user1);
		
		User user2 = new User("test13", "password13");
		userRepository.save(user2);
		
		System.out.println("saved in DB");
	}
}
