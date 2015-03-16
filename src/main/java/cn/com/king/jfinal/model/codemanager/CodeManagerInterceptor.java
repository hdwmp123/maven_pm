package cn.com.king.jfinal.model.codemanager;

import org.apache.log4j.Logger;

import cn.com.king.jfinal.util.BeanUtil;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class CodeManagerInterceptor implements Interceptor {
	private Logger LOGGER = BeanUtil.getLogger(CodeManagerInterceptor.class);

	public CodeManagerInterceptor() {
	}

	public void intercept(ActionInvocation ai) {
		LOGGER.info("CodeManager Befor invoking");
		ai.invoke();
		LOGGER.info("CodeManager After invoking ");
	}

}