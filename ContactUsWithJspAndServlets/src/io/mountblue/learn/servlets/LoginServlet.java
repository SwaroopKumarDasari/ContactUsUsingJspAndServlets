package io.mountblue.learn.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.mountblue.learn.dao.LoginDAO;

@WebServlet("/admin/loginValidation")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	LoginDAO loginDao = new LoginDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("uname");
		String password = request.getParameter("password");
		if (loginDao.validateLoginDetails(username, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("userName", username);
			session.setAttribute("pass", password);
			response.sendRedirect("/admin/contactUs/requests");
		} else {
			response.sendRedirect("/admin/login");
		}
	}

}
