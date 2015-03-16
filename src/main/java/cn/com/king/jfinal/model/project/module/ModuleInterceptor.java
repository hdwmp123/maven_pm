package cn.com.king.jfinal.model.project.module;

import org.apache.log4j.Logger;

import cn.com.king.jfinal.util.BeanUtil;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

public class ModuleInterceptor implements Interceptor {
	private Logger LOGGER = BeanUtil.getLogger(ModuleInterceptor.class);

	public ModuleInterceptor() {
	}

	public void intercept(ActionInvocation ai) {
		LOGGER.info("Module Befor invoking ");
		ai.invoke();
		initParam(ai.getController());
		LOGGER.info("Module After invoking ");
	}

	private void initParam(Controller controller) {
		int project_id = controller.getParaToInt("project_id", -1);
		if (project_id > 0) {
			controller.setAttr("project_id", project_id);
		}
	}
}
