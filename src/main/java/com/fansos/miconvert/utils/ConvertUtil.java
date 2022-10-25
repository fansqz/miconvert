package com.fansos.miconvert.utils;


import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

/**
 * @author Diligence
 * @create 2022 - 10 - 10 12:07
 */
@Slf4j
public class ConvertUtil {

	/**
	 * 获取 LibreOffice开头命令
	 */
	private static String getTitleCommand(String type) {
		String osName = System.getProperty("os.name");
		String titleCommand = "";
		if (Pattern.matches("Linux.*", osName)) {
			//Todo
			titleCommand = "";

		} else if (Pattern.matches("Windows.*", osName)) {
			if (type == "soffice") {
				titleCommand = "soffice";
			} else if (type == "pdf2docx") {
				titleCommand = "pdf2docx";
			}
		}
		return titleCommand;
	}


	public static void pdf2docxConvert(String inputFilePath, String toFormat) {
		String command = getTitleCommand("pdf2docx")
				+ "convert"
				+ inputFilePath + inputFilePath.split(".")[0] + "." + toFormat;
		executeCommand(command);
	}

	/**
	 * libreoffice转换
	 * @param inputFilePath
	 * @param toFormat
	 * @param outputPath
	 */
	public static void sofficeConvert(String inputFilePath, String toFormat, String outputPath) {
		/*
		 * 构造 libreoffice 的执行命令
		 */
		String command = getTitleCommand("soffice")
				+ " --headless --invisible --convert-to "
				+ toFormat + " "
				+ inputFilePath
				+ " --outdir " + outputPath;

		executeCommand(command);
	}

	/**
	 * 运行系统命令
	 * @param command
	 */
	public static void executeCommand(String command) {
		try {
			long start = System.currentTimeMillis();
			Process process = Runtime.getRuntime().exec(command);
			// 返回值是子线程执行完毕的返回值,返回0表示正常结束
			int exitStatus = process.waitFor();
			log.info("命令执行状态：" + exitStatus);
			// 销毁子进程
			process.destroy();
			long end = System.currentTimeMillis();
			log.info("转换耗时： " + (end - start) + "ms");
		} catch (Exception e) {
			log.error("error", e);
		}
	}


}
