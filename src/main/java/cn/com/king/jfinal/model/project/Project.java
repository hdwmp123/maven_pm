package cn.com.king.jfinal.model.project;

import java.util.ArrayList;
import java.util.List;

import cn.com.king.jfinal.model.bean.CheckDelMsg;
import cn.com.king.jfinal.model.project.module.Module;
import cn.com.king.jfinal.model.project.table.Table;
import cn.com.king.jfinal.util.BeanUtil;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class Project extends Model<Project> {

	/**
	 * 
	 */
	public static final Project dao = new Project();

	public Project() {
	}
	
	/**
	 * 分页
	 * @param pageNumber
	 * @param pageSize
	 * @param projectName
	 * @return
	 */
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
	
	/**
	 * 获取所有
	 * @return
	 */
	public List<Project> listAll() {
		return find("select * from project order by project_index asc");
	}
	
	/**
	 * 根据ID获取
	 * @param id
	 * @return
	 */
	public Project getById(Integer id) {
		if (id == null) {
			return null;
		}
		return findById(id);
	}
	
	/**
	 * 校验删除
	 * @param projectId
	 * @return
	 */
	public CheckDelMsg checkDelete(Integer projectId) {
		CheckDelMsg msg = new CheckDelMsg();
		msg.setCanDel(true);
		if (projectId == 0) {
			return msg;
		}
		int count = Module.dao.getCountByProjectId(projectId);
		if(count > 0) {
			msg.setCanDel(false);
			msg.setMsg("【项目】下面关联【模块】,不可删除");
		}
		count = Table.dao.getCountByProjectId(projectId);
		if(count > 0) {
			msg.setCanDel(false);
			msg.setMsg("【项目】下面关联【表】,不可删除");
		}
		return msg;
	}

}
