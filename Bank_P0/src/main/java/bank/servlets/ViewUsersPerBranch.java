package bank.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import bank.dao.BranchDAOImpl;
import bank.model.User;

/**
 * Servlet implementation class ViewUsersPerBranch
 */
@WebServlet("/user_branches")
public class ViewUsersPerBranch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String query = request.getQueryString();
		try {
		int branchID = Integer.parseInt(query);
		BranchDAOImpl branchImpl = new BranchDAOImpl();
		List<User> users = branchImpl.getAllUsersByBranch(branchID);
		ArrayList<String> output = new ArrayList<String>();
		for(User user:users) {
			// convert user objects to JSON format
			output.add(this.gson.toJson(user));
		}
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(output);
		out.flush();
		}catch (NumberFormatException e) {
			response.getWriter().append("branchID must be number only!! (eg url/user_branches?2)");
		}
		
		
	}

}
