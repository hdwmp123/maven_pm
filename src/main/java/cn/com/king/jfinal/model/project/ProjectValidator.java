package cn.com.king.jfinal.model.project;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class ProjectValidator extends Validator {
	public ProjectValidator() {
	}

	protected void validate(Controller controller) {
		validateRequiredString("project.project_name", "project_nameMsg", "");
	}

	protected void handleError(Controller controller) {
		controller.keepModel(Project.class);
		String actionKey = getActionKey();
		if (actionKey.equals("/project/save"))
			controller.render("add.html");
		else if (actionKey.equals("/project/update"))
			controller.render("edit.html");
	}
}
