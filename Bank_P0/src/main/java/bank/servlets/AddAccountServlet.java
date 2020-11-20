package bank.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.JWT.JwtManager;
import bank.dao.AccountDAOImpl;
import bank.dao.BranchDAOImpl;
import bank.dao.UserDAOImpl;
import bank.model.Account;
import bank.model.Branch;
import bank.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

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

		String authTokenHeader = request.getHeader("Authorization");
		if (authTokenHeader != null && !authTokenHeader.isEmpty()) {
			try {
				Jws<Claims> parsedToken = JwtManager.parseToken(authTokenHeader);
				String userRole = String.valueOf(parsedToken.getBody().get("role"));
				if (userRole.equals("admin")) {
					AccountDAOImpl accountImpl = new AccountDAOImpl();
					Account add = new Account();
					add.setUserID(Integer.parseInt(request.getParameter("userid")));
					add.setStatus(request.getParameter("status"));
					add.setBalance(Double.parseDouble(request.getParameter("balance")));

					boolean isAdded = accountImpl.addAccount(add);

					if (isAdded) {

						response.getWriter().append("Caller: " + parsedToken.getBody().get("email") + " >> "
								+ parsedToken.getBody().get("role") + "\n Account has been added successflly");
						response.setStatus(200);
					} else {

						response.getWriter().append("smth went wrong! try again.");
						response.setStatus(500);
					}

				} else {
					response.getWriter().append("Caller: " + parsedToken.getBody().get("email") + " >> " + userRole
							+ "\n This user role is not allowed to add");
					response.setStatus(401);
				}

			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().append("Invalid Token, Please login");
				response.setStatus(401);
			}

		} else {
			response.getWriter().append("No Token provided, Please login!!");
			response.setStatus(401);
		}

	}

}
