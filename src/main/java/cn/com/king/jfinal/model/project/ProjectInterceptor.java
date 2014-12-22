package cn.com.king.jfinal.model.project;

import org.apache.log4j.Logger;

import cn.com.king.jfinal.util.BeanUtil;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class ProjectInterceptor implements Interceptor {
	private Logger LOGGER = BeanUtil.getLogger(ProjectInterceptor.class);
	public ProjectInterceptor() {
	}

	public void intercept(ActionInvocation ai) {
		LOGGER.info("Project Befor invoking ");
		ai.invoke();
		LOGGER.info("Project After invoking ");
	}
}
