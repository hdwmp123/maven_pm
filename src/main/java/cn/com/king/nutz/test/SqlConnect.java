package cn.com.king.nutz.test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SqlConnect {

	private static String driver;
	private static String connectionUrl;
	private static String user;
	private static String password;
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;

	public SqlConnect() {
		connection = null;
		statement = null;
		resultSet = null;
		createConnection();
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Statement getStatement() {
		return statement;
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public boolean createConnection() {
		boolean result = false;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(connectionUrl, user,
					password);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean execute(String sql, boolean printSql) {
		boolean b = false;
		try {
			if (printSql)
				System.out.println(sql);
			statement = connection.createStatement();
			statement.execute(sql);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public boolean update(String sql, boolean printSql) {
		boolean b = false;
		try {
			if (printSql)
				System.out.println(sql);
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public void query(String sql, boolean printSql) {
		try {
			if (printSql)
				System.out.println(sql);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean next() {
		boolean b = false;
		try {
			if (resultSet.next())
				b = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}

	public String getValue(String colKey) {
		String value = null;
		try {
			if (resultSet != null)
				value = resultSet.getString(colKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public int getNum(String sql) {
		int i = 0;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			resultSet.last();
			i = resultSet.getRow();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int getNum(String sql, String countKey) {
		int i = 0;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			resultSet.next();
			i = resultSet.getInt(countKey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return i;
	}

	public int getIntValueByString(String colKey) {
		int value = 0;
		try {
			if (resultSet != null) {
				String temp = resultSet.getString(colKey);
				value = Integer.parseInt(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public float getFloatValueByString(String colKey) {
		float value = 0.0F;
		try {
			if (resultSet != null) {
				String temp = resultSet.getString(colKey);
				value = Float.parseFloat(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public int getIntValue(int colIndex) {
		int value = 0;
		try {
			if (resultSet != null)
				value = resultSet.getInt(colIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	public void delete(String sql) {
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void add(String sql) {
		try {
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeStatement() {
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeResultSet() {
		try {
			if (resultSet != null)
				resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeAll() {
		closeResultSet();
		closeStatement();
		closeConnection();
	}

	public static void main(String args[]) {
		SqlConnect db = new SqlConnect();
		if (db.createConnection()) {
			String check = "select 1 count_ from dual";
			db.query(check, true);
			for (; db.next(); System.out.println(db.getValue("count_")))
				;
			db.closeAll();
		}
	}

	static {
		Properties props;
		InputStream in;
		driver = "";
		connectionUrl = "";
		user = "";
		password = "";
		props = new Properties();
		in = null;
		in = SqlConnect.class.getResourceAsStream("/database.properties");
		try {
			props.load(in);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		driver = props.getProperty("database.driver");
		connectionUrl = props.getProperty("database.url");
		user = props.getProperty("database.username");
		password = props.getProperty("database.password");
		try {
			if (in != null) {
				in.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
