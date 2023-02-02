package com.fansos.miconvert.controller;


import com.fansos.miconvert.constant.ResultCodeEnum;
import com.fansos.miconvert.model.result.Result;
import com.fansos.miconvert.service.ConvertService;
import com.fansos.miconvert.service.FormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @Autowired
    private FormatService formatService;

    /**
     * 获取所有的输出格式
     */
    @GetMapping("/listAllOutputFormat")
    public Result<?> ListAllOutputFormat() {
        return Result.ok(formatService.listAllOutputFormat());
    }

    /**
     * 通过输出格式获取输入格式列表
     */
    @GetMapping("/listInputFormatByOutputFormat")
    public Result<?> ListInputFormatByOutputFormat(@RequestParam("format") String format) {
        return Result.ok(formatService.listInputFormatByOutputFormat(format));
    }

    /**
     * 尝试秒传
     * @param key 原文件的16进制md5
     */
    @PostMapping("/fastConvertFile")
    public Result<?> uploadFile(@RequestParam("key") String key, @RequestParam("format") String format) throws IOException {
        return Result.ok(convertService.fastConvert(key, format));
    }

    /**
     * 上传文件
     * @param file 文件
     * @return 成功信息
     */
    @PostMapping("/convertFile")
    public Result<?> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("outputFormat") String toFormat) throws IOException {
        if (file.isEmpty()) {
            return Result.fail(ResultCodeEnum.CUSTOM_SIMPLE_ERROR_MESSAGE);
        }
        if (file.getSize() <= 0) {
            return Result.fail(ResultCodeEnum.CUSTOM_SIMPLE_ERROR_MESSAGE);
        }

        String fileName = convertService.convert(file, toFormat);

        return Result.ok(fileName);
    }

    /**
     * 文件下载
     * 通过response输出流将文件传递到浏览器
     * @param response 下载文件
     */
    @GetMapping("/downloadFile/{fileName}")
    public void download(HttpServletResponse response, @PathVariable String fileName) {
        convertService.download(response, fileName);
    }
}
