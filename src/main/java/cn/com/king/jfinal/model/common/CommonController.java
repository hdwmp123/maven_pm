package cn.com.king.jfinal.model.common;

import com.jfinal.core.Controller;

public class CommonController extends Controller {

	public CommonController() {
	}

	public void index() {
		render("/common/index.html");
	}
}
