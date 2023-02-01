package com.fansos.miconvert.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fansos.miconvert.model.pojo.Format;

import java.util.List;

public interface FormatService extends IService<Format> {

    /**
     * 查询数据库，获取可转换类型
     * @param fileName 文件名称
     * @return 支持转换类型列表
     */
    List<Format> getFormats(String fileName);

    /**
     * 读取所有的输出格式
     * @return 输出格式列表
     */
    List<String> listAllOutputFormat();

    /**
     * 通过输出格式获取输入格式
     * @param outputFormat 输出格式
     * @return 输入格式列表
     */
    List<String> listInputFormatByOutputFormat(String outputFormat);
}
