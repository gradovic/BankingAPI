package bank.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.JWT.JwtManager;
import bank.dao.UserDAOImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/delete_user")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String authTokenHeader = request.getHeader("Authorization");
		if(authTokenHeader != null && !authTokenHeader.isEmpty()) {
			try {
				Jws<Claims> parsedToken = JwtManager.parseToken(authTokenHeader);
				String query = request.getQueryString();
				try {
				int userID = Integer.parseInt(query);
				UserDAOImpl userImpl = new UserDAOImpl();
				boolean isDeleted = userImpl.deleteUser(userID);
				
				if(isDeleted) {
					response.getWriter().append("User has been deleted");
					response.setStatus(200);
				}else {
					response.getWriter().append("User doesn't exist!!");
					response.setStatus(404);
				}
				}catch (NumberFormatException e) {
					response.getWriter().append("userID must be number only!! (eg url/delete_user?2)");
					response.setStatus(422);
				}
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
