package cn.com.king.jfinal.model.project.table.column;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class Column extends Model<Column> {

	public static final Column dao = new Column();

	public Column() {
	}

	public Page<Column> paginate(int pageNumber, int pageSize, int tableId) {
		return paginate(pageNumber, pageSize, "select a.*",
				"from table_column a where a.table_id = ? order by a.id asc",
				tableId);
	}

}
