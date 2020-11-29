package bank.servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.JWT.JwtManager;
import bank.dao.UserDAOImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * Servlet implementation class GetProfileImage
 */
@WebServlet("/profile_image")
public class GetProfileImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String authTokenHeader = request.getHeader("Authorization");
		if (authTokenHeader != null && !authTokenHeader.isEmpty()) {
			try {
				Jws<Claims> parsedToken = JwtManager.parseToken(authTokenHeader);
				String email = String.valueOf(parsedToken.getBody().get("email"));
				UserDAOImpl userImpl = new UserDAOImpl();
				byte[] image = userImpl.getProfileImage(email);
				InputStream is = new ByteArrayInputStream(image);
				OutputStream os = response.getOutputStream();
				byte[] buffer = new byte[1024];
				while (is.read(buffer) != -1) {
					os.write(buffer);
				}
				os.flush();
				os.close();
				is.close();
			}catch (Exception e) {
					response.getWriter().append("Invalid Token, Please login");
					e.printStackTrace();
					response.setStatus(401);
				}
		}else {
				response.getWriter().append("No Token provided, Please login!!");
				response.setStatus(401);
			}
		//////////////////
		
		

	}

}
