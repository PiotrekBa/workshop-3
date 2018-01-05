package pl.coderslab.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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
 * Servlet implementation class PanelExercises
 */
@WebServlet("/panel-exercises")
public class PanelExercises extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer editId = null;
		Integer delId = null;
		Integer showId = null;
		try {
			editId = Integer.parseInt(request.getParameter("editId"));
		} catch (NumberFormatException e) {
		}
		
		try {
			delId = Integer.parseInt(request.getParameter("delId"));
		} catch (NumberFormatException e) {
		}
		
		try {
			showId = Integer.parseInt(request.getParameter("showId"));
		} catch (NumberFormatException e) {
		}
		
		if (editId != null) {
			if (editId == 0) {
				request.setAttribute("head", "Dodawanie zadania");
			} else {
				Connection conn;
				try {
					conn = DbUtil.getConn();
					Exercise exercise = Exercise.loadExerciseById(conn, editId);
					request.setAttribute("exercise", exercise);
					request.setAttribute("head", "Edycja Zadania");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			getServletContext().getRequestDispatcher("/WEB-INF/views/update-exercise.jsp").forward(request, response);

		} else if (showId != null) {
			try {
				Connection conn = DbUtil.getConn();
				Solution[] solutions = Solution.loadAllByExerciseId(showId, conn);
				Exercise exercise = Exercise.loadExerciseById(conn, showId);
				request.setAttribute("exercise", exercise);
				request.setAttribute("solutions", solutions);
				getServletContext().getRequestDispatcher("/WEB-INF/views/exercise-details.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (delId != null) {
			try {
				Connection conn = DbUtil.getConn();
				Exercise exercise = Exercise.loadExerciseById(conn, delId);
				Solution[] solutions = Solution.loadAllByExerciseId(delId, conn);
				for (Solution solution : solutions) {
					solution.delete(conn);
				}
				exercise.delete(conn);
				Exercise[] exercises = Exercise.loadAllExercises(conn);
				request.setAttribute("exercises", exercises);
				getServletContext().getRequestDispatcher("/WEB-INF/views/panel-exercises.jsp").forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else {
			try {
				Connection conn = DbUtil.getConn();
				Exercise[] exercises = Exercise.loadAllExercises(conn);
				request.setAttribute("exercises", exercises);
				getServletContext().getRequestDispatcher("/WEB-INF/views/panel-exercises.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Integer id = null;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		Exercise exercise= null;
		if (id == null) {
			id = 0;
			exercise = new Exercise(title, description);
		} else {
			Connection conn = null;
			try {
				conn = DbUtil.getConn();
				exercise = Exercise.loadExerciseById(conn, id);
				exercise.setTitle(title);
				exercise.setDescription(description);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Connection conn;
		try {
			conn = DbUtil.getConn();
			exercise.saveToDB(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		doGet(request, response);
	}

}
