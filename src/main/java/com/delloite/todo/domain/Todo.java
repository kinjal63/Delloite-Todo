package com.delloite.todo.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
//@Table(catalog=ToDo.Database.CATALOG_NAME)
@NoArgsConstructor
@RequiredArgsConstructor
public class Todo {
	@Id
	@GeneratedValue
	@Setter
	@Getter
	private Long id;
	
	@Setter
	@Getter
	private @NonNull String title;
	
	@Setter
	@Getter
	private @NonNull String description;
	
	@Getter
	@Setter
	private @NonNull boolean isChecked;
	
	@Setter
	@Getter
	private @NonNull Date updatedAt;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@Setter
	@Getter
	@JsonIgnore
	private User user;

}
