package com.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private Connection connection;

	public Connection getConnection() {
		return connection;
	}

	public DBConnection(String url, String username, String pwd) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(url,username,pwd);
	}
	
	public void closeConnection() throws SQLException  {
		if(this.connection != null) {
			connection.close();
		}
	}

}
