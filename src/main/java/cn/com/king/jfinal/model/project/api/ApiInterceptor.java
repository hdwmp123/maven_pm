
package cn.com.king.jfinal.model.project.api;

import cn.com.king.jfinal.model.codemanager.CodeManager;
import cn.com.king.jfinal.model.project.Project;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

public class ApiInterceptor implements Interceptor {

	public ApiInterceptor() {
	}

	public void intercept(ActionInvocation ai) {
		System.out.println("API Before invoking ");
		ai.invoke();
		System.out.println("API After invoking ");
		initSel(ai.getController());
	}

	private void initSel(Controller controller) {
		controller.setAttr("projectList", Project.dao.listAll());
		controller.setAttr("apiTypeList",
				CodeManager.dao.listByNamespace("HTTP_Method"));
	}
}
