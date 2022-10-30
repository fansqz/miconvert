package com.fansos.miconvert.model.pojo;

import lombok.Data;

/**
 * @author Diligence
 * @create 2022 - 10 - 23 20:39
 */
@Data
public class UserInfo {
	private String username;
	private String password;
	private String email;
	private String verifiCode;
}
