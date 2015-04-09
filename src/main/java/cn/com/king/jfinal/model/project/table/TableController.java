package cn.com.king.jfinal.model.project.table;

import cn.com.king.jfinal.util.Constant;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import java.util.Date;
import java.util.List;

@Before(TableInterceptor.class)
public class TableController extends Controller {

	public void index() {
		render("list.html");
	}

	public void page() {
		setAttr("tablePage", Table.dao.paginate(
				getParaToInt("page_index", -1),
				Constant.PAGE_SIZE, 
				getPara("table_name"),
				getParaToInt("project_id")
				));
		render("list-table.html");
	}

	public void add() {
		render("add.html");
	}

	@Before(TableValidator.class)
	public void save() {
		Table bean = getModel(Table.class);
		bean.set("created", new Date());
		bean.save();
		index();
	}

	public void edit() {
		setAttr("table", Table.dao.findById(getParaToInt()));
	}

	@Before(TableValidator.class)
	public void update() {
		getModel(Table.class).update();
		index();
	}
	
	@Before(TableValidator.class)
	public void delete() {
		Table.dao.deleteById(getParaToInt());
		index();
	}

	public void listByProjectId() {
		List<Table> list = Table.dao.listByProjectId(getParaToInt("project_id",
				-1));
		renderJson(list);
	}
}
