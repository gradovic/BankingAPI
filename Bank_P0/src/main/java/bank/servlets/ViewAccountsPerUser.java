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

import bank.JWT.JwtManager;
import bank.dao.AccountDAOImpl;
import bank.model.Account;
import bank.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * Servlet implementation class AccountsPerUser
 */
@WebServlet("/user_accounts")
public class ViewAccountsPerUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String authTokenHeader = request.getHeader("Authorization");
		if (authTokenHeader != null && !authTokenHeader.isEmpty()) {
			try {
				Jws<Claims> parsedToken = JwtManager.parseToken(authTokenHeader);
				String query = request.getQueryString();
				try {
					int userID = Integer.parseInt(query);
					AccountDAOImpl accountImpl = new AccountDAOImpl();
					List<Account> accounts = accountImpl.getAccountsPerUser(userID);
					String jsonString = objectMapper.writeValueAsString(accounts);
					response.getWriter().append("Caller: " + parsedToken.getBody().get("email") + "\n" + jsonString);
					response.setStatus(200);
					response.setContentType("application/json");

				} catch (NumberFormatException e) {
					response.getWriter().append("userID must be number only!! (eg url/user_accounts?2)");
					response.setStatus(422);
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

		///

	}

}
