package com.fansos.miconvert.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fansos.miconvert.model.pojo.Format;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Diligence
 * @create 2022 - 10 - 12 0:07
 */
public interface ConvertService{


    /**
     * 上传文件
     * @param file
     * @param toFormat
     * @return
     */
	String upload(MultipartFile file, String toFormat);

    /**
     * 下载文件
     * @param response
     * @param fileName
     * @return
     */
	void download(HttpServletResponse response, @PathVariable String fileName);

}
