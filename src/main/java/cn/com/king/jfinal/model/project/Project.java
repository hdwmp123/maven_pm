package cn.com.king.jfinal.model.project;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import java.util.List;

@SuppressWarnings("serial")
public class Project extends Model<Project> {

	/**
	 * 
	 */
	public static final Project dao = new Project();

	public Project() {
	}

	public Page<Project> paginate(int pageNumber, int pageSize) {
		return paginate(pageNumber, pageSize, "select *",
				"from project order by id asc");
	}

	public List<Project> listAll() {
		return find("select * from project order by id asc");
	}

	public Project getById(int id) {
		return (Project) findById(Integer.valueOf(id));
	}

}
