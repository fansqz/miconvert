package com.fansos.miconvert.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fansos.miconvert.mapper.FormatMapper;
import com.fansos.miconvert.model.pojo.Format;
import com.fansos.miconvert.service.FormatService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FormatServiceImpl extends ServiceImpl<FormatMapper, Format> implements FormatService {

    /**
     * 查询数据库，获取可转换类型
     * @return 类型列表
     */
    @Override
    public List<Format> getFormats(String fileName) {
        String suffix = fileName.split("\\.")[1];
        QueryWrapper<Format> wrapper = new QueryWrapper<>();
        wrapper.eq("in_format", suffix);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<String> listAllOutputFormat() {
        List<Format> list = baseMapper.selectList(null);
        Set<String> set = new HashSet<>();
        list.forEach(a->set.add(a.getOutputFormat()));
        List<String> answer = new ArrayList<>(set.size());
        answer.addAll(set);
        return answer;
    }

    @Override
    public List<String> listInputFormatByOutputFormat(String outputFormat) {
        QueryWrapper<Format> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("out_format", outputFormat);
        List<Format> list = baseMapper.selectList(queryWrapper);
        Set<String> set = new HashSet<>();
        list.forEach(a->set.add(a.getInputFormat()));
        List<String> answer = new ArrayList<>(set.size());
        answer.addAll(set);
        return answer;
    }

}
