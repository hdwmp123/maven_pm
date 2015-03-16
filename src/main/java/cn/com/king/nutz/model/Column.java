package cn.com.king.nutz.model;

import java.util.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("table_column")
@Comment("列")
public class Column {
	@Id
	@Comment("主键")
	public int id;
	
	@Comment("隶属项目")
	public int project_id;
	
	@Comment("隶属表")
	public int table_id;
	
	@Comment("列名")
	@ColDefine(width = 200)
	public String column_name;
	
	@Comment("列数据类型")
	@ColDefine(width = 200)
	public String column_type;
	
	@Comment("是否为空")
	@ColDefine(width = 200)
	public String is_nullable;
	
	@Comment("默认值")
	@ColDefine(width = 200)
	public String column_default;
	
	@Comment("说明")
	@ColDefine(width = 200)
	public String column_comment;
	
	@Comment("排序")
	public int column_index;
	
	@Comment("创建时间")
	public Date created;
	
	@Comment("域")
	public int domain;
}
