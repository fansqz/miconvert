package com.fansos.miconvert.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fansos.miconvert.mapper.FormatMapper;
import com.fansos.miconvert.model.pojo.Format;
import com.fansos.miconvert.service.FileService;
import com.fansos.miconvert.utils.ConstantPropertiesUtil;
import com.fansos.miconvert.utils.ConvertUtil;
import com.fansos.miconvert.utils.TimingDelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

/**
 * @author Diligence
 * @create 2022 - 10 - 12 0:07
 */
@Service
@Slf4j
public class FileServiceImpl extends ServiceImpl<FormatMapper, Format> implements FileService {
	/**
	 * 上传文件
	 * @param file
	 * @return
	 */
	@Override
	public String upload(MultipartFile file, String toFormat) {
		String fileName = file.getOriginalFilename();
		//生成随机唯一值，使用uuid，添加到文件名称里面
		String uuid = UUID.randomUUID().toString().replaceAll("-", "") + "_";
		fileName = uuid + fileName;

		//获取项目运行的绝对路径
		String filePath = System.getProperty("user.dir");
		String newFilePath = filePath + "\\demo-upload\\";
		File file1 = new File(newFilePath);
		if (!file1.exists()) {
			file1.mkdirs();
		}

		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(newFilePath + fileName);
			fileOutputStream.write(file.getBytes());
			fileOutputStream.flush();
			fileOutputStream.close();

			/**
			 * 转换
			 */
			try {
				if (fileName.split("\\.")[1].equals("pdf")) {
					ConvertUtil.pdf2docxConvert(newFilePath, toFormat);
				} else {
					ConvertUtil.sofficeConvert(newFilePath + fileName, toFormat, newFilePath);
				}
			} finally {
				Runnable runnable = new TimingDelUtil();
				Thread thread = new Thread(runnable);
				thread.start();
				log.info("定时删除文件线程启动.........");
			}
			log.info("localhost:8080/fans/convert/download/" + fileName);
			return "localhost:8080/fans/convert/download/" + fileName;
			// return Result.ok();
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 下载目标文件
	 * @param response
	 * @param fileName
	 * @return
	 */
	@Override
	public String download(HttpServletResponse response, String fileName) {
		Path path = Paths.get(ConstantPropertiesUtil.UPLOAD_SAVE_PATH, fileName);

		if (Files.exists(path)) {
			//获取文件的后缀名
			String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			response.setContentType("application/" + fileSuffix);

			/**
			 * 添加http头信息
			 */
			try {
				response.addHeader("Content-Disposition", "attachment;filename="
						+ new String(fileName.getBytes("UTF-8"), "ISO8859-1"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			/**
			 * 使用  Path 和response输出流将文件输出到浏览器
			 */
			try {
				Files.copy(path, response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

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
