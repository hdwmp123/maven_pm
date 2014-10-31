package cn.com.king.jfinal.model.codemanager;

import java.util.Date;

import cn.com.king.jfinal.util.Constant;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

@Before(CodeManagerInterceptor.class)
public class CodeManagerController extends Controller {

	public void index() {
		render("list.html");
	}

	public void page() {
		setAttr("codeManagerPage",
				CodeManager.dao.paginate(getParaToInt("page_index", 1), Constant.PAGE_SIZE));
		render("list-table.html");
	}

	public void add() {
		render("add.html");
	}

	@Before(CodeManagerValidator.class)
	public void save() {
		CodeManager bean = getModel(CodeManager.class);
		bean.set("created", new Date());
		bean.save();
		index();
	}

	public void edit() {
		setAttr("codeManager", CodeManager.dao.findById(getParaToInt()));
	}

	@Before(CodeManagerValidator.class)
	public void update() {
		getModel(CodeManager.class).update();
		index();
	}

	public void delete() {
		CodeManager.dao.deleteById(getParaToInt());
		index();
	}
}
