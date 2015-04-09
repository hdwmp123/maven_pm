package cn.com.king.jfinal.model.project.module;

import java.util.Date;
import java.util.List;

import cn.com.king.jfinal.util.Constant;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

@Before(ModuleInterceptor.class)
public class ModuleController extends Controller {

	public void index() {
		render("list.html");
	}

	public void page() {
		setAttr("modulePage", Module.dao.paginate(
				getParaToInt("page_index", 1), Constant.PAGE_SIZE,
				getPara("module_name", null),
				getParaToLong("project_id", null),
				getParaToLong("parent_id", null)));
		render("list-table.html");
	}

	public void add() {
		render("add.html");
	}

	@Before(ModuleValidator.class)
	public void save() {
		Module bean = getModel(Module.class);
		bean.set("created", new Date());
		bean.save();
		index();
	}

	public void edit() {
		setAttr("module", Module.dao.findById(getParaToInt()));
	}

	@Before(ModuleValidator.class)
	public void update() {
		getModel(Module.class).update();
		index();
	}
	
	@Before(ModuleValidator.class)
	public void delete() {
		Module.dao.deleteById(getParaToInt());
		index();
	}

	public void listByProjectId() {
		List<Module> list = Module.dao.listByProjectId(getParaToInt("project_id", -1));
		renderJson(list);
	}
	
}
