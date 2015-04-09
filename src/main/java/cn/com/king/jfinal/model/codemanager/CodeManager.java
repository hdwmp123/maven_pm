package cn.com.king.jfinal.model.codemanager;

import cn.com.king.jfinal.model.bean.CheckDelMsg;
import cn.com.king.jfinal.util.BeanUtil;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class CodeManager extends Model<CodeManager> {

	public static final CodeManager dao = new CodeManager();
	
	/**
	 * 分页
	 * @param pageNumber
	 * @param pageSize
	 * @param namespace
	 * @param fatherId
	 * @return
	 */
	public Page<CodeManager> paginate(
			int pageNumber, 
			int pageSize,
			String namespace,
			Long fatherId) {
		StringBuffer where = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		if(BeanUtil.checkStr(namespace)){
			where.append(" and a.namespace = ? ");
			params.add(namespace);
		}
		if(BeanUtil.checkStr(fatherId)){
			where.append(" and a.father_id = ? ");
			params.add(fatherId);
		}
		return paginate(pageNumber, pageSize, "select a.*",
				"from code_manager a where 1=1 "+ where.toString()
				+" order by a.id asc", params.toArray());
	}
	
	/**
	 * 获取所有
	 * @return
	 */
	public List<CodeManager> listAll() {
		return find("select * from code_manager order by id asc");
	}
	
	/**
	 * 根据 namespace 获取集合
	 * @param namespace
	 * @return
	 */
	public List<CodeManager> listByNamespace(String namespace) {
		return find("select * from code_manager a where a.namespace=? order by a.id asc",namespace);
	}

	public CheckDelMsg checkDelete(Integer codeManagerId) {
		CheckDelMsg msg = new CheckDelMsg();
		msg.setCanDel(true);
		if (codeManagerId == 0) {
			return msg;
		}
		msg.setCanDel(false);
		msg.setMsg("你想干啥，不要乱删除");
		return msg;
	}

}
