package cn.com.king.jfinal.model.project.table.column;

import cn.com.king.jfinal.model.project.Project;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

public class ColumnInterceptor implements Interceptor {

	public ColumnInterceptor() {
	}

	public void intercept(ActionInvocation ai) {
		System.out.println("Table Befor invoking ");
		ai.invoke();
		System.out.println("Table After invoking ");
		initSel(ai.getController());
	}

	private void initSel(Controller controller) {
		controller.setAttr("projectList", Project.dao.listAll());
	}
}
