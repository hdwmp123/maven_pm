package cn.com.king.jfinal.model.codemanager;

import cn.com.king.jfinal.model.bean.CheckDelMsg;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class CodeManagerValidator extends Validator {
	
	String actionKey = null;
	
	public CodeManagerValidator() {
	}

	protected void validate(Controller controller) {
		actionKey = getActionKey();
		if (actionKey.equals("/code_manager/save") || actionKey.equals("/code_manager/update")) {
			validateRequiredString("codeManager.namespace","codeManager_namespaceMsg", "请选择命名空间!");
			validateRequiredString("codeManager.name", "codeManager_nameMsg", "请输入名称!");
			validateRequiredString("codeManager.father_id", "codeManager_father_idMsg", "请选择上级!");
		}
		if(actionKey.equals("/code_manager/delete")){
			Integer codeManagerId = controller.getParaToInt();
			CheckDelMsg msg = CodeManager.dao.checkDelete(codeManagerId);
			if(!msg.isCanDel()){
				addError("deleteMsg", msg.getMsg());
				controller.render("list.html");
			}
		}
	}

	protected void handleError(Controller controller) {
		actionKey = getActionKey();
		if (actionKey.equals("/code_manager/save") || actionKey.equals("/code_manager/update")) {
			controller.keepModel(CodeManager.class);
		}
		if (actionKey.equals("/code_manager/save")) {
			controller.render("add.html");
		} else if (actionKey.equals("/code_manager/update")) {
			controller.render("edit.html");
		}
	}
}
