package bank.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

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
		
		if(username !=null && password !=null) {
			User user = userImpl.getUserByEmail(username);
			String output = "";
			if(user != null) {
				output = this.gson.toJson(user);
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				out.print(output);
				out.flush();
				
			}else {
				response.getWriter().append("User not found!!");
			}
			
		}
		
	}

}
