package cn.com.king.jfinal.model.codemanager;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;

public class CodeManagerInterceptor implements Interceptor {

	public CodeManagerInterceptor() {
	}

	public void intercept(ActionInvocation ai) {
		System.out.println("CodeManager Befor invoking");
		ai.invoke();
		System.out.println("CodeManager After invoking ");
		initSel(ai.getController());
	}

	private void initSel(Controller controller) {
		controller.setAttr("namespaceList",
				CodeManager.dao.listByNamespace("Namespace"));
		controller.setAttr("fatherIdList",
				CodeManager.dao.listByNamespace("None"));
	}
}
