package cn.com.king.jfinal.model.project.table;

import java.util.ArrayList;
import java.util.List;

import cn.com.king.jfinal.model.bean.CheckDelMsg;
import cn.com.king.jfinal.model.project.Project;
import cn.com.king.jfinal.model.project.table.column.Column;
import cn.com.king.jfinal.util.BeanUtil;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

@SuppressWarnings("serial")
public class Table extends Model<Table> {

	public static final Table dao = new Table();

	public Table() {
	}
	
	/**
	 * 分页
	 * @param pageNumber
	 * @param pageSize
	 * @param tableName
	 * @param projectId
	 * @return
	 */
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
	
	/**
	 * 获取所有
	 * @return
	 */
	public List<Table> listAll() {
		return find("select * from project_table order by id asc");
	}
	
	/**
	 * 属性
	 * @return
	 */
	public Project getProject() {
		return Project.dao.getById(getInt("project_id"));
	}
	
	/**
	 * 根据ID获取
	 * @param id
	 * @return
	 */
	public Table getById(Integer id) {
		if (id == null) {
			return null;
		}
		return findById(id);
	}
	
	/**
	 * 
	 * @param projectId
	 * @return
	 */
	public List<Table> listByProjectId(Integer projectId) {
		return find(
				"select a.* from project_table a where a.project_id = ? order by a.id asc",
				projectId);
	}
	
	/**
	 * 根据 projectId 获取数量
	 * @param projectId
	 * @return
	 */
	public int getCountByProjectId(Integer projectId) {
		List<Table> list = this.listByProjectId(projectId);
		return list == null ? 0 : list.size();
	}

	public CheckDelMsg checkDelete(Integer tableId) {
		CheckDelMsg msg = new CheckDelMsg();
		msg.setCanDel(true);
		if (tableId == 0) {
			return msg;
		}
		int count = Column.dao.getCountByTableId(tableId);
		if(count > 0) {
			msg.setCanDel(false);
			msg.setMsg("【表】下面关联【列】,不可删除");
		}
		return msg;
	}

}
