package cn.com.king.nutz.test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.nutz.dao.Dao;

import cn.com.king.nutz.dao.DaoUtil;
import cn.com.king.nutz.model.Column;
import cn.com.king.nutz.model.Table;

public class ReadTableStructure {

	public ReadTableStructure() {
	}

	public static void main(String args[]) {
		try {
			run();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void run() throws SQLException {
		String schema = "xc";
		String sql = String
				.format("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES a WHERE a.TABLE_SCHEMA = '%s'",
						schema);
		SqlConnect connect = new SqlConnect();
		connect.query(sql, false);
		ResultSet resultSet = connect.getResultSet();
		List<String> tables = new ArrayList<String>();
		while (resultSet.next()) {
			tables.add(resultSet.getString("table_name"));
		}
		if (tables.size() == 0) {
			return;
		}
		String sqlRest = "SELECT rno_reset() FROM DUAL";
		String sqlTableStructure = "SELECT rno() column_index,a.column_name column_name,"
				+ "a.column_type column_type,a.is_nullable is_nullable,"
				+ "a.column_default column_default,a.column_comment column_comment "
				+ "FROM information_schema.COLUMNS a "
				+ "WHERE a.table_schema = '%s' " + "AND a.table_name = '%s'";
		Date curent = new Date();
		Dao dao = DaoUtil.getDao();
		int index = 1;
		for (int i = 0; i < tables.size(); i++) {
			index = 1;
			String tb = tables.get(i);
			Table table = new Table();
			table.project_id = 2;
			table.table_name = tb;
			table.table_index = i + 1;
			table.created = curent;
			dao.insert(table);
			connect.query(sqlRest, false);
			connect.query(String.format(sqlTableStructure, schema, tb), false);
			Column column;
			resultSet = connect.getResultSet();
			while (resultSet.next()) {
				column = new Column();
				column.table_id = table.id;
				column.column_name = resultSet.getString("column_name");
				column.column_type = resultSet.getString("column_type");
				column.is_nullable = resultSet.getString("is_nullable");
				column.column_default = resultSet.getString("column_default");
				column.column_comment = resultSet.getString("column_comment");
				column.column_index = index;
				column.column_default = resultSet.getString("column_default");
				column.created = curent;
				dao.insert(column);
				index++;
			}
		}
	}
}
