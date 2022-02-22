package com.product;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/product")
public class JDBC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JDBC() {
        super();
    }

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
			
			// this one show the content of product in MySQL	
			if(connection != null) {
				Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				ResultSet result = stmt.executeQuery("select * from product");
				
				while(result.next()) {
					out.println(result.getInt("ID") + " " + 
							result.getString("prodcut_name") + " " + 
							result.getBigDecimal("price") + " " + 
							result.getDate("date_added") + "<br>");
				}
				out.println("<br>");
				connection.close();
				out.println("DB Connection starting<br>");
				out.println("</body></html>");
				conn.closeConnection();
				out.println("Connection close");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
