package bank.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.BranchDAOImpl;
import bank.model.User;

/**
 * Servlet implementation class ViewUsersPerBranch
 */
@WebServlet("/users_per_branch")
public class ViewUsersPerBranch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String query = request.getQueryString();
		try {
		int branchID = Integer.parseInt(query);
		BranchDAOImpl branchImpl = new BranchDAOImpl();
		List<User> users = branchImpl.getAllUsersByBranch(branchID);
		String output = "";
		for (User user:users) {
			output = output.concat(user.getFirstName() + ",");
		}
		response.getWriter().append(output);
		}catch (NumberFormatException e) {
			response.getWriter().append("branchID must be number only!! (eg url/?2)");
		}
		
		
	}

}
