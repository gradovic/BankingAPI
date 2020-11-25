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

import bank.dao.UserDAOImpl;

/**
 * Servlet implementation class GetProfileImage
 */
@WebServlet("/profile_image")
public class GetProfileImage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String query = request.getQueryString();
		try {
			int userID = Integer.parseInt(query);
			UserDAOImpl userImpl = new UserDAOImpl();
			byte[] image = userImpl.getProfileImage(userID);
			InputStream is = new ByteArrayInputStream(image);
			OutputStream os = response.getOutputStream();
			byte[] buffer = new byte[1024];
			while (is.read(buffer) != -1) {
				os.write(buffer);
			}
			os.flush();
			os.close();
			is.close();
			
		} catch (NumberFormatException e) {
			response.getWriter().append("userID must be number only!! (eg url/profile_image?2)");
			response.setStatus(422);
		}

	}

}
