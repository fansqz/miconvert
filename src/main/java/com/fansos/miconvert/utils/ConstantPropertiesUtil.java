package com.fansos.miconvert.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件中的常量
 * @author Diligence
 * @create 2022 - 10 - 11 23:51
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {
	@Value("${UPLOAD_SAVE_PATH}")
	private String uploadSavePath;

	public static String UPLOAD_SAVE_PATH;

	@Override
	public void afterPropertiesSet() throws Exception {
		UPLOAD_SAVE_PATH = uploadSavePath;
	}
}
