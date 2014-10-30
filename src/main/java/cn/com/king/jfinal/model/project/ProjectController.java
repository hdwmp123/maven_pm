package cn.com.king.jfinal.model.project;


import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import java.util.Date;

@Before(ProjectInterceptor.class)
public class ProjectController extends Controller {

	public void index() {
		setAttr("projectPage", Project.dao.paginate(
				getParaToInt(0, Integer.valueOf(1)).intValue(), 10));
		render("list.html");
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
}
