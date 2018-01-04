package pl.coderslab.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.api.policy.PolicyResolver.ServerContext;

import pl.coderslab.model.Solution;
import pl.coderslab.utils.DbUtil;

/**
 * Servlet implementation class Homepage
 */
@WebServlet("/")
public class Homepage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			Connection conn  = DbUtil.getConn();
			Integer limit = Integer.parseInt(getServletContext().getInitParameter("number-solutions"));
			Solution[] solutions = Solution.loadAllSolutions(conn, limit);
			request.setAttribute("solutions", solutions);
			getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
