package com.delloite.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.delloite.todo.filter.RequestIntercepter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	RequestIntercepter requestInterceptor;

	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor).addPathPatterns("/todo/*", "/user/*");
    }
}
