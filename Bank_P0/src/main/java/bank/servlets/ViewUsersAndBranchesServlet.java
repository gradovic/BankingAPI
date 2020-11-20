package bank.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import bank.JWT.JwtManager;
import bank.dao.BranchDAOImpl;
import bank.model.Branch;
import bank.template.UserBranchIntImpl;
import bank.template.UserBranchTemplate;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * Servlet implementation class ViewUsersAndBranchesServlet
 */
@WebServlet("/users_branches")
public class ViewUsersAndBranchesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();
	
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String authTokenHeader = request.getHeader("Authorization");
		if(authTokenHeader != null && !authTokenHeader.isEmpty()) {
			try {
				Jws<Claims> parsedToken = JwtManager.parseToken(authTokenHeader);
				UserBranchIntImpl userBranch = new UserBranchIntImpl();
				List<UserBranchTemplate> usersBranches = userBranch.getUsersBranchName();
				String jsonString = objectMapper.writeValueAsString(usersBranches);
				response.getWriter().append("Caller: " + parsedToken.getBody().get("email") + " >> " + parsedToken.getBody().get("role") + "\n" + jsonString);
				response.setStatus(200);
				response.setContentType("application/json");
			}catch (Exception e){
				e.printStackTrace();
				response.getWriter().append("Invalid Token, Please login");
				response.setStatus(401);
			}
			
		}else {
			response.getWriter().append("No Token provided, Please login!!");
			response.setStatus(401);
		}

	}

}
