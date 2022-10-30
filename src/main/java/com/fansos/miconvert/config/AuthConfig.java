package com.fansos.miconvert.config;

import com.fansos.miconvert.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Diligence
 * @create 2022 - 10 - 30 14:52
 */
@Configuration
public class AuthConfig implements WebMvcConfigurer {

	@Bean
	public AuthInterceptor initAuthInterceptor(){
		return new AuthInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(initAuthInterceptor()).addPathPatterns("/**").excludePathPatterns("/**/login/**");
	}

}
