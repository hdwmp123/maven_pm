package cn.com.king.jfinal.model.codemanager;

import java.util.Date;
import java.util.List;

import cn.com.king.jfinal.util.Constant;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

@Before(CodeManagerInterceptor.class)
public class CodeManagerController extends Controller {
	/**
	 * 列表首页
	 */
	public void index() {
		render("list.html");
	}
	
	/**
	 * 分页
	 */
	public void page() {
		setAttr("codeManagerPage",CodeManager.dao.paginate(
				getParaToInt("page_index", 1), 
				Constant.PAGE_SIZE,
				getPara("namespace", null),
				getParaToLong("father_id", null)));
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
	@Before(CodeManagerValidator.class)
	public void save() {
		CodeManager bean = getModel(CodeManager.class);
		bean.set("created", new Date());
		bean.save();
		index();
	}
	
	/**
	 * 跳转到更新页面
	 */
	public void edit() {
		setAttr("codeManager", CodeManager.dao.findById(getParaToInt()));
	}

	/**
	 * 更新
	 */
	@Before(CodeManagerValidator.class)
	public void update() {
		getModel(CodeManager.class).update();
		index();
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		CodeManager.dao.deleteById(getParaToInt());
		index();
	}
	
	/**
	 * 根据命名空间获取集合(下拉框)
	 */
	public void listByNamespace(){
		List<CodeManager> list = CodeManager.dao.listByNamespace(getPara("namespace"));
		renderJson(list);
	}
}
