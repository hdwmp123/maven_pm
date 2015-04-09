package cn.com.king.jfinal.model.project.table;

import cn.com.king.jfinal.model.bean.CheckDelMsg;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class TableValidator extends Validator {
	String actionKey = null ;

	public TableValidator() {
	}

	protected void validate(Controller controller) {
		actionKey = getActionKey();
		if (actionKey.equals("/table/save") || actionKey.equals("/table/update")){
			validateRequiredString("table.project_id", "project_idMsg", "请选择隶属项目!");
			validateRequiredString("table.table_name", "table_nameMsg", "请输入表名!");
			validateRequiredString("table.table_index", "table_indexMsg", "请输入排序!");
		}
		if(actionKey.equals("/table/delete")){
			Integer tableId = controller.getParaToInt();
			CheckDelMsg msg = Table.dao.checkDelete(tableId);
			if(!msg.isCanDel()){
				addError("deleteMsg", msg.getMsg());
				controller.render("list.html");
			}
		}
	}

	protected void handleError(Controller controller) {
		actionKey = getActionKey();
		if (actionKey.equals("/table/save") || actionKey.equals("/table/update")){
			controller.keepModel(Table.class);
		}
		if (actionKey.equals("/table/save")) {
			controller.render("add.html");
		} else if (actionKey.equals("/table/update")) {
			controller.render("edit.html");
		}
	}
}
