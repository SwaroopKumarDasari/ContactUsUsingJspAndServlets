package io.mountblue.learn.dbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectToDatabase {
	private static final String url = "jdbc:postgresql://localhost:5432/Requests";
	private static final String name = "postgres";
	private static final String dbPassword = "somu9042";

	public Connection dbConnect() {
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url, name, dbPassword);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
}
