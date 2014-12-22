package cn.com.king.jfinal.util;

import org.apache.log4j.Logger;

public class BeanUtil {
	/**
	 * 获取日志记录器
	 * 
	 * @param clazz
	 * @return
	 */
	public static <T> Logger getLogger(Class<T> clazz) {
		Logger LOGGER = Logger.getLogger(BeanUtil.class);
		return LOGGER;
	}

	/**
	 * 检测是否为null或""
	 * 
	 * @param str
	 * @return
	 */
	public static boolean checkStr(Object str) {
		if (str instanceof String) {
			return str != null && !"".equals(str.toString().trim());
		} else {
			return str != null;
		}
	}
}
