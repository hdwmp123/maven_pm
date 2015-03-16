package cn.com.king.jfinal.model.project.table.column;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class ColumnValidator extends Validator {

	public ColumnValidator() {
	}

	protected void validate(Controller controller) {
		validateRequiredString("column.project_id", "project_idMsg", "请选择隶属项目!");
		validateRequiredString("column.table_id", "table_idMsg", "请选择隶属表!");
		validateRequiredString("column.column_name", "column_nameMsg", "请输入列名!");
		validateRequiredString("column.column_type", "column_typeMsg", "请输入列数据类型!");
		validateRequiredString("column.column_index", "column_indexMsg", "请输入列排序!");
	}

	protected void handleError(Controller controller) {
		controller.keepModel(Column.class);
		String actionKey = getActionKey();
		if (actionKey.equals("/column/save"))
			controller.render("add.html");
		else if (actionKey.equals("/column/update"))
			controller.render("edit.html");
	}
}
