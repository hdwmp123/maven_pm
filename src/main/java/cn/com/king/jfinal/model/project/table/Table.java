package cn.com.king.jfinal.model.project.table;

import cn.com.king.jfinal.model.project.Project;
import cn.com.king.jfinal.util.BeanUtil;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Table extends Model<Table> {

	public static final Table dao = new Table();

	public Table() {
	}

	public Page<Table> paginate(int pageNumber, int pageSize,String tableName, Integer projectId) {
		StringBuffer where = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (BeanUtil.checkStr(tableName)) {
			where.append(" and a.table_name like ? ");
			params.add("%" + tableName + "%");
		}
		if (BeanUtil.checkStr(projectId)) {
			where.append(" and a.project_id = ? ");
			params.add(projectId);
		}
		return paginate(pageNumber, pageSize, "select a.*",
				"from project_table a where 1=1 " + where.toString() +" order by a.id asc",
				params.toArray());
	}

	public List<Table> listAll() {
		return find("select * from project_table order by id asc");
	}

	public Project getProject() {
		return Project.dao.getById(getInt("project_id"));
	}

	public Table getById(Integer id) {
		if (id == null) {
			return null;
		}
		return findById(id);
	}
	
	public List<Table> listByProjectId(Integer projectId) {
		return find(
				"select a.* from project_table a where a.project_id = ? order by a.id asc",
				projectId);
	}

}
