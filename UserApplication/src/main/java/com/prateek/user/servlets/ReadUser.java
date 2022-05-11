package com.prateek.user.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateUser
 */

public class ReadUser extends HttpServlet {
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Statement stmt=con.createStatement();
			ResultSet res=stmt.executeQuery("select * from user");
			PrintWriter out=response.getWriter();
			out.print("<tables>");
			out.print("<tr>");
			out.print("<th>");
			out.println("First Name");
			out.print("</th>");
			out.print("<th>");
			out.println("Last Name");
			out.print("</th>");
			out.print("<th>");
			out.println("Email");
			out.print("</th>");
			out.print("</tr>");
			while(res.next()) {
				out.println("<tr>");
				
				out.println("<td>");
				out.print(res.getString(1));
				out.println("</td>");
				out.println("<td>");
				out.print(res.getString(2));
				out.println("</td>");
				out.println("<td>");
				out.print(res.getString(3));
				out.println("</td>");
				out.println("</tr>");
			}
			out.print("</tables>");
		
			
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
