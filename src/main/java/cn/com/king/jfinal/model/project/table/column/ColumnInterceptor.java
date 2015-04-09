package cn.com.king.jfinal.model.project.table.column;

import org.apache.log4j.Logger;

import cn.com.king.jfinal.util.BeanUtil;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

public class ColumnInterceptor implements Interceptor {
	private Logger LOGGER = BeanUtil.getLogger(ColumnInterceptor.class);

	public ColumnInterceptor() {
	}

	public void intercept(ActionInvocation ai) {
		LOGGER.info("Column Befor invoking ");
		ai.invoke();
		initParam(ai.getController());
		LOGGER.info("Column After invoking ");
	}

	private void initParam(Controller controller) {
		int project_id = controller.getParaToInt("project_id", -1);
		int table_id = controller.getParaToInt("table_id", -1);
		if (project_id > 0) {
			controller.setAttr("project_id", project_id);
		}
		if (table_id > 0) {
			controller.setAttr("table_id", table_id);
		}
	}
}
