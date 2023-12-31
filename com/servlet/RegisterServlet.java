package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	
	private final static String query="insert into user(name,email,mobile,dob,city,gender) values(?,?,?,?,?,?)";

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
		String name=req.getParameter("userName");
		String email=req.getParameter("email");
		String mobile=req.getParameter("mobile");
		String dob=req.getParameter("dob");
		String city=req.getParameter("city");
		String gender=req.getParameter("gender");
		
		//load JDBC Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		//Generate the connection
		try (Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/database-name","username","password");
				PreparedStatement ps=con.prepareStatement(query);){
			//set the values
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3,mobile);
			ps.setString(4, dob);
			ps.setString(5, city);
			ps.setString(6, gender);
			//execute the query
			int count=ps.executeUpdate();
			pw.println("<body style='background-color:lightYellow'>");
			pw.println("<div class='card' style='margin:auto;width:400px;margin-top:100px; border-style:solid; border-width:medium; border-color:black; border-radius:5px'>");
			if(count==1)
				pw.println("<h2 class='bg-success text-light text-center'>User Registered Successfully</h2>");
			else
				pw.println("<h2 class='bg-danger text-light text-center'>User Not Registered </h2>");
					
			
		}catch(SQLException se) {
			pw.println("<h2 class='bg-danger text-light text-center'>"+se.getMessage()+"</h2>");
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		pw.println("</div>");pw.println("&nbsp; &nbsp");
		pw.println("<a href='home.html'><button class='btn btn-outline-primary'>Home</button></a>");
		pw.println("</body");
		pw.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}
