package bank.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.JWT.JwtManager;
import bank.dao.AccountDAOImpl;
import bank.model.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * Servlet implementation class DeleteAccountServlet
 */
@WebServlet("/delete_account")
public class DeleteAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String authTokenHeader = request.getHeader("Authorization");
		if (authTokenHeader != null && !authTokenHeader.isEmpty()) {
			try {
				Jws<Claims> parsedToken = JwtManager.parseToken(authTokenHeader);
				String userRole = String.valueOf(parsedToken.getBody().get("role"));
				//Test if user has admin role to be able to add or remove
				if (userRole.equals("admin")) {
					String query = request.getQueryString();
					try {
						int accountID = Integer.parseInt(query);
						AccountDAOImpl accountImpl = new AccountDAOImpl();
						boolean isAdded = accountImpl.deleteAccount(accountID);
						if (isAdded) {

							response.getWriter().append("Caller: " + parsedToken.getBody().get("email") + " >> "
									+ parsedToken.getBody().get("role") + "\n Account has been deleted successflly");
							response.setStatus(200);
						} else {

							response.getWriter().append("smth went wrong! try again.");
							response.setStatus(500);
						}

					} catch (NumberFormatException e) {
						response.getWriter().append("accountID must be number only!! (eg url/delete_account?2)");
						response.setStatus(422);
					}

				} else {
					response.getWriter().append("Caller: " + parsedToken.getBody().get("email") + " >> " + userRole
							+ "\n This user role is not allowed to delete");
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
