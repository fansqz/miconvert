package com.fansos.miconvert.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fansos.miconvert.model.pojo.Format;

import java.util.List;

public interface FormatService extends IService<Format> {

    /**
     * 查询数据库，获取可转换类型
     * @param fileName
     * @return
     */
    List<Format> getFormats(String fileName);
}
