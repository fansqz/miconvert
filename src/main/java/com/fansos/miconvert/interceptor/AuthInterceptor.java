package com.fansos.miconvert.interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author Diligence
 * @create 2022 - 10 - 30 14:51
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

	@Resource
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");

		String token = request.getHeader("token");
		if (StringUtils.isEmpty(token)) {
			response.getWriter().print("用户未登录，请登录后操作！");
			return false;
		}
		Object loginStatus = redisTemplate.opsForValue().get(token);
		if( Objects.isNull(loginStatus)){
			response.getWriter().print("token错误，请查看！");
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

	}
}
