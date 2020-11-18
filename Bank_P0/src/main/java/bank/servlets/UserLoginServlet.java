package bank.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import com.google.gson.Gson;

import bank.JWT.JwtManager;
import bank.dao.UserDAOImpl;
import bank.model.User;

/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson(); 

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
					String token = JwtManager.createToken(String.valueOf(user.getUserID()), user.getRole());
					response.getWriter().append(token);
					/*
					 * String output = this.gson.toJson(user); PrintWriter out =
					 * response.getWriter(); response.setContentType("application/json");
					 * response.setCharacterEncoding("UTF-8"); out.print(output); out.flush();
					 */
				}else {
					response.getWriter().append("Incorrect Username/Paswowrd!!");
				}
				
				
				
			}else {
				response.getWriter().append("Incorrect Username/Paswowrd!!");
			}
			
		}else {
			response.getWriter().append("Username & Password are required");
		}
		
	}

}
