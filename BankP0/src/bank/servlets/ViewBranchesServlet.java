package bank.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import bank.dao.BranchDAOImpl;
import bank.model.Branch;

@WebServlet("/get_branches")
public class ViewBranchesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBranchesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BranchDAOImpl branchImpl = new BranchDAOImpl();
		List<Branch> branches = branchImpl.getAllBranches();
		String output = "";
		for(Branch branch:branches) {
			output = output.concat("<tr><td>" + branch.getBranchName() + "</td><td>" + branch.getBranchCity() + "</td></tr>");
		}
		response.getWriter().append("<table style='border: 1px solid black;'><tr><th>Branch Name</th><th>Branch City</th></tr>" + output + "</table>");
	}


}
