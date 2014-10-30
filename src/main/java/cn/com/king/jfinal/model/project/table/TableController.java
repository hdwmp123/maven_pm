package cn.com.king.jfinal.model.project.table;

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
				getParaToInt(0, Integer.valueOf(1)).intValue(), 10,
				getParaToInt("project_id", Integer.valueOf(-1)).intValue()));
		render("list-table.html");
	}

	public void add() {
		render("add.html");
	}

	@Before(TableValidator.class)
	public void save() {
		Table bean = (Table) getModel(Table.class);
		bean.set("created", new Date());
		bean.save();
		redirect("/table");
	}

	public void edit() {
		setAttr("table", Table.dao.findById(getParaToInt()));
	}

	@Before(TableValidator.class)
	public void update() {
		getModel(Table.class).update();
		redirect("/table");
	}

	public void delete() {
		Table.dao.deleteById(getParaToInt());
		redirect("/table");
	}

	public void listByProjectId() {
		List<Table> list = Table.dao.listByProjectId(getParaToInt("project_id",
				Integer.valueOf(-1)));
		renderJson(list);
	}
}
