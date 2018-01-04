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
import pl.coderslab.utils.DbUtil;
import sun.tracing.NullProviderFactory;

/**
 * Servlet implementation class PanelGroups
 */
@WebServlet("/panel-groups")
public class PanelGroups extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer editId = null;
		Integer delId = null;
		try {
			editId = Integer.parseInt(request.getParameter("editId"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		try {
			delId = Integer.parseInt(request.getParameter("delId"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		if (editId != null) {
			if (editId == 0) {
				request.setAttribute("head", "Dodawanie grupy");
			} else {
				Connection conn;
				try {
					conn = DbUtil.getConn();
					Group group = Group.loadGroupById(conn, editId);
					request.setAttribute("head", "Edycja grupy");
					request.setAttribute("group", group);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			getServletContext().getRequestDispatcher("/WEB-INF/views/update-group.jsp").forward(request, response);

		} else if (delId != null) {
			try {
				Connection conn = DbUtil.getConn();
				Group group = Group.loadGroupById(conn, delId);
				group.delete(conn);
				Group[] allGroups = Group.loadAllGroups(conn);
				request.setAttribute("allGroups", allGroups);
				getServletContext().getRequestDispatcher("/WEB-INF/views/panel-groups.jsp").forward(request, response);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		} else {

			try {
				Connection conn = DbUtil.getConn();
				Group[] allGroups = Group.loadAllGroups(conn);
				request.setAttribute("allGroups", allGroups);
				getServletContext().getRequestDispatcher("/WEB-INF/views/panel-groups.jsp").forward(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer id = null;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		String name = request.getParameter("name");
		System.out.println(id + "-"+ name);
		Group group= null;
		if (id == null) {
			id = 0;
			group = new Group(name);
		} else {
			Connection conn = null;
			try {
				conn = DbUtil.getConn();
				group = Group.loadGroupById(conn, id);
				group.setName(name);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		Connection conn;
		try {
			conn = DbUtil.getConn();
			group.saveToDB(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		System.out.println(id + "-"+ name);
		doGet(request, response);
	}

}
