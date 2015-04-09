package cn.com.king.jfinal.model.project.module;

import cn.com.king.jfinal.model.bean.CheckDelMsg;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class ModuleValidator extends Validator {
	String actionKey = null ;
	
	public ModuleValidator() {
	}

	protected void validate(Controller controller) {
		actionKey = getActionKey();
		if(actionKey.equals("/module/save") || actionKey.equals("/module/update")){
			validateRequiredString("module.project_id", "project_idMsg", "请选择隶属项目!");
			validateRequiredString("module.module_name", "module_nameMsg", "请输入模块名称!");
			validateRequiredString("module.module_index", "module_indexMsg", "请输入排序!");
		}
		if(actionKey.equals("/module/delete")){
			Integer moduleId = controller.getParaToInt();
			CheckDelMsg msg = Module.dao.checkDelete(moduleId);
			if(!msg.isCanDel()){
				addError("deleteMsg", msg.getMsg());
				controller.render("list.html");
			}
		}
	}

	protected void handleError(Controller controller) {
		actionKey = getActionKey();
		if(actionKey.equals("/module/save") || actionKey.equals("/module/update")){
			controller.keepModel(Module.class);
		}
		if (actionKey.equals("/module/save")){
			controller.render("add.html");
		} else if (actionKey.equals("/module/update")){
			controller.render("edit.html");
		}
	}
}
