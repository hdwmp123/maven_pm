package cn.com.king.jfinal.model.project.table;

import cn.com.king.jfinal.model.project.Project;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

public class TableInterceptor implements Interceptor {

	public TableInterceptor() {
	}

	public void intercept(ActionInvocation ai) {
		System.out.println("Table Befor invoking ");
		ai.invoke();
		System.out.println("Table After invoking ");
		initSel(ai.getController());
	}

	private void initSel(Controller controller) {
		controller.setAttr("projectList", Project.dao.listAll());
		controller.setAttr("urlParas","?project_id="+ controller.getParaToInt("project_id",-1));
	}
}
