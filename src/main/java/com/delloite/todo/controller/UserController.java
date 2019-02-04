package com.delloite.todo.controller;

import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.delloite.todo.request.entity.UserRequest;
import com.delloite.todo.response.entity.GenericResponse;
import com.delloite.todo.response.entity.ResponseCode;
import com.delloite.todo.response.entity.UserResponse;
import com.delloite.todo.service.UserService;
import com.delloite.todo.utils.TokenUtil;

@Controller
@RequestMapping(value="/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping(value="/login", consumes="application/json", produces="application/json")
	@ResponseBody
	public ResponseEntity<GenericResponse> login(@RequestBody UserRequest user) throws NoSuchPaddingException {		
		String token = userService.login(user);
		
		ResponseCode resCode = ResponseCode.USER_LOGIN_SUCCESS;
		if(StringUtils.isEmpty(token)) {
			resCode = ResponseCode.USER_LOGIN_FAIL;
		}
		return new ResponseEntity<GenericResponse>(new UserResponse(resCode.getCode(), resCode.getMessage(), token), HttpStatus.OK);
	}
}
