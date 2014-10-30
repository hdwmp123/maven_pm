package cn.com.king.jfinal.model.project.table.column;

import cn.com.king.jfinal.model.project.table.Table;
import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class ColumnValidator extends Validator {

	public ColumnValidator() {
	}

	protected void validate(Controller controller) {
		validateRequiredString("column.table_id", "table_idMsg", "!");
		validateRequiredString("column.column_name", "column_nameMsg", "!");
		validateRequiredString("column.column_index", "column_indexMsg", "!");
	}

	protected void handleError(Controller controller) {
		controller.keepModel(Table.class);
		String actionKey = getActionKey();
		if (actionKey.equals("/column/save"))
			controller.render("add.html");
		else if (actionKey.equals("/column/update"))
			controller.render("edit.html");
	}
}
