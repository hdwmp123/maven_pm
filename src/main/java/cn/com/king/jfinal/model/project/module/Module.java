package cn.com.king.jfinal.model.project.module;

import java.util.ArrayList;
import java.util.List;

import cn.com.king.jfinal.model.bean.CheckDelMsg;
import cn.com.king.jfinal.model.project.Project;
import cn.com.king.jfinal.model.project.api.Api;
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
	
	/**
	 * 分页
	 * @param pageNumber
	 * @param pageSize
	 * @param projectName
	 * @param projectId
	 * @param parentId
	 * @return
	 */
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
	
	/**
	 * 获取所有
	 * @return
	 */
	public List<Module> listAll() {
		return find("select * from project_module order by module_index asc");
	}
	
	/**
	 * 根据ID获取
	 * @param id
	 * @return
	 */
	public Module getById(Integer id) {
		if (id == null) {
			return null;
		}
		return findById(id);
	}
	
	/**
	 * 获取关联的Project
	 * @return
	 */
	public Project getProject() {
		return Project.dao.getById(getInt("project_id"));
	}
	
	/**
	 * 获取上级
	 * @return
	 */
	public Module getParent() {
		return this.getById(getInt("parent_id"));
	}
	
	/**
	 * 根据projectId获取集合
	 * @param projectId
	 * @return
	 */
	public List<Module> listByProjectId(int projectId) {
		return find(
				"select a.* from project_module a where a.project_id = ? order by a.id asc",
				projectId);
	}
	
	/**
	 * 根据上级ID获取集合
	 * @param moduleId
	 * @return
	 */
	public List<Module> listByParentId(int moduleId) {
		return find(
				"select a.* from project_module a where a.parent_id = ? order by a.id asc",
				moduleId);
	}
	
	/**
	 * 根据上级ID获取数量
	 * @param moduleId
	 * @return
	 */
	public int getCountByParentId(int moduleId){
		List<Module> list = this.listByParentId(moduleId);
		return list == null ? 0 : list.size();
	}
	
	/**
	 * 校验删除
	 * @param moduleId
	 * @return
	 */
	public CheckDelMsg checkDelete(int moduleId) {
		CheckDelMsg msg = new CheckDelMsg();
		msg.setCanDel(true);
		if (moduleId == 0) {
			return msg;
		}
		int count = Api.dao.getCountByModuleId(moduleId);
		if(count > 0){
			msg.setCanDel(false);
			msg.setMsg("【模块】下面关联【API】,不可删除");
		}
		count = getCountByParentId(moduleId);
		if(count > 0){
			msg.setCanDel(false);
			msg.setMsg("【模块】下面关联【子模块】,不可删除");
		}
		return msg;
	}
	
	/**
	 * 根据项目ID获取模块数量
	 * @param projectId
	 * @return
	 */
	public int getCountByProjectId(Integer projectId) {
		List<Module> list = this.listByProjectId(projectId);
		return list == null ? 0 : list.size();
	}
}
