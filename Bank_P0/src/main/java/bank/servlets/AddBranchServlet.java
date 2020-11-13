package bank.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bank.dao.BranchDAOImpl;
import bank.model.Branch;

/**
 * Servlet implementation class AddBranchServlet
 */
@WebServlet("/add_branch")
public class AddBranchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String branchName = request.getParameter("branchName");
		String branchCity = request.getParameter("branchCity");
		BranchDAOImpl branchImpl = new BranchDAOImpl();
		Branch add = new Branch(branchName, branchCity);
		boolean isAdded = branchImpl.addBranch(add);
		
		if(isAdded) {
			System.out.println("ok");
			response.getWriter().append("branch has been added successflly");
		}else {
			System.out.println("wrong");
			response.getWriter().append("smth went wrong! try again.");
		}
	}

}
