package cn.com.king.jfinal.model.project.api;


import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import java.util.Date;

@Before(ApiInterceptor.class)
public class ApiController extends Controller {

	public void index() {
		render("list.html");
	}

	public void page() {
		setAttr("apiPage", Api.dao.paginate(getParaToInt(0, Integer.valueOf(1))
				.intValue(), 10));
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
		redirect("/api");
	}

	public void edit() {
		setAttr("api", Api.dao.findById(getParaToInt()));
	}

	@Before(ApiValidator.class)
	public void update() {
		getModel(Api.class).update();
		redirect("/api");
	}

	public void delete() {
		Api.dao.deleteById(getParaToInt());
		redirect("/api");
	}

	public void detail() {
		setAttr("api", Api.dao.findById(getParaToInt()));
	}
}
