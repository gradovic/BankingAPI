package bank.servlets;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.UserDAOImpl;
import bank.model.User;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/add_user")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAOImpl userImpl = new UserDAOImpl();
		User add = new User();
		add.setBranchID(Integer.parseInt(request.getParameter("branchid")));
		add.setFirstName(request.getParameter("firstname"));
		add.setLastName(request.getParameter("lastname"));
		add.setEmail(request.getParameter("email"));
		add.setPassword(request.getParameter("pass"));
		add.setDOB(LocalDate.parse(request.getParameter("dob")));
		boolean isAdded = userImpl.addUser(add);
		
		if(isAdded) {
			System.out.println("ok");
			response.getWriter().append("user has been added successflly");
		}else {
			System.out.println("wrong");
			response.getWriter().append("smth went wrong! try again.");
		}
	}

}
