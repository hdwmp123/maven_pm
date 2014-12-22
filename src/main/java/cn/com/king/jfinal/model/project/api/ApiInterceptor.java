
package cn.com.king.jfinal.model.project.api;

import org.apache.log4j.Logger;

import cn.com.king.jfinal.model.codemanager.CodeManager;
import cn.com.king.jfinal.model.project.Project;
import cn.com.king.jfinal.util.BeanUtil;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

public class ApiInterceptor implements Interceptor {
	private Logger LOGGER = BeanUtil.getLogger(ApiInterceptor.class);
	public ApiInterceptor() {
	}

	public void intercept(ActionInvocation ai) {
		LOGGER.info("API Before invoking ");
		ai.invoke();
		initSel(ai.getController());
		initParam(ai.getController());
		LOGGER.info("API After invoking ");
	}

	private void initSel(Controller controller) {
		controller.setAttr("projectList", Project.dao.listAll());
		controller.setAttr("apiRequestTypeList", CodeManager.dao.listByNamespace("RequestType"));
		controller.setAttr("apiResponseTypeList", CodeManager.dao.listByNamespace("ResponseType"));
	}

	private void initParam(Controller controller) {
		int project_id = controller.getParaToInt("project_id", -1);
		if (project_id > 0) {
			controller.setAttr("project_id", project_id);
		}
	}

}
