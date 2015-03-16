
package cn.com.king.jfinal.model.project.api;

import org.apache.log4j.Logger;

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
		initParam(ai.getController());
		LOGGER.info("API After invoking ");
	}

	private void initParam(Controller controller) {
		int project_id = controller.getParaToInt("project_id", -1);
		if (project_id > 0) {
			controller.setAttr("project_id", project_id);
		}
	}

}
