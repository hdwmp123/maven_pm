package cn.com.king.jfinal.model.project.table.column;

import java.util.ArrayList;
import java.util.List;

import cn.com.king.jfinal.model.project.Project;
import cn.com.king.jfinal.model.project.table.Table;
import cn.com.king.jfinal.util.BeanUtil;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class Column extends Model<Column> {

	public static final Column dao = new Column();

	public Column() {
	}
	
	/**
	 * 分页
	 * @param pageNumber
	 * @param pageSize
	 * @param columnName
	 * @param projectId
	 * @param tableId
	 * @return
	 */
	public Page<Column> paginate(int pageNumber, int pageSize, String columnName, 
			Integer projectId,Integer tableId) {
		StringBuffer where = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (BeanUtil.checkStr(columnName)) {
			where.append(" and a.column_name like ? ");
			params.add("%" + columnName + "%");
		}
		if (BeanUtil.checkStr(projectId)) {
			where.append(" and a.project_id = ? ");
			params.add(projectId);
		}
		if (BeanUtil.checkStr(tableId)) {
			where.append(" and a.table_id = ? ");
			params.add(tableId);
		}
		return paginate(pageNumber, pageSize, "select a.*",
				"from table_column a where 1=1 " + where.toString() +" order by a.id asc",
				params.toArray());
	}
	/**
	 * 属性
	 * @return
	 */
	public Project getProject() {
		return Project.dao.getById(getInt("project_id"));
	}
	
	/**
	 * 属性
	 * @return
	 */
	public Table getTable() {
		return Table.dao.getById(getInt("table_id"));
	}
	
	/**
	 * 根据tableId获取数量
	 * @param tableId
	 * @return
	 */
	public int getCountByTableId(Integer tableId) {
		List<Column> list = this.listByTableId(tableId);
		return list == null ? 0 : list.size();
	}
	
	/**
	 * 根据tableId获取集合
	 * @param tableId
	 * @return
	 */
	private List<Column> listByTableId(Integer tableId) {
		return find(
				"select a.* from table_column a where a.table_id = ? order by a.id asc",
				tableId);
	}

}
