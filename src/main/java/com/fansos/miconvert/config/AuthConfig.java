package com.fansos.miconvert.config;

import com.fansos.miconvert.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
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

	/**
	 * 跨域
	 * @param registry
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				//是否发送Cookie
				.allowCredentials(true)
				//放行哪些原始域
				.allowedOriginPatterns("*")
				// .allowedOrigins("*")
				.allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
				.allowedHeaders("*")
				.exposedHeaders("*");
	}

	/**
	 * 拦截
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		String[] addPathPatterns ={
				//输入要拦截路径
				// "/system/**",   //拦截所有路径 ，之后再决定放行
				// "convert/**"
				"/**"
		};
		//定义排除放行内容
		//要排除路径，排除的路径说明不需要用户登录也可以访问
		String[] excludePathPatterns={
				"/system/login",  //排除掉登录页面
				"/system/register",
				"/system/test",
				"convert/test"
		};
		registry.addInterceptor(initAuthInterceptor())
				.addPathPatterns(addPathPatterns)
				.excludePathPatterns(excludePathPatterns);
	}
}
