package bank.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import bank.JWT.JwtManager;
import bank.dao.BranchDAOImpl;
import bank.model.Branch;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.SignatureException;

@WebServlet("/get_branches")
public class ViewBranchesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L; 
	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String authTokenHeader = request.getHeader("Authorization");
		if(authTokenHeader != null && !authTokenHeader.isEmpty()) {
			try {
				Jws<Claims> parsedToken = JwtManager.parseToken(authTokenHeader);
				System.out.println(parsedToken);
				BranchDAOImpl branchImpl = new BranchDAOImpl();
				List<Branch> branches = branchImpl.getAllBranches();
				String jsonString = objectMapper.writeValueAsString(branches);
				response.getWriter().append("Caller: " + parsedToken.getBody().get("email") + " >> " + parsedToken.getBody().get("role") + "\n" + jsonString);
				response.setStatus(200);
				response.setContentType("application/json");
			}catch (Exception e){
				e.printStackTrace();
				response.getWriter().append("Invalid Token, Please login");
				response.setStatus(401);
			}
			
		}else {
			response.getWriter().append("No Token provided, Please login!!");
			response.setStatus(401);
		}
		
		
	}


}
