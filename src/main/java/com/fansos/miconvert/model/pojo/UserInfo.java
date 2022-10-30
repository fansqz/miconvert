package com.fansos.miconvert.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author Diligence
 * @create 2022 - 10 - 23 20:39
 */
@Data
public class UserInfo {
	@TableId(value = "id",type = IdType.AUTO)
	private int userId;
	private String username;
	private String password;
	private String email;
	private String verifiCode;
}
