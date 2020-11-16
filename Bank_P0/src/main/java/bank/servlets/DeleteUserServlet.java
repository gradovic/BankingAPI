package bank.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.UserDAOImpl;

/**
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/delete_user")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String query = request.getQueryString();
		try {
		int userID = Integer.parseInt(query);
		UserDAOImpl userImpl = new UserDAOImpl();
		boolean isDeleted = userImpl.deleteUser(userID);
		
		if(isDeleted) {
			response.getWriter().append("User has been deleted");
		}else {
			response.getWriter().append("User doesn't exist!!");
		}
		}catch (NumberFormatException e) {
			response.getWriter().append("userID must be number only!! (eg url/delete_user?2)");
		}
	}

}
