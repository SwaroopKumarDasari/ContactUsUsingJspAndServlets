package io.mountblue.learn.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.mountblue.learn.dbconnect.ConnectToDatabase;
import io.mountblue.learn.pojo.Request;

public class RequestsDAO {

	ConnectToDatabase connectionObj=new ConnectToDatabase();
	public List<Request> getActiveRequests(boolean archived) {
		List<Request> requests = new ArrayList<>();
		PreparedStatement st;
		ResultSet rs;
		try (Connection con=connectionObj.dbConnect()) {
			st = con.prepareStatement("select * from Public.\"user_requests\" where is_archived=" + archived + ";");
			rs = st.executeQuery();
			while (rs.next()) {
				int reqId = rs.getInt("request_id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String message = rs.getString("message");
				Request request = new Request(reqId, name, email, message, archived);
				requests.add(request);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return requests;
	}

	public void updateRequest(int reqId, boolean archive) {
		PreparedStatement st;
		try (Connection con=connectionObj.dbConnect()) {
			st = con.prepareStatement(
					"update Public.\"user_requests\" set is_archived=" + archive + " where request_id=" + reqId + ";");
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
