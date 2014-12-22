package cn.com.king.jfinal.model.project.table;

import org.apache.log4j.Logger;

import cn.com.king.jfinal.model.project.Project;
import cn.com.king.jfinal.util.BeanUtil;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

public class TableInterceptor implements Interceptor {
	private Logger LOGGER = BeanUtil.getLogger(TableInterceptor.class);

	public TableInterceptor() {
	}

	public void intercept(ActionInvocation ai) {
		LOGGER.info("Table Befor invoking ");
		ai.invoke();
		initSel(ai.getController());
		initParam(ai.getController());
		LOGGER.info("Table After invoking ");
	}

	private void initSel(Controller controller) {
		controller.setAttr("projectList", Project.dao.listAll());

	}

	private void initParam(Controller controller) {
		int project_id = controller.getParaToInt("project_id", -1);
		if (project_id > 0) {
			controller.setAttr("project_id", project_id);
		}
	}
}
