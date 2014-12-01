package cn.com.king.jfinal.model.project.api;

import cn.com.king.jfinal.util.Constant;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import java.util.Date;

@Before(ApiInterceptor.class)
public class ApiController extends Controller {

	public void index() {
		render("list.html");
	}

	public void page() {
		setAttr("apiPage", Api.dao.paginate(
				getParaToInt("page_index", 1), 
				Constant.PAGE_SIZE,
				getPara("api_name", null),
				getParaToLong("project_id", null),
				getParaToLong("module_id", null)));
		render("list-table.html");
	}

	public void add() {
		render("add.html");
	}

	@Before(ApiValidator.class)
	public void save() {
		Api bean = getModel(Api.class);
		bean.set("created", new Date());
		bean.save();
		index();
	}

	public void edit() {
		setAttr("api", Api.dao.findById(getParaToInt()));
	}

	@Before(ApiValidator.class)
	public void update() {
		getModel(Api.class).update();
		index();
	}

	public void delete() {
		Api.dao.deleteById(getParaToInt());
		index();
	}

	public void detail() {
		setAttr("api", Api.dao.findById(getParaToInt()));
		render("detail.html");
	}
}
