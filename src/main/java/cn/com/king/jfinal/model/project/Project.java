package cn.com.king.jfinal.model.project;

import cn.com.king.jfinal.util.BeanUtil;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Project extends Model<Project> {

	/**
	 * 
	 */
	public static final Project dao = new Project();

	public Project() {
	}

	public Page<Project> paginate(int pageNumber, int pageSize,
			String projectName) {
		StringBuffer where = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (BeanUtil.checkStr(projectName)) {
			where.append(" and a.project_name like ? ");
			params.add("%" + projectName + "%");
		}
		return paginate(pageNumber, pageSize, "select a.*",
				"from project a where 1=1 " + where.toString()
						+ " order by a.project_index asc", params.toArray());
	}

	public List<Project> listAll() {
		return find("select * from project order by project_index asc");
	}

	public Project getById(Integer id) {
		if (id == null) {
			return null;
		}
		return findById(id);
	}

}
