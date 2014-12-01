package cn.com.king.jfinal.model.project.api;

import cn.com.king.jfinal.model.project.Project;
import cn.com.king.jfinal.model.project.module.Module;
import cn.com.king.jfinal.util.BeanUtil;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Api extends Model<Api> {

	public static final Api dao = new Api();

	public Api() {
	}

	public Page<Api> paginate(int pageNumber, int pageSize, String apiName,
			Long projectId, Long moduleId) {
		StringBuffer where = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if (BeanUtil.checkStr(apiName)) {
			where.append(" and a.api_name like ? ");
			params.add("%" + apiName + "%");
		}
		if (BeanUtil.checkStr(projectId)) {
			where.append(" and a.project_id = ? ");
			params.add(projectId);
		}
		if (BeanUtil.checkStr(moduleId)) {
			where.append(" and a.module_id = ? ");
			params.add(moduleId);
		}

		return paginate(pageNumber, pageSize, "select a.*",
				"from project_api a where 1=1 " + where.toString()
						+ "order by a.api_index asc", params.toArray());
	}

	public List<Api> listAll() {
		return find("select * from project_api order by id asc");
	}

	public Api getById(Integer id) {
		if (id == null) {
			return null;
		}
		return findById(id);
	}

	public Project getProject() {
		return Project.dao.getById(getInt("project_id"));
	}

	public Module getParent() {
		return Module.dao.getById(getInt("module_id"));
	}

}
