package cn.com.king.jfinal.model.project.module;

import java.util.ArrayList;
import java.util.List;

import cn.com.king.jfinal.model.project.Project;
import cn.com.king.jfinal.util.BeanUtil;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class Module extends Model<Module> {
	/**
	 * 
	 */
	public static final Module dao = new Module();

	public Module() {
	}

	public Page<Module> paginate(int pageNumber, int pageSize,
			String projectName, Long projectId, Long parentId) {
		StringBuffer where = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (BeanUtil.checkStr(projectName)) {
			where.append(" and a.module_name like ? ");
			params.add("%" + projectName + "%");
		}
		if (BeanUtil.checkStr(projectId)) {
			where.append(" and a.project_id = ? ");
			params.add(projectId);
		}
		if (BeanUtil.checkStr(parentId)) {
			where.append(" and a.parent_id = ? ");
			params.add(parentId);
		}
		return paginate(pageNumber, pageSize, "select a.*",
				"from project_module a where 1=1 " + where.toString()
						+ " order by a.module_index asc", params.toArray());
	}

	public List<Module> listAll() {
		return find("select * from project_module order by module_index asc");
	}

	public Module getById(Integer id) {
		if (id == null) {
			return null;
		}
		return findById(id);
	}

	public Project getProject() {
		return Project.dao.getById(getInt("project_id"));
	}

	public Module getParent() {
		return this.getById(getInt("parent_id"));
	}

	public List<Module> listByProjectId(Integer projectId) {
		return find(
				"select a.* from project_module a where a.project_id = ? order by a.id asc",
				projectId);
	}
}
