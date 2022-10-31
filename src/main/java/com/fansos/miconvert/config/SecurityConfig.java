package com.fansos.miconvert.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring security的配置类,自动放行，由后面 token 认证
 * @author Diligence
 * @create 2022 - 10 - 30 15:30
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and().csrf().disable();
	}
}
