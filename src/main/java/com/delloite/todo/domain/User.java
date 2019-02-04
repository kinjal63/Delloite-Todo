package com.delloite.todo.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
//@Table(catalog=ToDo.Database.CATALOG_NAME)
@Data
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue
	private Long id;
	private @NonNull String username;
	private @NonNull String password;
	
	@OneToMany(mappedBy="user")
	private List<Todo> todoList;
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
}
