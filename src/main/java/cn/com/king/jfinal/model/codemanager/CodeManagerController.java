package cn.com.king.jfinal.model.codemanager;

import java.util.Date;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

@Before(CodeManagerInterceptor.class)
public class CodeManagerController extends Controller {

	public void index() {
		setAttr("codeManagerPage", CodeManager.dao.paginate(
				getParaToInt(0, Integer.valueOf(1)).intValue(), 10));
		render("list.html");
	}

	public void add() {
		render("add.html");
	}

	@Before(CodeManagerValidator.class)
	public void save() {
		CodeManager bean = getModel(CodeManager.class);
		bean.set("created", new Date());
		bean.save();
		redirect("/code_manager");
	}

	public void edit() {
		setAttr("codeManager", CodeManager.dao.findById(getParaToInt()));
	}

	@Before(CodeManagerValidator.class)
	public void update() {
		getModel(CodeManager.class).update();
		redirect("/code_manager");
	}

	public void delete() {
		CodeManager.dao.deleteById(getParaToInt());
		redirect("/code_manager");
	}
}
