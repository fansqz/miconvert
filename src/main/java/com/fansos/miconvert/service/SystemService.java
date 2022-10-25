package com.fansos.miconvert.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fansos.miconvert.model.pojo.Format;
import com.fansos.miconvert.model.pojo.LoginInfo;

/**
 * @author Diligence
 * @create 2022 - 10 - 23 20:45
 */
public interface SystemService extends IService<LoginInfo> {
	/**
	 * 查询数据库，获取用户
	 * @param userName
	 * @return
	 */
	LoginInfo getInfoByName(String userName);
}
