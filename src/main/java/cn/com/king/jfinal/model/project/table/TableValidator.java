package cn.com.king.jfinal.model.project.table;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class TableValidator extends Validator {

	public TableValidator() {
	}

	protected void validate(Controller controller) {
		validateRequiredString("table.project_id", "project_idMsg", "!");
		validateRequiredString("table.table_name", "table_nameMsg", "!");
		validateRequiredString("table.table_index", "table_indexMsg", "!");
	}

	protected void handleError(Controller controller) {
		controller.keepModel(Table.class);
		String actionKey = getActionKey();
		if (actionKey.equals("/table/save"))
			controller.render("add.html");
		else if (actionKey.equals("/table/update"))
			controller.render("edit.html");
	}
}
