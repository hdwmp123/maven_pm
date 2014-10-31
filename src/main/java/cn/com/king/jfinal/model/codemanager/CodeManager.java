package cn.com.king.jfinal.model.codemanager;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import java.util.List;

@SuppressWarnings("serial")
public class CodeManager extends Model<CodeManager> {

	public static final CodeManager dao = new CodeManager();

	public Page<CodeManager> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *",
				"from code_manager order by id asc");
	}

	public List<CodeManager> listAll() {
		return find("select * from code_manager order by id asc");
	}

	public List<CodeManager> listByNamespace(String namespace) {
		return find(
				"select * from code_manager a where a.namespace=? order by id asc",
				namespace);
	}

}
