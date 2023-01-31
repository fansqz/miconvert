package com.fansos.miconvert.controller;


import com.fansos.miconvert.constant.ResultCodeEnum;
import com.fansos.miconvert.model.result.Result;
import com.fansos.miconvert.service.ConvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;

/**
 * 游客相关的转换的Controller
 * 这些接口并不会去检测token
 * @author Diligence
 * @create 2022 - 10 - 10 15:06
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/convert")
public class ConvertController {

    @Autowired
    private ConvertService convertService;

    /**
     * 上传文件
     * @param file 文件
     * @return 成功信息
     */
    @PostMapping("/convertFile")
    public Result<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("toFormat") String toFormat) {
        if (file.isEmpty()) {
            return Result.fail(ResultCodeEnum.CUSTOM_SIMPLE_ERROR_MESSAGE);
        }
        if (file.getSize() <= 0) {
            return Result.fail(ResultCodeEnum.CUSTOM_SIMPLE_ERROR_MESSAGE);
        }

        String url = convertService.upload(file, toFormat);

        return Result.ok(url);
    }


    /**
     * 文件下载{}
     * 通过response输出流将文件传递到浏览器
     * @param response 下载文件
     */
    @GetMapping("/downloadFile/{fileName}")
    public void download(HttpServletResponse response, @PathVariable String fileName) {
        convertService.download(response, fileName);
    }
}
