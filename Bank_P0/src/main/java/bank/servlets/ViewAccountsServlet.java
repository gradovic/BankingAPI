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

import com.google.gson.Gson;

import bank.dao.AccountDAOImpl;
import bank.model.Account;
import bank.model.Branch;

/**
 * Servlet implementation class ViewAccountsServlet
 */
@WebServlet("/get_accounts")
public class ViewAccountsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson = new Gson();
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountDAOImpl accountImpl = new AccountDAOImpl();
		List<Account> accounts = accountImpl.getAllAccounts();
		List<String> output = new ArrayList<String>();
		for(Account account:accounts) {
			// convert branch objects to JSON format
			output.add(this.gson.toJson(account));
		}
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(output);
		out.flush();
		

}
}
