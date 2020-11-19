package bank.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.JWT.JwtManager;
import bank.dao.BranchDAOImpl;
import bank.model.Branch;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

/**
 * Servlet implementation class AddBranchServlet
 */
@WebServlet("/add_branch")
public class AddBranchServlet extends HttpServlet {
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
				String branchName = request.getParameter("branchName");
				String branchCity = request.getParameter("branchCity");
				BranchDAOImpl branchImpl = new BranchDAOImpl();
				Branch add = new Branch(branchName, branchCity);
				boolean isAdded = branchImpl.addBranch(add);

				if (isAdded) {
					
					response.getWriter().append("branch has been added successflly");
					response.setStatus(200);
				} else {
					System.out.println("wrong");
					response.getWriter().append("smth went wrong! try again.");
					response.setStatus(500);
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
