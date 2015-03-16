package cn.com.king.jfinal.model.project.module;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class ModuleValidator extends Validator {

	public ModuleValidator() {
	}

	protected void validate(Controller controller) {
		validateRequiredString("module.project_id", "project_idMsg", "请选择隶属项目!");
		//validateRequiredString("module.parent_id", "parent_idMsg", "请选择上级模块!");
		validateRequiredString("module.module_name", "module_nameMsg", "请输入模块名称!");
		validateRequiredString("module.module_index", "module_indexMsg", "请输入排序!");
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
