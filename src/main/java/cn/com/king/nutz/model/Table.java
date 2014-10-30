package cn.com.king.nutz.model;

import java.util.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;

@org.nutz.dao.entity.annotation.Table("project_table")
@Comment("API表")
public class Table {
	@Id
	@Comment("主键")
	public int id;
	
	@Comment("隶属项目ID")
	public int project_id;
	
	@Comment("表名")
	@ColDefine(width = 200)
	public String table_name;
	
	@Comment("排序")
	public int table_index;
	
	@Comment("创建时间")
	public Date created;

	@Comment("域")
	public int domain;

}
