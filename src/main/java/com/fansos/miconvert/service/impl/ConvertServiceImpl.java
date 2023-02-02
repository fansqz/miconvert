package com.fansos.miconvert.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fansos.miconvert.constant.RedisConstant;
import com.fansos.miconvert.exception.ConvertFalseException;
import com.fansos.miconvert.exception.FastConvertFalseException;
import com.fansos.miconvert.exception.ServerErrorException;
import com.fansos.miconvert.mapper.FormatMapper;
import com.fansos.miconvert.model.pojo.Format;
import com.fansos.miconvert.service.ConvertService;
import com.fansos.miconvert.service.FormatService;
import com.fansos.miconvert.utils.ConstantPropertiesUtil;
import com.fansos.miconvert.utils.ConvertUtil;
import com.fansos.miconvert.utils.MD5Util;
import com.fansos.miconvert.utils.TimingDelUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.soap.Addressing;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * @author Diligence
 * @create 2022 - 10 - 12 0:07
 */
@Service
@Slf4j
public class ConvertServiceImpl implements ConvertService {

	@Autowired
	private FormatService formatService;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	@Override
	public String convert(MultipartFile file, String outputFormat) throws IOException {
		// 读取oldFormat
		int t = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");
		String suffix = "";
		if (t != -1) {
			suffix = file.getOriginalFilename().substring(t + 1);
		}
		//生成随机唯一值，使用uuid，添加到文件名称里面
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		String oldFileName = uuid + '.' + suffix;
		String newFileName = uuid + '.' + outputFormat;
		//获取项目运行的绝对路径，统一用 "/"
		String filePath = System.getProperty("user.dir") + "/demo-upload/";
		File file1 = new File(filePath);
		if (!file1.exists()) {
			file1.mkdirs();
		}

		try (FileOutputStream fileOutputStream = new FileOutputStream(filePath + oldFileName)){
			// 上传文件
			fileOutputStream.write(file.getBytes());
			fileOutputStream.flush();
			fileOutputStream.close();
			// 写入redis中
			String oldFileMD5 = MD5Util.getFileMD5String(new File(filePath + oldFileName));
			redisTemplate.opsForValue().set(RedisConstant.FILE_PREFIX + oldFileMD5, oldFileName);
			//转换
			try {
			    //通过数据库获取工具的选择方案
				if (suffix.equals("pdf")) {
					ConvertUtil.pdf2docxConvert(filePath + oldFileName, outputFormat);
				} else {
					ConvertUtil.sofficeConvert(filePath + oldFileName, outputFormat, filePath);
				}
				// 校验是否解析成功
				File newFile = new File(filePath + newFileName);
				if (!newFile.exists()) {
					throw new ConnectException();
				}
				//解析成功
				String outputFileMD5 = MD5Util.getFileMD5String(newFile);
				redisTemplate.opsForValue().set(RedisConstant.FILE_PREFIX + outputFileMD5, newFileName);
			} finally {
				Runnable runnable = new TimingDelUtil();
				Thread thread = new Thread(runnable);
				thread.start();
				log.info("定时删除文件线程启动.........");
			}
			// 读取新文件名
			return uuid + "." + outputFormat;
			// return Result.ok();
		} catch (IOException e) {
			e.printStackTrace();
			throw new ServerErrorException("文件读取异常");
		}
	}

	@Override
	public String fastConvert(String key, String outputFormat) throws IOException {
		// 1.检验是否原文件存在
		String oldFileName = redisTemplate.opsForValue().get(key);
		if (Objects.isNull(oldFileName)) {
			throw new FastConvertFalseException();
		}
		String newFileName = "";
		if (oldFileName.lastIndexOf('.') == -1) {
			newFileName = oldFileName + '.' + outputFormat;
		} else {
			newFileName = oldFileName.substring(0, oldFileName.lastIndexOf('.') + 1) + outputFormat;
		}
		// 2. 检验转换以后文件是否存在
		String filePath = System.getProperty("user.dir") + "/demo-upload/";
		File newFile = new File(filePath + newFileName);
		// 目标文件存在，直接返回文件名称
		if (newFile.exists()) {
			return newFileName;
		}
		// 不存在需要解析并返回

		// 通过数据库获取工具的选择方案
		String suffix = oldFileName.substring(oldFileName.lastIndexOf('.') + 1);
		if (suffix.equals("pdf")) {
			ConvertUtil.pdf2docxConvert(filePath + oldFileName, outputFormat);
		} else {
			ConvertUtil.sofficeConvert(filePath + oldFileName, outputFormat, filePath);
		}
		// 校验是否解析成功
		if (!newFile.exists()) {
			throw new ConvertFalseException();
		}
		//解析成功
		String outputFileMD5 = MD5Util.getFileMD5String(newFile);
		redisTemplate.opsForValue().set(RedisConstant.FILE_PREFIX + outputFileMD5, newFileName);
		return newFileName;
	}

	/**
	 * 下载目标文件
	 */
	@Override
	public void download(HttpServletResponse response, String fileName) {
		String filePath = System.getProperty("user.dir");
		String newFilePath = filePath + "/demo-upload/";
		File file1 = new File(newFilePath);
		if (!file1.exists()) {
			file1.mkdirs();
		}
		Path path = Paths.get(newFilePath, fileName);

		if (!Files.exists(path)) {
			throw new ServerErrorException("文件不存在");
		}
		//获取文件的后缀名
		String fileSuffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		response.setContentType("application/" + fileSuffix);

		//添加http头信息
		response.addHeader("Content-Disposition", "attachment;filename="
					+ fileName);

		//使用  Path 和response输出流将文件输出到浏览器
		try {
			Files.copy(path, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


}
