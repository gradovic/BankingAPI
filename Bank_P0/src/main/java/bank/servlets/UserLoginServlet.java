package bank.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;


import bank.JWT.JwtManager;
import bank.dao.Test;
import bank.dao.UserDAOImpl;
import bank.model.User;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(UserLoginServlet.class);
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAOImpl userImpl = new UserDAOImpl();
		String username = request.getParameter("username");
		String password = request.getParameter("password"); 		
		if(username !=null && !username.isEmpty() && password !=null && !password.isEmpty()) {
			User user = userImpl.getUserByEmail(username);
			if(user != null) {
				String retrivedHash = user.getPassword();
				if(BCrypt.checkpw(password, retrivedHash)) {
					//User is authenticated, then let's generate JWT token
					String token = JwtManager.createToken(String.valueOf(user.getUserID()), user.getRole(), username);
					response.getWriter().append(token);
					response.setStatus(200);
					logger.debug(username + " logged in");
				}else {
					response.getWriter().append("Incorrect Username/Paswowrd!!");
					response.setStatus(401);
				}
				
				
				
			}else {
				response.getWriter().append("Incorrect Username/Paswowrd!!");
				response.setStatus(401);
			}
			
		}else {
			response.getWriter().append("Username & Password are required");
			response.setStatus(422);
		}
		
	}

}
