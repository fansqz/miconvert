package com.fansos.miconvert.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Diligence
 * @create 2022 - 10 - 30 14:38
 */
@Service
public class RedisService {
	@Resource
	private RedisTemplate<String,Object> redisTemplate;

	public void set(String key, Object value) {
		//更改在redis里面查看key编码问题
		RedisSerializer redisSerializer =new StringRedisSerializer();
		redisTemplate.setKeySerializer(redisSerializer);
		ValueOperations<String,Object> vo = redisTemplate.opsForValue();
		vo.set(key, value);
	}

	public Object get(String key) {
		ValueOperations<String,Object> vo = redisTemplate.opsForValue();
		return vo.get(key);
	}

	public Boolean delete(String key) {
		return redisTemplate.delete(key);
	}
}
