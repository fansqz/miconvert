package com.fansos.miconvert.model.pojo;

import lombok.Data;

/**
 * @author Diligence
 * @create 2022 - 10 - 23 20:39
 */
@Data
public class LoginInfo {
	private Integer userId;
	private String username;
	private String password;
	private String verifiCode;
}
