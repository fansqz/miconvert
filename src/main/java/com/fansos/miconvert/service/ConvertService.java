package com.fansos.miconvert.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fansos.miconvert.model.pojo.Format;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Diligence
 * @create 2022 - 10 - 12 0:07
 */
public interface ConvertService{


    /**
     * 上传文件，并解析文件
     * @param file 文件
     * @param outputFormat 目标格式
     * @return 新文件名称
     */
	String convert(MultipartFile file, String outputFormat) throws IOException;

    /**
     * 尝试快速解析
     * @param key 原文件的16进制md5
     * @return 转换以后文件url
     */
    String fastConvert(String key, String outputFormat) throws IOException;

    /**
     * 下载文件
     * @param response response
     * @param fileName 文件名称
     */
	void download(HttpServletResponse response, @PathVariable String fileName);

}
