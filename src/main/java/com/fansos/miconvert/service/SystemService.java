package com.fansos.miconvert.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fansos.miconvert.model.pojo.UserInfo;

/**
 * @author Diligence
 * @create 2022 - 10 - 23 20:45
 */
public interface SystemService extends IService<UserInfo> {
	/**
	 * 查询数据库，获取用户
	 * @param userName
	 * @return
	 */
	UserInfo getInfoByName(String userName);

	/**
	 *
	 * @param newUser
	 * @return
	 */
	UserInfo saveUser(UserInfo newUser);
}
