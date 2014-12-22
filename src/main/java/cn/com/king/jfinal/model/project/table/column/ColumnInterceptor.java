package cn.com.king.jfinal.model.project.table.column;

import org.apache.log4j.Logger;

import cn.com.king.jfinal.model.project.Project;
import cn.com.king.jfinal.util.BeanUtil;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

public class ColumnInterceptor implements Interceptor {
	private Logger LOGGER = BeanUtil.getLogger(ColumnInterceptor.class);

	public ColumnInterceptor() {
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
		int table_id = controller.getParaToInt("table_id", -1);
		if (table_id > 0) {
			controller.setAttr("table_id", table_id);
		}
	}
}
