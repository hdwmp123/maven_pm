package cn.com.king.jfinal.model.project.api;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class ApiValidator extends Validator {

	public ApiValidator() {
	}

	protected void validate(Controller controller) {
		validateRequiredString("api.project_id", "project_idMsg", "!");
		validateRequiredString("api.api_url", "api_urlMsg", "!");
		validateRequiredString("api.api_index", "api_indexMsg", "!");
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
