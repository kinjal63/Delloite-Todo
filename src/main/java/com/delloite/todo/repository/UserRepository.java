package com.delloite.todo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.delloite.todo.domain.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsernameAndPassword(String userName, String password);

}
