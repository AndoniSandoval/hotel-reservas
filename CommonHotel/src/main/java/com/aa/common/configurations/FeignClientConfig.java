package com.aa.common.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class FeignClientConfig {

	@Bean
	RequestInterceptor requestInterceptor() {
		return (RequestTemplate template) -> {
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			if(attributes != null) {
				HttpServletRequest request = attributes.getRequest();
				String authorizationHader = request.getHeader("Authorization");
				if (authorizationHader != null && authorizationHader.startsWith("Bearer ")) {
					template.header("Authorization",authorizationHader);
				}
			}
		};
	}
	
}
