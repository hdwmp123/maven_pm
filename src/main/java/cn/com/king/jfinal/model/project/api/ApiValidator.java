package cn.com.king.jfinal.model.project.api;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class ApiValidator extends Validator {

	public ApiValidator() {
	}

	protected void validate(Controller controller) {
		validateRequiredString("api.project_id", "project_idMsg", "请选择隶属项目!");
		validateRequiredString("api.module_id", "module_idMsg", "请选择隶属模块!");
		validateRequiredString("api.api_name", "api_nameMsg", "请选输入API名称!");
		validateRequiredString("api.api_url", "api_urlMsg", "请输入URL!");
		validateRequiredString("api.api_request_type", "api_request_typeMsg", "请选择API请求方式!");
		validateRequiredString("api.api_response_type", "api_response_typeMsg", "请选择API相应方式!");
		validateRequiredString("api.api_index", "api_indexMsg", "请输入排序!");
	}

	protected void handleError(Controller controller) {
		controller.keepModel(Api.class);
		String actionKey = getActionKey();
		if (actionKey.equals("/api/save")) {
			controller.render("add.html");
		} else if (actionKey.equals("/api/update")) {
			controller.render("edit.html");
		}
	}
}
