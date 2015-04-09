package cn.com.king.jfinal.model.project;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.king.jfinal.model.project.api.Api;
import cn.com.king.jfinal.util.Constant;
import cn.com.king.jfinal.util.freemarker.FreeMarkerUtil;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

@Before(ProjectInterceptor.class)
public class ProjectController extends Controller {
	/**
	 * 列表主页
	 */
	public void index() {
		render("list.html");
	}
	/**
	 * 分页
	 */
	public void page() {
		setAttr("projectPage", Project.dao.paginate(
				getParaToInt("page_index", 1), Constant.PAGE_SIZE,
				getPara("project_name", "")));
		render("list-table.html");
	}
	
	/**
	 * 跳转到新增页面
	 */
	public void add() {
		render("add.html");
	}
	
	/**
	 * 新增
	 */
	@Before(ProjectValidator.class)
	public void save() {
		Project bean = getModel(Project.class);
		bean.set("created", new Date());
		bean.save();
		index();
	}
	
	/**
	 * 跳转到修改页面
	 */
	public void edit() {
		setAttr("project", Project.dao.findById(getParaToInt()));
	}
	
	/**
	 * 修改
	 */
	@Before(ProjectValidator.class)
	public void update() {
		getModel(Project.class).update();
		index();
	}
	
	/**
	 * 删除
	 */
	@Before(ProjectValidator.class)
	public void delete() {
		Project.dao.deleteById(getParaToInt());
		index();
	}
	
	/**
	 * 获取所有集合(下拉框)
	 */
	public void listAll(){
		List<Project> list = Project.dao.listAll();
		renderJson(list);
	}
	
	/**
	 * 导出API
	 */
	public void export() {
		//
		List<Api> apis = Api.dao.listByProjectId(getParaToInt("project_id", -1));
		//
		String templateName = "export_api.ftl";
		String targetHtmlPath = "D:/Project/myself_proX64/maven_pm/maven.1414661360171/trunk/src/main/resources/temp/export_api_test.doc";
		Map<String,Object> dataMap = new HashMap<String,Object>();
		dataMap.put("apisExport", apis);
		FreeMarkerUtil.crateFile(dataMap, templateName, targetHtmlPath);
		File file = new File(targetHtmlPath);
		renderFile(file);
	}
}
