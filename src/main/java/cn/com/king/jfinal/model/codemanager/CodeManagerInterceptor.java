package cn.com.king.jfinal.model.codemanager;

import org.apache.log4j.Logger;

import cn.com.king.jfinal.util.BeanUtil;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

public class CodeManagerInterceptor implements Interceptor {
	private Logger LOGGER = BeanUtil.getLogger(CodeManagerInterceptor.class);
	public CodeManagerInterceptor() {
	}

	public void intercept(ActionInvocation ai) {
		LOGGER.info("CodeManager Befor invoking");
		ai.invoke();
		initSel(ai.getController());
		LOGGER.info("CodeManager After invoking ");
	}

	private void initSel(Controller controller) {
		controller.setAttr("namespaceList",CodeManager.dao.listByNamespace("Namespace"));
		controller.setAttr("fatherIdList",CodeManager.dao.listByNamespace("None"));
	}
}
