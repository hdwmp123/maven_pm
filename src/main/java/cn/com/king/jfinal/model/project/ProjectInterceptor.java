package cn.com.king.jfinal.model.project;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class ProjectInterceptor implements Interceptor {

	public ProjectInterceptor() {
	}

	public void intercept(ActionInvocation ai) {
		System.out.println("Project Befor invoking ");
		ai.invoke();
		System.out.println("Project After invoking ");
	}
}
