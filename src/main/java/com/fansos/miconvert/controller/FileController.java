package com.fansos.miconvert.controller;


import com.fansos.miconvert.constant.ResultCodeEnum;
import com.fansos.miconvert.model.pojo.Format;
import com.fansos.miconvert.model.result.Result;
import com.fansos.miconvert.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Diligence
 * @create 2022 - 10 - 10 15:06
 */
@Controller
@CrossOrigin
@ResponseBody
@RequestMapping("/convert")
public class FileController {

	@Autowired
	private FileService fileService;

	/**
	 * 查询数据库，获取可转换类型
	 * @return
	 */
	@GetMapping("/getSupportFormat")
	public Result getFormat(@RequestParam("fileName") String fileName) {
		List<Format> formats = fileService.getFormats(fileName);
		return Result.ok(formats);
	}

	/**
	 * 上传文件
	 * @param file
	 * @return
	 */
	@PostMapping("/convertFile")
	public Result uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("toFormat") String toFormat) {
		if (file.isEmpty()) {
			return Result.fail(ResultCodeEnum.CUSTOM_SIMPLE_ERROR_MESSAGE);
		}
		if (file.getSize() <= 0) {
			return Result.fail(ResultCodeEnum.CUSTOM_SIMPLE_ERROR_MESSAGE);
		}

		String url = fileService.upload(file, toFormat);

		return Result.ok(url);
	}


	/**
	 * 文件下载{}
	 * 通过response输出流将文件传递到浏览器
	 * @param response
	 */
	@GetMapping("/downloadFile/{fileName}")
	public Result download(HttpServletResponse response, @PathVariable String fileName) {
		fileService.download(response, fileName);
		return Result.ok();
	}
}
