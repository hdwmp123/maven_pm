package cn.com.king.jfinal.model.project.table;

import cn.com.king.jfinal.model.project.Project;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import java.util.List;

@SuppressWarnings("serial")
public class Table extends Model<Table> {

	public static final Table dao = new Table();

	public Table() {
	}

	public Page<Table> paginate(int pageNumber, int pageSize, int projectId) {
		return paginate(
				pageNumber,
				pageSize,
				"select a.*",
				"from project_table a where a.project_id = ? order by a.id asc",
				projectId);
	}

	public List<Table> listAll() {
		return find("select * from project_table order by id asc");
	}

	public Project getProject() {
		return Project.dao.getById(getInt("project_id").intValue());
	}

	public List<Table> listByProjectId(Integer projectId) {
		return find(
				"select a.* from project_table a where a.project_id = ? order by a.id asc",
				projectId);
	}

}
