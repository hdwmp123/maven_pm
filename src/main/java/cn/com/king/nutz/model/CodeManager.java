package cn.com.king.nutz.model;

import java.util.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("code_manager")
@Comment("常量表")
public class CodeManager {
	@Id
	@Comment("主键")
	public int id;

	@Comment("API名称")
	@ColDefine(width = 200)
	public String name;

	@Comment("父级ID")
	public int father_id;

	@Comment("代码程序识别")
	@ColDefine(width = 200)
	public String code;

	@Comment("命名空间")
	@ColDefine(width = 200)
	public String namespace;

	@Comment("是否默认")
	public int is_default;

	@Comment("创建时间")
	public Date created;

	@Comment("域")
	public int domain;

}
