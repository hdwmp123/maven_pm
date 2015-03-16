package cn.com.king.nutz.model;

import java.util.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("project")
@Comment("API表")
public class Project {
	@Id
	@Comment("主键")
	public int id;

	@Comment("项目名称")
	@ColDefine(width = 200)
	public String project_name;
	
	@Comment("排序")
	public int project_index;

	@Comment("创建时间")
	public Date created;

	@Comment("域")
	public int domain;
}
