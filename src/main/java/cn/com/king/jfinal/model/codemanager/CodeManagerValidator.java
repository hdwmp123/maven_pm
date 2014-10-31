package cn.com.king.jfinal.model.codemanager;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class CodeManagerValidator extends Validator {

	public CodeManagerValidator() {
	}

	protected void validate(Controller controller) {
		validateRequiredString("codeManager.namespace",
				"codeManager_namespaceMsg", "!");
		validateRequiredString("codeManager.name", "codeManager_nameMsg", "!");
	}

	protected void handleError(Controller controller) {
		controller.keepModel(CodeManager.class);
		String actionKey = getActionKey();
		if (actionKey.equals("/code_manager/save")) {
			controller.render("add.html");
		} else if (actionKey.equals("/code_manager/update")) {
			controller.render("edit.html");
		}
	}
}
