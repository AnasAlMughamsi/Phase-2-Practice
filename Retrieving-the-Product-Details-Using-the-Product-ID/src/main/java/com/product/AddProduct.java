package com.product;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/insert")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("");
		PrintWriter out = response.getWriter();
        out.println("<html><body>");
		try {

			InputStream in = getServletContext().getResourceAsStream("/WEB-INF/config.properties");
			Properties props = new Properties();
			props.load(in);
			
			DBConnection conn = new DBConnection(props.getProperty("url"),props.getProperty("username") , props.getProperty("password"));
			Connection connection = conn.getConnection();
		
			String name = request.getParameter("name");
			String price = request.getParameter("price");
			if(connection != null) {
				PreparedStatement stmt = connection.prepareStatement("insert into product(prodcut_name,price) values(?,?)");
				stmt.setString(1, name);
				stmt.setString(2, price);
				int x = stmt.executeUpdate();
				System.out.println("product added " + x);
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
