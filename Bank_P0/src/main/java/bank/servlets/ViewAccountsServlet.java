package bank.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import bank.dao.AccountDAOImpl;
import bank.model.Account;
import bank.model.Branch;

/**
 * Servlet implementation class ViewAccountsServlet
 */
@WebServlet("/get_accounts")
public class ViewAccountsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAOImpl accountImpl = new AccountDAOImpl();
		List<Account> accounts = accountImpl.getAllAccounts();
		String jsonString = objectMapper.writeValueAsString(accounts);
		response.getWriter().append(jsonString);
		

}
}
