package io.mountblue.learn.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDatabase {
	private static final String url = "jdbc:postgresql://localhost:5432/Requests";
	private static final String name = "postgres";
	private static final String dbPassword = "somu9042";
	
	public Connection dbConnect() {
		loadPostgresDriver();
		Connection con=null;
		try {
			con = DriverManager.getConnection(url, name, dbPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	private void loadPostgresDriver() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
