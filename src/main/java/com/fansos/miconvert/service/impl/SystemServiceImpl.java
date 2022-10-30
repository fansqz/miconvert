package com.fansos.miconvert.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fansos.miconvert.mapper.LoginInfoMapper;
import com.fansos.miconvert.model.pojo.UserInfo;
import com.fansos.miconvert.service.SystemService;
import org.springframework.stereotype.Service;

/**
 * @author Diligence
 * @create 2022 - 10 - 23 20:45
 */
@Service
public class SystemServiceImpl extends ServiceImpl<LoginInfoMapper, UserInfo> implements SystemService {

	/**
	 * 查询数据库，获取用户
	 * @param userName
	 * @return
	 */
	@Override
	public UserInfo getInfoByName(String userName) {
		QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
		wrapper.eq("userName", userName);
		UserInfo loginInfo = baseMapper.selectOne(wrapper);
		return loginInfo;
	}

	@Override
	public UserInfo saveUser(UserInfo newUser) {
		baseMapper.insert(newUser);
		QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
		wrapper.eq("userName", newUser.getUsername());
		return baseMapper.selectOne(wrapper);
	}
}
