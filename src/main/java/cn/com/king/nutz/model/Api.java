package cn.com.king.nutz.model;

import java.util.Date;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.ColType;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("project_api")
@Comment("API表")
public class Api {
	@Id
	@Comment("主键")
	public int id;

	@Comment("隶属项目ID")
	public int project_id;

	@Comment("隶属模块ID")
	public int module_id;

	@Comment("API名称")
	@ColDefine(width = 200)
	public String api_name;

	@Comment("API地址")
	@ColDefine(width = 200)
	public String api_url;

	@Comment("API请求方式")
	@ColDefine(type=ColType.TEXT)
	public String api_request_param;

	@Comment("API请求参数")
	@ColDefine(type=ColType.TEXT)
	public String api_response_param;

	@Comment("API响应参数")
	@ColDefine(width = 200)
	public String api_type;

	@Comment("说明")
	@ColDefine(width = 200)
	public String api_comment;

	@Comment("排序")
	public int api_index;

	@Comment("创建时间")
	public Date created;

	@Comment("域")
	public int domain;

}
