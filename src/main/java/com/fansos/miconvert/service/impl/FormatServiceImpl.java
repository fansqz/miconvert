package com.fansos.miconvert.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fansos.miconvert.mapper.FormatMapper;
import com.fansos.miconvert.model.pojo.Format;
import com.fansos.miconvert.service.FormatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormatServiceImpl extends ServiceImpl<FormatMapper, Format> implements FormatService {

    /**
     * 查询数据库，获取可转换类型
     * @return
     */
    @Override
    public List<Format> getFormats(String fileName) {
        String suffix = fileName.split("\\.")[1];
        QueryWrapper<Format> wrapper = new QueryWrapper<>();
        wrapper.eq("in_format", suffix);
        List<Format> formats = baseMapper.selectList(wrapper);
        return formats;
    }
}
