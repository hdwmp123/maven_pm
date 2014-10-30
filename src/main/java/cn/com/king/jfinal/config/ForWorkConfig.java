package cn.com.king.jfinal.config;

import cn.com.king.jfinal.model.codemanager.CodeManager;
import cn.com.king.jfinal.model.codemanager.CodeManagerController;
import cn.com.king.jfinal.model.common.CommonController;
import cn.com.king.jfinal.model.project.Project;
import cn.com.king.jfinal.model.project.ProjectController;
import cn.com.king.jfinal.model.project.api.Api;
import cn.com.king.jfinal.model.project.api.ApiController;
import cn.com.king.jfinal.model.project.table.Table;
import cn.com.king.jfinal.model.project.table.TableController;
import cn.com.king.jfinal.model.project.table.column.Column;
import cn.com.king.jfinal.model.project.table.column.ColumnController;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;

public class ForWorkConfig extends JFinalConfig {

	public ForWorkConfig() {
	}

	public void configConstant(Constants me) {
		loadPropertyFile("config.properties");
		me.setDevMode(getPropertyToBoolean("devMode", false).booleanValue());
	}

	public void configRoute(Routes me) {
		me.add("/", CommonController.class);
		me.add("/project", ProjectController.class);
		me.add("/table", TableController.class);
		me.add("/column", ColumnController.class);
		me.add("/api", ApiController.class);
		me.add("/code_manager", CodeManagerController.class);
	}

	public void configPlugin(Plugins me) {
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"),
				getProperty("user"), getProperty("password").trim());
		me.add(c3p0Plugin);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		arp.addMapping("project", Project.class);
		arp.addMapping("project_table", Table.class);
		arp.addMapping("table_column", Column.class);
		arp.addMapping("project_api", Api.class);
		arp.addMapping("code_manager", CodeManager.class);
		me.add(arp);
	}

	public void configInterceptor(Interceptors interceptors) {
	}

	public void configHandler(Handlers handlers) {
	}

	public static void main(String args[]) {
		JFinal.start("src/main/webapp", 81, "/", 5);
	}
}
