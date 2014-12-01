package cn.com.king.jfinal.model.project.module;

import cn.com.king.jfinal.model.project.Project;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

public class ModuleInterceptor implements Interceptor {

	public ModuleInterceptor() {
	}

	public void intercept(ActionInvocation ai) {
		System.out.println("Module Befor invoking ");
		ai.invoke();
		System.out.println("Module After invoking ");
		initSel(ai.getController());
	}
	
	private void initSel(Controller controller) {
		controller.setAttr("projectList", Project.dao.listAll());
		controller.setAttr("parentModuleList", Module.dao.listAll());
	}
}
