package io.mountblue.learn.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.mountblue.learn.dao.ContactUsDAO;
import io.mountblue.learn.pojo.Request;


@WebServlet("/contactUsSubmit")
public class ContactUsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ContactUsDAO contactUsDao=new ContactUsDAO();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		
		if(command.equals("ADD_REQUEST")) {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String message = request.getParameter("message");
		Request theRequest = new Request(name, email, message);
		contactUsDao.addRequest(theRequest);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/success.jsp");
		dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect("/admin/login");
		}
	}
}
