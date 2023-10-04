package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/showdata")
public class ShowUserServlet extends HttpServlet {
	private final static String query="select * from user";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		//link the Bootstrap
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'> </link>");
		pw.println("<marquee><h1 class='text-primary' style='margin-top:10px'>Registered Users Data</h1></marquee>");
		//load JDBC Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		//Generate the connection
		try (Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb","root","root");
				PreparedStatement ps=con.prepareStatement(query);){
			ResultSet rs=ps.executeQuery();
			pw.println("<body style='background-color:lightYellow'>");
			pw.println("<div style='margin:auto;width:1100px;margin-top:100px;'>");
			pw.println("<table class='table table-hover table-striped' style='border-style:solid; border-width:medium; border-color:black; border-radius:5px'>");
			pw.println("<tr>");
			pw.println("<th style='background-color:#808080'>ID</th>");
			pw.println("<th style='background-color:#808080'>Name</th>");
			pw.println("<th style='background-color:#808080'>Email</th>");
			pw.println("<th style='background-color:#808080'>Mobile No</th>");
			pw.println("<th style='background-color:#808080'>DOB</th>");
			pw.println("<th style='background-color:#808080'>City</th>");
			pw.println("<th style='background-color:#808080'>Gender</th>");
			pw.println("<th style='background-color:#808080'>Edit</th>");
			pw.println("<th style='background-color:#808080'>Delete</th>");
			pw.println("</tr>");
			while(rs.next()) {
				pw.println("<tr style='background-color:white'>");
				pw.println("<td>"+rs.getInt(1)+"</td>");
				pw.println("<td>"+rs.getString(2)+"</td>");
				pw.println("<td>"+rs.getString(3)+"</td>");
				pw.println("<td>"+rs.getString(4)+"</td>");
				pw.println("<td>"+rs.getString(5)+"</td>");
				pw.println("<td>"+rs.getString(6)+"</td>");
				pw.println("<td>"+rs.getString(7)+"</td>");
				pw.println("<td><a href='editurl?id="+rs.getInt(1)+"'>Edit</a></td>");
				pw.println("<td><a href='deleteurl?id="+rs.getInt(1)+"'>Delete</a></td>");
				pw.println("</tr>");
			}
			pw.println("</table>");
			
		}catch(SQLException se) {
			pw.println("<h2 class='bg-danger text-light text-center'>"+se.getMessage()+"</h2>");
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		pw.println("&nbsp; &nbsp");
		pw.println("<a href='home.html'><button class='btn btn-outline-primary'>Home</button></a>");
		pw.println("<br><br>");
		pw.println("</div>");
		pw.println("</body>");
		pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}
