package bank.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import bank.JWT.JwtManager;
import bank.dao.UserDAOImpl;
import bank.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/add_user")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String authTokenHeader = request.getHeader("Authorization");
		if(authTokenHeader != null && !authTokenHeader.isEmpty()) {
			try {
				Jws<Claims> parsedToken = JwtManager.parseToken(authTokenHeader);
				UserDAOImpl userImpl = new UserDAOImpl();
				User add = new User();
				add.setBranchID(Integer.parseInt(request.getParameter("branchid")));
				add.setFirstName(request.getParameter("firstname"));
				add.setLastName(request.getParameter("lastname"));
				add.setEmail(request.getParameter("email"));
				// hash the password before saving to DB using BCrypt
				String password = request.getParameter("pass");
				String hashed = BCrypt.hashpw(password, BCrypt.gensalt(12));
				add.setPassword(hashed);
				add.setDOB(LocalDate.parse(request.getParameter("dob")));
				add.setRole(request.getParameter("role"));
				boolean isAdded = userImpl.addUser(add);
				
				if(isAdded) {
					System.out.println("ok");
					response.getWriter().append("Caller: " + parsedToken.getBody().get("email") + "\n user has been added successflly");
					response.setStatus(200);
				}else {
					System.out.println("wrong");
					response.getWriter().append("smth went wrong! try again.");
					response.setStatus(500);
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
