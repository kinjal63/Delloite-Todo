package com.delloite.todo;

import org.apache.catalina.filters.RemoteAddrFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//public class IPConfig {
//	
//	@Bean
//	public FilterRegistrationBean<RemoteAddrFilter> remoteAddressFilter() {
//	    FilterRegistrationBean<RemoteAddrFilter> filterRegistrationBean = new FilterRegistrationBean<RemoteAddrFilter>();
//	    RemoteAddrFilter filter = new RemoteAddrFilter();
//
//	    filter.setAllow("127\\.0\\.0\\.1");
//	    filter.setDenyStatus(403);
//
//	    filterRegistrationBean.setFilter(filter);
//	    filterRegistrationBean.addUrlPatterns("/*");
//
//	    return filterRegistrationBean;
//	}
//}
