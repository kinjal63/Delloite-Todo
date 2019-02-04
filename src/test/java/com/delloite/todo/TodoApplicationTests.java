package com.delloite.todo;

import java.nio.charset.Charset;
import java.util.Random;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.delloite.todo.controller.ToDoController;
import com.delloite.todo.request.entity.ToDoRequest;
import com.delloite.todo.request.entity.UserRequest;
import com.delloite.todo.response.entity.GenericResponse;
import com.delloite.todo.response.entity.ToDoListResponse;
import com.delloite.todo.response.entity.ToDoResponse;
import com.delloite.todo.response.entity.UserResponse;

/**
 * Created by kinjal.patel on 21/7/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TodoApplicationTests {
	@LocalServerPort
	private int port;
	
	@Autowired
	TestRestTemplate restTemplate;
	@Autowired
	ToDoController todoController;
	
	private String token;
	
	private String constructUrl(String path) {
		return "http://localhost:" + port + path; 
	}
	
	public HttpHeaders addHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Bearer " + token);
		return headers;
	}
	
	@Before
	public void setUp() {
		Assertions.assertThat(restTemplate).isNotEqualTo(null);
		Assertions.assertThat(todoController).isNotEqualTo(null);
		
		loginUser();
	}
	
	private void loginUser() {
		UserRequest userRequest = new UserRequest();
		userRequest.setUsername("test");
		userRequest.setPassword("password");
		
		HttpEntity<UserRequest> httpEntity = new HttpEntity<>(userRequest, addHeaders());
		ResponseEntity<UserResponse> response = restTemplate.exchange(constructUrl("/user/login"), HttpMethod.POST, httpEntity, UserResponse.class);
		
		Assertions.assertThat(response).isNotEqualTo(null);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		token = response.getBody().getAuthToken();
	}
	
	@Test
	public void postTodoTask() {
		ToDoRequest todoRequest = getTodoRequest();
		HttpEntity<ToDoRequest> httpEntity = new HttpEntity<>(todoRequest, addHeaders());
		ResponseEntity<ToDoResponse> response = restTemplate.exchange(constructUrl("/todo/addTodo"), HttpMethod.POST, httpEntity, ToDoResponse.class);
		
		Assertions.assertThat(response).isNotEqualTo(null);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
	}
	
	@Test
	public void updateTodoTask() {
		ToDoRequest todoRequest = getTodoRequest();
		HttpEntity<ToDoRequest> httpEntity = new HttpEntity<>(todoRequest, addHeaders());
		ResponseEntity<ToDoResponse> response = restTemplate.exchange(constructUrl("/todo/updateTodo"), HttpMethod.POST, httpEntity, ToDoResponse.class);
		
		Assertions.assertThat(response).isNotEqualTo(null);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void removeTodoTask() {
		removeTask(new Long(1));
	}
	
	public void removeTask(Long id) {
		if(id == null || id.intValue() == 0)
			id = new Long(1);
		
		ResponseEntity<GenericResponse> response = restTemplate.exchange(constructUrl("/todo/removeTodo?todo_id="+id),  HttpMethod.POST, new HttpEntity<>(addHeaders()), GenericResponse.class);
		
		Assertions.assertThat(response).isNotEqualTo(null);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void getAll() {
		postTodoTask();
		postTodoTask();

		ResponseEntity<ToDoListResponse> response = restTemplate.exchange(constructUrl("/todo/getAll"),  HttpMethod.GET, new HttpEntity<>(addHeaders()), ToDoListResponse.class);

		Assertions.assertThat(response).isNotEqualTo(null);
		Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		Assertions.assertThat(response.getBody().getTodos().size()).isEqualTo(3);
		
		response.getBody().getTodos().stream().forEach(todo -> {
			removeTask(todo.getId());
		});
	}
	
	private ToDoRequest getTodoRequest() {
		ToDoRequest todoRequest = new ToDoRequest();
		todoRequest.setTitle(generateRandomString());
		todoRequest.setDescription(generateLongRandomString());
		todoRequest.setChecked(false);
		todoRequest.setUserId(new Long(1));
		
		return todoRequest;
	}
	
	private String generateRandomString() {
		byte[] array = new byte[7];
	    new Random().nextBytes(array);
	    return new String(array, Charset.forName("UTF-8"));
	}
	
	private String generateLongRandomString() {
		byte[] array = new byte[100];
	    new Random().nextBytes(array);
	    return new String(array, Charset.forName("UTF-8"));
	}

}
