package com.fansos.miconver.test;

/**
 * @author Diligence
 * @create 2022 - 10 - 13 0:51
 */
public class SplitTest {
	public static void main(String[] args) {
		String s = "f645447eb7d146aaa2d923425d325383_test.doc";
		String[] split = s.split("\\.");
		System.out.println(split[1]);
		for (String s1 : split) {
			System.out.println(s1);
		}
	}
}
