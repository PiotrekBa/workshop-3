package pl.coderslab.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.Exercise;
import pl.coderslab.model.Group;
import pl.coderslab.model.Solution;
import pl.coderslab.model.User;
import pl.coderslab.utils.DbUtil;

/**
 * Servlet implementation class SolutionDetails
 */
@WebServlet("/details")
public class SolutionDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		Integer id = null;
		Integer editId = null;
		Integer delId = null;

		try {
			editId = Integer.parseInt(request.getParameter("editId"));
		} catch (NumberFormatException e) {
		}
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
		}
		try {
			delId = Integer.parseInt(request.getParameter("delId"));
		} catch (NumberFormatException e) {
		}

		if (editId != null) {
			if (editId == 0) {
				request.setAttribute("head", "Dodawanie rozwiązania");
				Connection conn = null;
				Exercise[] exercises = null;
				User[] users = null;
				User user = null;
				try {
					conn = DbUtil.getConn();
					exercises = Exercise.loadAllExercises(conn);
					users = User.loadAllUsers(conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("exercises", exercises);
				request.setAttribute("users", users);
				getServletContext().getRequestDispatcher("/WEB-INF/views/update-solution.jsp").forward(request,
						response);
			} else {
				Connection conn;
				Exercise[] exercises = null;
				User[] users = null;
				Solution solution = null;
				try {
					conn = DbUtil.getConn();
					exercises = Exercise.loadAllExercises(conn);
					users = User.loadAllUsers(conn);
					solution = Solution.loadSolutionById(conn, editId);
					request.setAttribute("head", "Edycja Rozwiązania");

				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("exercises", exercises);
				request.setAttribute("users", users);
				request.setAttribute("solution", solution);
				getServletContext().getRequestDispatcher("/WEB-INF/views/update-solution.jsp").forward(request,
						response);
			}
		} else if (delId != null) {
			try {
				Connection conn = DbUtil.getConn();
				Solution solution = Solution.loadSolutionById(conn, delId);
				int userId = solution.getUser_id();
				solution.delete(conn);
				Solution[] solutions = Solution.loadAllSolutions(conn);
				request.setAttribute("solutions", solutions);
				getServletContext().getRequestDispatcher("/WEB-INF/views/user-details?id="+userId+".jsp").forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (id != null) {
			try {
				Connection conn = DbUtil.getConn();
				Solution solution = Solution.loadSolutionById(conn, id);
				request.setAttribute("details", solution);
				getServletContext().getRequestDispatcher("/WEB-INF/views/details.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			getServletContext().getRequestDispatcher("/WEB-INF/views/all-groups.jsp").forward(request,
					response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = null;
		Integer userId = null;
		Integer exerciseId = null;
		try {
			exerciseId = Integer.parseInt(request.getParameter("exerciseId"));
			userId = Integer.parseInt(request.getParameter("userId"));
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
		}
		String description = request.getParameter("sol");
		Solution solution = null;
		if (id == null) {
			solution = new Solution();
			solution.setExercise_id(exerciseId);
			solution.setUser_id(userId);
			solution.setDescription(description);
		} else {
			Connection conn = null;
			try {
				conn = DbUtil.getConn();
				solution = Solution.loadSolutionById(conn, id);
				solution.setExercise_id(exerciseId);
				solution.setUser_id(userId);
				solution.setDescription(description);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Connection conn;
		try {
			conn = DbUtil.getConn();
			solution.saveToDB(conn);
			doGet(request, response);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
