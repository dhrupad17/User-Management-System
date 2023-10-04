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

@WebServlet("/editurl")
public class EditScreenServlet extends HttpServlet{
	private final static String query="select name,email,mobile,dob,city,gender from user where id=?";

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
		
		int id=Integer.parseInt(req.getParameter("id"));
		
		
		//load JDBC Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		//Generate the connection
		try (Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/database-name","username","password");
				PreparedStatement ps=con.prepareStatement(query);){
			//set value
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			rs.next();
			pw.println("<body style='background-color:lightYellow'>");
			pw.println("<div style='margin:auto;width:600px;margin-top:100px;border-style:solid; border-width:medium; border-color:black; border-radius:5px'>");
			pw.println("<form action='edit?id="+id+"' method='post'>");
			pw.println("<table class='table table-hover table-striped'>");
			pw.println("<tr style='background-color:#D3D3D3'>");
			pw.println("<td>Name</td>");
			pw.println("<td><input type='text' name='name' value='"+rs.getString(1)+" '</td>");
			pw.println("</tr>");
			pw.println("<tr style='background-color:#ffffff'>");
			pw.println("<td>Email</td>");
			pw.println("<td><input type='email' name='email' value='"+rs.getString(2)+" '</td>");
			pw.println("</tr>");
			pw.println("<tr style='background-color:#D3D3D3'>");
			pw.println("<td>mobile</td>");
			pw.println("<td><input type='text' name='mobile' value='"+rs.getString(3)+" '</td>");
			pw.println("</tr>");
			pw.println("<tr style='background-color:#ffffff'>");
			pw.println("<td>dob</td>");
			pw.println("<td><input type='date' name='dob' value='"+rs.getString(4)+" '</td>");
			pw.println("</tr>");
			pw.println("<tr style='background-color:#D3D3D3'>");
			pw.println("<td>city</td>");
//			pw.println("<td><select name='city'> <option value='pune'>Pune</option> <option value='Mumbai'>Mumbai</option> <option value='Delhi'>Delhi</option> <option value='Bangalore'>Bangalore</option> <option value='Kochi'>Kochi</option> <option value='kolkata'>kolkata</option> <option value='Hyderabad'>Hyderabad</option> <option value='Chennai'>Chennai</option> <option value='Lucknow'>Lucknow</option> <option value='Nagpur'>Nagpur</option> <option value='Jaipur'>Jaipur</option> </select></td>");
			pw.println("<td><input type='text' name='city' value='"+rs.getString(5)+" ' </td>");
			pw.println("</tr>");
			pw.println("<tr style='background-color:#ffffff'>");
			pw.println("<td>Gender</td>");
			pw.println("<td><input type='text' name='gender' value='"+rs.getString(6)+" '</td>");
			pw.println("</tr>");
			pw.println("</table>");
			pw.println("<tr>");
			pw.println("<td>&nbsp;&nbsp;<button type='submit' class= 'btn btn-outline-success'>Edit</button></td>");
			pw.println("<td>&nbsp;&nbsp;<button type='reset' class= 'btn btn-outline-danger'>Cancel</button></td>");
			pw.println("</tr>");
			pw.println("</form");
			
		}catch(SQLException se) {
			pw.println("<h2 class='bg-danger text-light text-center'>"+se.getMessage()+"</h2>");
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		pw.println("</div>");
		pw.println("&nbsp;&nbsp");
		pw.println("<a href='home.html'><button class='btn btn-outline-primary'>Home</button></a>");
		pw.println("<br><br>");
		pw.println("</body>");
		pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}
