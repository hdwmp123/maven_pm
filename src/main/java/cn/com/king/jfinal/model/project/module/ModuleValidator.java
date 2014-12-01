package cn.com.king.jfinal.model.project.module;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class ModuleValidator extends Validator {

	public ModuleValidator() {
	}

	protected void validate(Controller controller) {
		validateRequiredString("module.module_name", "module_nameMsg", "");
	}

	protected void handleError(Controller controller) {
		controller.keepModel(Module.class);
		String actionKey = getActionKey();
		if (actionKey.equals("/module/save"))
			controller.render("add.html");
		else if (actionKey.equals("/module/update"))
			controller.render("edit.html");
	}
}
