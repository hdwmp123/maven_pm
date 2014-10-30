package cn.com.king.nutz.model;

import java.util.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("project_module")
@Comment("API模块管理")
public class Module {
	@Id
	@Comment("主键")
	public int id;

	@Comment("隶属项目")
	public int project_id;

	@Comment("模块名称")
	@ColDefine(width = 200)
	public String module_name;
	
	@Comment("排序")
	public int module_index;
	
	@Comment("创建时间")
	public Date created;

	@Comment("域")
	public int domain;

}
