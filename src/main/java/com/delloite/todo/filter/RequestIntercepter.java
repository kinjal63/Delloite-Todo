package com.delloite.todo.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.delloite.todo.utils.TokenUtil;

@Component
public class RequestIntercepter extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getRequestURI().endsWith("/login"))
			return true;
		
		String authHeader = request.getHeader("Authorization");
		if(StringUtils.isEmpty(authHeader)) {
			response.sendError(502, "Bad Request");
			return false;
		}
		
		String token = authHeader.split(" ")[1];
		Long userId = TokenUtil.verifyToken(token);
		if(userId == null) {
			response.sendError(401, "Unauthorized");
			return false;
		}
		
		request.setAttribute("userId", userId);
		return true;
	}
}
