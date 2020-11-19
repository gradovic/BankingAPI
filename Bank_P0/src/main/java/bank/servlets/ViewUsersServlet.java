package bank.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import bank.dao.UserDAOImpl;
import bank.model.User;

/**
 * Servlet implementation class ViewUsersServlet
 */
@WebServlet("/get_users")
public class ViewUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserDAOImpl impl = new UserDAOImpl();
		List<User> users = impl.getAllusers();
		String jsonString = objectMapper.writeValueAsString(users);
		response.getWriter().append(jsonString);
	}

}
