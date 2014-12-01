package cn.com.king.jfinal.util;

public class BeanUtil {
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
