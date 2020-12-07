package io.mountblue.learn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import io.mountblue.learn.dbconnect.ConnectToDatabase;
import io.mountblue.learn.pojo.Request;

public class ContactUsDAO {
	
	ConnectToDatabase connectionObj=new ConnectToDatabase();
	public void addRequest(Request request) {
		PreparedStatement st;
		try (Connection con=connectionObj.dbConnect()) {
			st = con.prepareStatement("insert into user_requests(name,email,message,is_archived) values(?,?,?,?);");
			st.setString(1, request.getName());
			st.setString(2, request.getEmail());
			st.setString(3, request.getMessage());
			st.setBoolean(4, request.isArchived());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
