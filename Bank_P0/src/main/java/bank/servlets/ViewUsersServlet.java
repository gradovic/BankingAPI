package bank.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.UserDAOImpl;
import bank.model.Branch;
import bank.model.User;

/**
 * Servlet implementation class ViewUsersServlet
 */
@WebServlet("/get_users")
public class ViewUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAOImpl impl = new UserDAOImpl();
		List<User> users = impl.getAllusers();
		String output = "";
		for(User user:users) {
			output = output.concat("<tr><td>" + user.getBranchID() + "</td><td>" + user.getFirstName() + "</td><td>" + user.getLastName() + "</td><td>" + user.getEmail() + "</td><td>" + user.getDOB() + "</td></tr>");
		}
		response.getWriter().append("<table style='border: 1px solid black;'><tr><th>Branch ID</th><th>First Name</th><th>Last Name</th><th>Email</th><th>DOB</th></tr>" + output + "</table>");
		
	}

}
