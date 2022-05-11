package com.prateek.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateUser
 */
@WebServlet("/updateServlet")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private Connection con;
	 public void init() {
	 	try {
	 		Class.forName("com.mysql.cj.jdbc.Driver");
	 	 con=DriverManager.getConnection("jdbc:mysql://localhost/mydb","root","Activa8985");
	 	} catch (SQLException e) {
	 		e.printStackTrace();
	 	} catch (ClassNotFoundException e) {
	 		e.printStackTrace();
	 	}
	 }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		try {
			Statement stmt=con.createStatement();
			int res=stmt.executeUpdate("update user set password='"+password+"'where email='"+email+"'");
			PrintWriter out=response.getWriter();
			if(res>0) {
				out.print("<H1>Password updated</H1>");
			}else {
				out.print("<H1>Error updating password</H1>");
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
