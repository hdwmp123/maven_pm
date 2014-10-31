package cn.com.king.jfinal.model.project.table.column;

import cn.com.king.jfinal.util.Constant;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

@Before(ColumnInterceptor.class)
public class ColumnController extends Controller {

	public ColumnController() {
	}

	public void index() {
		render("list.html");
	}

	public void page() {
		setAttr("columnPage",
				Column.dao.paginate(getParaToInt("page_index", 1),  Constant.PAGE_SIZE,
						getParaToInt("table_id", -1)));
		render("list-table.html");
	}

	@Before(ColumnValidator.class)
	public void add() {
		render("add.html");
	}

	public void save() {
		getModel(Column.class).save();
		index();
	}

	public void edit() {
		setAttr("column", Column.dao.findById(getParaToInt()));
	}

	@Before(ColumnValidator.class)
	public void update() {
		getModel(Column.class).update();
		index();
	}

	public void delete() {
		Column.dao.deleteById(getParaToInt());
		index();
	}
}
