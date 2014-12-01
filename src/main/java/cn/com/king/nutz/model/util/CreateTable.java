package cn.com.king.nutz.model.util;

import org.nutz.dao.Dao;

import cn.com.king.nutz.dao.DaoUtil;
import cn.com.king.nutz.model.Api;
import cn.com.king.nutz.model.CodeManager;
import cn.com.king.nutz.model.Column;
import cn.com.king.nutz.model.Module;
import cn.com.king.nutz.model.Project;
import cn.com.king.nutz.model.Table;

public class CreateTable {

	public CreateTable() {
	}

	public static void main(String args[]) {
		Dao dao = DaoUtil.getDao();
//		dao.create(Api.class, true);
//		dao.create(CodeManager.class, true);
//		dao.create(Column.class, true);
		dao.create(Module.class, true);
//		dao.create(Project.class, true);
//		dao.create(Table.class, true);
		System.out.println("OK");
	}
}
