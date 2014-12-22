package cn.com.king.jfinal.util.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * @Title: FreeMarkerUtil.java
 * @Package cn.com.king.jfinal.util.freemarker
 * @Description: 使用FreeMarker模板生成文件
 * @author hdwmp123@163.com
 * @date 2014-12-13 下午6:46:38
 * @version V1.0
 */
public class FreeMarkerUtil {

	private static Configuration tempConfiguration = new Configuration();;

	public FreeMarkerUtil() {

	}

	/**
	 * 
	 * @param data
	 *            数据库
	 * @param templateDir
	 *            模板存放路径
	 * @param templateName
	 *            模板文件名
	 * @param targetPath
	 *            生成文件路径
	 */
	public static void crateFile(Map<String,Object> data, String templateName, String targetPath) {
		try {
			// 模板 存放路径（/template/file/static）
			//tempConfiguration.setDirectoryForTemplateLoading(new File(templateDir));
			tempConfiguration.setClassForTemplateLoading(FreeMarkerUtil.class,"/freemarker/template");
			// 模板名称（templateName.ftl）
			Template template = tempConfiguration.getTemplate(templateName);
			template.setEncoding("UTF-8");
			// 生成文件存放的路径
			File file = new File(targetPath);
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
			// 处理模版 map数据 ,输出流
			template.process(data, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		List<Map<String, Object>> apis = new ArrayList<Map<String, Object>>();
		Map<String, Object> api = new HashMap<String, Object>();
		api.put("api_name", "King");
		apis.add(api);
		String templateName = "export_api.ftl";
		String targetHtmlPath = "D:/Project/myself_proX64/maven_pm/maven.1414661360171/trunk/src/main/resources/temp/export_api_test.doc";
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("apisExport", apis);
		FreeMarkerUtil.crateFile(dataMap, templateName, targetHtmlPath);
		System.out.println("OK");
	}
}
