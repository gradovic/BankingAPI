package bank.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.AccountDAOImpl;
import bank.model.Account;

/**
 * Servlet implementation class DeleteAccountServlet
 */
@WebServlet("/delete_account")
public class DeleteAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String query = request.getQueryString();
		try {
			int accountID = Integer.parseInt(query);
			AccountDAOImpl accountImpl = new AccountDAOImpl();
			boolean isAdded = accountImpl.deleteAccount(accountID);
			if (isAdded) {
			
				response.getWriter().append("Account has been deleted successflly");
			} else {
				
				response.getWriter().append("smth went wrong! try again.");
			}
			
		}catch (NumberFormatException e) {
			response.getWriter().append("accountID must be number only!! (eg url/delete_account?2)");
		} 
		

		
	}

}
