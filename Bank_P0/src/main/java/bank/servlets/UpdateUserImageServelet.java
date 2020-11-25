package bank.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import bank.JWT.JwtManager;
import bank.dao.UserDAOImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * Servlet implementation class UpdateUserImage
 */
@MultipartConfig
@WebServlet("/update_image")
public class UpdateUserImageServelet extends HttpServlet {
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
				String email = String.valueOf(parsedToken.getBody().get("email"));
				Part image = request.getPart("image");

				try (InputStream is = image.getInputStream(); ByteArrayOutputStream os = new ByteArrayOutputStream();) {

					byte[] buffer = new byte[1024];

					while (is.read(buffer) != -1) {
						os.write(buffer);
					}

					UserDAOImpl userImpl = new UserDAOImpl();
					boolean isUploaded = userImpl.updateProfileImage(email, os.toByteArray());

					if (isUploaded) {
						response.getWriter().append("image has been uploaded successfully");
						response.setStatus(200);
					} else {
						response.getWriter().append("smth went wrong, Could not upload image!");
						response.setStatus(500);
					}

				} catch (IOException e) {
					response.getWriter().append("smth went wrong, Could not upload image!");
					response.setStatus(500);
					e.printStackTrace();
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
