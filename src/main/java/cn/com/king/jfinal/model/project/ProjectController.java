package cn.com.king.jfinal.model.project;

import cn.com.king.jfinal.model.project.api.Api;
import cn.com.king.jfinal.util.Constant;
import cn.com.king.jfinal.util.freemarker.FreeMarkerUtil;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Before(ProjectInterceptor.class)
public class ProjectController extends Controller {

	public void index() {
		render("list.html");
	}

	public void page() {
		setAttr("projectPage", Project.dao.paginate(
				getParaToInt("page_index", 1), Constant.PAGE_SIZE,
				getPara("project_name", "")));
		render("list-table.html");
	}

	public void add() {
		render("add.html");
	}

	@Before(ProjectValidator.class)
	public void save() {
		Project bean = getModel(Project.class);
		bean.set("created", new Date());
		bean.save();
		index();
	}

	public void edit() {
		setAttr("project", Project.dao.findById(getParaToInt()));
	}

	@Before(ProjectValidator.class)
	public void update() {
		getModel(Project.class).update();
		index();
	}

	public void delete() {
		Project.dao.deleteById(getParaToInt());
		index();
	}

	public void export() {
		//
		List<Api> apis = Api.dao.listByProjectId(getParaToLong("project_id", -1L));
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
