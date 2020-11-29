package bank.servlets;

import java.io.IOException;
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
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/change_pass")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String authTokenHeader = request.getHeader("Authorization");
		if (authTokenHeader != null && !authTokenHeader.isEmpty()) {
			try {
				Jws<Claims> parsedToken = JwtManager.parseToken(authTokenHeader);
				String email = String.valueOf(parsedToken.getBody().get("email"));
				String currentPassword = request.getParameter("currPassword");
				String newPassword = request.getParameter("newPassword");
				if (currentPassword != null && !currentPassword.isEmpty()) {
					UserDAOImpl userImpl = new UserDAOImpl();
					User user = userImpl.getUserByEmail(email);
					if (user != null) {
						String retrivedHash = user.getPassword();
						if (BCrypt.checkpw(currentPassword, retrivedHash)) {
							// User has provided the right current password.
							// hash the new password to be replaced with the old one
							String hashedNewPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt(12));
							boolean isChanged = userImpl.updatePassword(email, hashedNewPassword);
							if (isChanged) {
								response.getWriter()
										.append("Caller: " + email + " >> " + parsedToken.getBody().get("role")
												+ "\n password has been changed successflly");
								response.setStatus(200);
							} else {
								response.getWriter().append("smth went wrong! try again.");
								response.setStatus(500);
							}

						} else {
							response.getWriter().append("Incorrect Username/Paswowrd!!");
							response.setStatus(401);
						}
					} else {
						response.getWriter().append("Incorrect Username/Paswowrd!!");
						response.setStatus(401);
					}
				} else {
					response.getWriter().append("Current password is required");
					response.setStatus(422);
				}

			} catch (Exception e) {
				response.getWriter().append("Invalid Token, Please login");
				e.printStackTrace();
				response.setStatus(401);
			}

		} else {
			response.getWriter().append("No Token provided, Please login!!");
			response.setStatus(401);
		}
	}

}
