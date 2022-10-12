package com.fansos.miconvert.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;

/**
 * 定时删除文件工具类
 * @author Diligence
 * @create 2022 - 10 - 12 0:38
 */
@Slf4j
public class TimingDelUtil implements Runnable {

	/**
	 * 判断指定的文件删除是否成功
	 * @param file
	 * @return
	 */
	public static boolean deleteFile(File file) {
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				log.info("文件删除成功！");
				return true;
			} else {
				log.info("文件删除失败！");
				return false;
			}
		} else {
			log.info("文件不存在，删除失败！");
			return false;
		}
	}

	@Override
	public void run() {
		try {
			Thread.sleep(1000 * 60 * 60);//每小时运行一次
			log.info("开始删除.........");
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		try {
			File dir = new File(ConstantPropertiesUtil.UPLOAD_SAVE_PATH);
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteFile(files[i]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
