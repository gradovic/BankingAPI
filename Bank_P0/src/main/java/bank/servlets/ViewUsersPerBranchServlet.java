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

import com.fasterxml.jackson.databind.ObjectMapper;

import bank.JWT.JwtManager;
import bank.dao.BranchDAOImpl;
import bank.dao.UserDAOImpl;
import bank.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * Servlet implementation class ViewUsersPerBranch
 */
@WebServlet("/user_branches")
public class ViewUsersPerBranchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String authTokenHeader = request.getHeader("Authorization");
		if (authTokenHeader != null && !authTokenHeader.isEmpty()) {

			try {
				Jws<Claims> parsedToken = JwtManager.parseToken(authTokenHeader);
				String query = request.getQueryString();
				try {
					int branchID = Integer.parseInt(query);
					BranchDAOImpl branchImpl = new BranchDAOImpl();
					List<User> users = branchImpl.getAllUsersByBranch(branchID);
					if (users.size() > 0) {
						String jsonString = objectMapper.writeValueAsString(users);
						response.getWriter().append(jsonString);
						response.setStatus(200);
						response.setContentType("application/json");
					} else {
						response.getWriter().append("No users in this branch!!");
						response.setStatus(404);
					}

				} catch (NumberFormatException e) {
					response.getWriter().append("branchID must be number only!! (eg url/user_branches?2)");
					response.setStatus(422);
				}
			} catch(Exception e) {
				e.printStackTrace();
				response.getWriter().append("Invalid Token, Please login");
				response.setStatus(401);
			}
				
		} else {
			response.getWriter().append("No Token provided, Please login!!");
			response.setStatus(401);
		}

	}

}
