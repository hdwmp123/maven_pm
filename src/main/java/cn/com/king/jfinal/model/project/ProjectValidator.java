package cn.com.king.jfinal.model.project;

import cn.com.king.jfinal.model.bean.CheckDelMsg;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class ProjectValidator extends Validator {
	String actionKey = null ;
	
	public ProjectValidator() {
	}

	protected void validate(Controller controller) {
		actionKey = getActionKey();
		if(actionKey.equals("/project/save") || actionKey.equals("/project/update")){
			validateRequiredString("project.project_name", "project_nameMsg", "请输入项目名称!");
		}
		if(actionKey.equals("/project/delete")){
			Integer projectId = controller.getParaToInt();
			CheckDelMsg msg = Project.dao.checkDelete(projectId);
			if(!msg.isCanDel()){
				addError("deleteMsg", msg.getMsg());
				controller.render("list.html");
			}
		}
	}

	protected void handleError(Controller controller) {
		actionKey = getActionKey();
		if (actionKey.equals("/project/save") || actionKey.equals("/project/update")){
			controller.keepModel(Project.class);
		}
		if (actionKey.equals("/project/save")){
			controller.render("add.html");
		} else if (actionKey.equals("/project/update")){
			controller.render("edit.html");
		}
	}
}
