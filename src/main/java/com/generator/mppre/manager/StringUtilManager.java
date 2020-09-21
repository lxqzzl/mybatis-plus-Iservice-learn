package com.generator.mppre.manager;

/**
 * @Description 字符串工具类            
 * @author lxq
 * @since Sun Sep 20 18:19:07 CST 2020
 */

public class StringUtilManager {
	/**
	 * 首字母大写
	 */
	public static String captureUp(String string) {
		//将字符串转化为字符数组
        char[] stringCharArray = string.toCharArray();
        //将第一个字符的ACSII码前移32位，获取到大写
        stringCharArray[0]-=32;
		return String.valueOf(stringCharArray);		
	}		
}
