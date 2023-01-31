package com.fansos.miconvert.utils;

import com.fansos.miconvert.model.pojo.UserInfo;

import java.util.Objects;

/**
 * 基于当前线程的UserInfo的获取
 * @author fzw
 * @date 2023/1/31 23:01
 */
public class CurrentUserInfo {
	/**
	 * 当前线程的UserInfo
	 */
	public static final ThreadLocal<UserInfo> CURRENT_THREAD_USER_INFO = new ThreadLocal<>();

	/**
	 * 设置当前线程的UserInfo对象
	 * @param userInfo UserInfo
	 */
	public static void set(UserInfo userInfo) {
		CURRENT_THREAD_USER_INFO.set(Objects.requireNonNull(userInfo));
	}

	/**
	 * 从当前线程获取UserInfo对象
	 * @return 当前线程下的UserInfo对象
	 */
	public static UserInfo get() {
		return Objects.requireNonNull(CURRENT_THREAD_USER_INFO.get());
	}

	/**
	 * 移除当前线程的UserInfo对象
	 */
	public static void remove() {
		CURRENT_THREAD_USER_INFO.remove();
	}
}
