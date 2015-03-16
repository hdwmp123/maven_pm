package cn.com.king.jfinal.model.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.sf.json.JSONObject;

import com.jfinal.core.Controller;

public class TestController extends Controller{
	public void testDB(){
		JSONObject json = new JSONObject();
		String driver = "com.mysql.jdbc.Driver";
		String connectionUrl_ = "jdbc:mysql://%s:3306/%s?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=UTF8&amp;characterSetResults=UTF8";
		String ip = getPara("ip");
		String db = getPara("db");
		String user = getPara("user");
		String password = getPara("password");
		String  connectionUrl = String.format(connectionUrl_, ip, db);
		//
		try {
			Class.forName(driver);
			Connection connection = DriverManager.getConnection(connectionUrl, user, password);
			String check = "select 1 count_ from dual";
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(check);
			String count_ = null;
			while(resultSet.next()){
				count_ = resultSet.getString("count_");
			}
			json.put("success", true);
			json.put("count", count_);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			json.put("success", false);
			json.put("msg", e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			json.put("success", false);
			json.put("msg", e.getMessage());
		}
		//
		renderJson(json);
	}
}
