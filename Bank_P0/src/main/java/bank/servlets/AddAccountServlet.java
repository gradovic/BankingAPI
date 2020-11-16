package bank.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.AccountDAOImpl;
import bank.dao.BranchDAOImpl;
import bank.dao.UserDAOImpl;
import bank.model.Account;
import bank.model.Branch;
import bank.model.User;

/**
 * Servlet implementation class AddAccountServlet
 */
@WebServlet("/add_account")
public class AddAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AccountDAOImpl accountImpl = new AccountDAOImpl();
		Account add = new Account();
		add.setUserID(Integer.parseInt(request.getParameter("userid")));
		add.setStatus(request.getParameter("status"));
		add.setBalance(Double.parseDouble(request.getParameter("balance")));

		boolean isAdded = accountImpl.addAccount(add);

		if (isAdded) {

			response.getWriter().append("Account has been added successflly");
		} else {

			response.getWriter().append("smth went wrong! try again.");
		}

	}

}
