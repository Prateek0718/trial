package com.prateek.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet(urlPatterns="/addServlet")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private Connection con;
       public void init(ServletConfig config) {
			try {
				ServletContext context=config.getServletContext();
				Class.forName("com.mysql.cj.jdbc.Driver");
			 con=DriverManager.getConnection(context.getInitParameter("dbUrl"),context.getInitParameter("dbUser"),context.getInitParameter("dbPassword"));
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	 }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		try {
			Statement stmt=con.createStatement();
			int res=stmt.executeUpdate("insert into user values('"+firstName+"','"+lastName+"','"+email+"','"+password+"')");
			PrintWriter out=response.getWriter();
			if(res>0) {
				out.print("<H1>USER CREATED</H1>");
			}else {
				out.print("<H1>Error creating user</H1>");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void destroy() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
