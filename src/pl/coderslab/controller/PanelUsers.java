package pl.coderslab.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pl.coderslab.model.Group;
import pl.coderslab.model.User;
import pl.coderslab.utils.DbUtil;

/**
 * Servlet implementation class PanelUsers
 */
@WebServlet("/panel-users")
public class PanelUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer editId = null;
		Integer delId = null;
		try {
			editId = Integer.parseInt(request.getParameter("editId"));
		} catch (NumberFormatException e) {
		}
		
		try {
			delId = Integer.parseInt(request.getParameter("delId"));
		} catch (NumberFormatException e) {
		}
		
		if (editId != null) {
			if (editId == 0) {
				request.setAttribute("head", "Dodawanie użytkownika");
				Connection conn = null;
				Group[] groups = null;
				try {
					conn = DbUtil.getConn();
					groups = Group.loadAllGroups(conn);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				request.setAttribute("groups", groups);
			} else {
				Connection conn;
				try {
					conn = DbUtil.getConn();
					Group[] groups = Group.loadAllGroups(conn);
					request.setAttribute("groups", groups);
					User user = User.loadUserById(conn, editId);
					request.setAttribute("head", "Edycja Użytkownika");
					request.setAttribute("user", user);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			getServletContext().getRequestDispatcher("/WEB-INF/views/update-user.jsp").forward(request, response);

		} else if (delId != null) {
			try {
				Connection conn = DbUtil.getConn();
				User user = User.loadUserById(conn, delId);
				user.delete(conn);
				User[] users = User.loadAllUsers(conn);
				request.setAttribute("users", users);
				getServletContext().getRequestDispatcher("/WEB-INF/views/panel-users.jsp").forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else {
			try {
				Connection conn = DbUtil.getConn();
				User[] users = User.loadAllUsers(conn);
				request.setAttribute("users", users);
				getServletContext().getRequestDispatcher("/WEB-INF/views/panel-users.jsp").forward(request, response);
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
		Integer groupId = null;
		try {
			groupId = Integer.parseInt(request.getParameter("groupId"));
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
		}
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		User user = null;
		if (id == null) {
			user = new User(username,email,password);
			user.setUser_group_id(groupId);
		} else {
			Connection conn = null;
			try {
				conn = DbUtil.getConn();
				user = User.loadUserById(conn, id);
				user.setUsername(username);
				user.setEmail(email);
				user.setUser_group_id(groupId);
				if (!password.equals("")) {
					user.setPassword(password);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Connection conn;
		try {
			conn = DbUtil.getConn();
			user.saveToDB(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		doGet(request, response);
	}

}
