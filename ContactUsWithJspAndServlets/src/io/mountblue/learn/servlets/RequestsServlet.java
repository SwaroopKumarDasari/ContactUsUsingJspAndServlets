package io.mountblue.learn.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import io.mountblue.learn.dao.RequestsDAO;
import io.mountblue.learn.pojo.Request;

@WebServlet("/admin/contactUs/requests")
public class RequestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	RequestsDAO requestDao = new RequestsDAO();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("userName");
		String password = (String) session.getAttribute("pass");
		if (!authorizedUser(username, password)) {
			response.sendRedirect("/admin/login");
		} 
		else {
			String command = request.getParameter("command");
			if (command == null) {
				command = "SHOW_ACTIVE_REQ";
			}
			switch (command) {
			case "SHOW_ACTIVE_REQ":
				getActiveRequests(request, response, false, command);
				break;
			case "ARCHIVE":
				updateRequest(request, response, true, command);
				break;
			case "VIEW_ARCHIVED":
				getActiveRequests(request, response, true, command);
				break;
			case "UN_ARCHIVE":
				updateRequest(request, response, false, command);
				break;
			case "LOGOUT":
				logoutAdmin(request,response);
				break;
			}
		}
	}

	private void logoutAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
        response.sendRedirect("/admin/login");	
	}

	private boolean authorizedUser(String username, String password) {
		if (username == null || password == null) {
			return false;
		}
		return true;
	}

	private void updateRequest(HttpServletRequest request, HttpServletResponse response, boolean archive,
			String command) throws IOException, ServletException {
		int requestId = Integer.parseInt(request.getParameter("requestId"));
		requestDao.updateRequest(requestId, archive);
		getActiveRequests(request, response, !archive, command);
	}

	private void getActiveRequests(HttpServletRequest request, HttpServletResponse response, boolean archived,
			String command) throws IOException, ServletException {
		if (command != null) {
			List<Request> requests = null;
			requests = requestDao.getActiveRequests(archived);
			request.setAttribute("requestDetails", requests);
			if (!archived) {
				request.setAttribute("toggler", true);
			} else {
				request.setAttribute("toggler", false);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/list-requests.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("/admin/login");
		}
	}

}
