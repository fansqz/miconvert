package com.fansos.miconvert.model.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Diligence
 * @create 2022 - 10 - 13 0:26
 */
@Data
@TableName("format_converts")
public class Format {
	@TableId(value = "id",type = IdType.AUTO)
	private Integer id;
	@TableField(value = "in_format")
	private String inputFormat;
	@TableField(value = "out_format")
	private String outputFormat;
	private String convertUtil;
}
