package cn.com.king.jfinal.model.project.api;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import java.util.List;

@SuppressWarnings("serial")
public class Api extends Model<Api> {

	public static final Api dao = new Api();

	public Api() {
	}

	public Page<Api> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select a.*",
				"from project_api a order by a.api_index asc");
	}

	public List<Api> listAll() {
		return find("select * from project_api order by id asc");
	}

}
