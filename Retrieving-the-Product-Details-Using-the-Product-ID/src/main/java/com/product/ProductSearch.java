package com.product;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/search")
public class ProductSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ProductSearch() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean found = false;

		PrintWriter out = response.getWriter();	
		ProductDetails pd = new ProductDetails();
		pd.setProduct_id(Integer.parseInt(request.getParameter("product_id")));
		pd.setProduct_name(request.getParameter("name"));
		
		response.setContentType("text/html");
		out.println("<html><body>");
		
		try {
			
			InputStream in = getServletContext().getResourceAsStream("/WEB-INF/config.properties");
			Properties props = new Properties();
			props.load(in);
			DBConnection conn = new DBConnection(props.getProperty("url"),props.getProperty("username") , props.getProperty("password"));
			Connection connection = conn.getConnection();
			String sqlStatment = "select * from product where id=?";

			if(connection != null) {
				PreparedStatement ps = connection.prepareStatement(sqlStatment);
				
				ps.setInt(1,pd.getProduct_id());
				ResultSet result = ps.executeQuery();
				RequestDispatcher rd = request.getRequestDispatcher("index.html");
	    		rd.include(request, response);
	             
	    		while(result.next()) {
	       			 found = true;
	       			 if(found) {
						out.print("Product Found!" + "<br/>");
		       			out.println("Product id: " + result.getInt(1)+ "<br/>" +"Product name: " + result.getString(2) + "<br/>" + "Product price ($): "+ result.getDouble(3));   
		    		} 	 
	       		 }
	    		
	    		if(!found) {
	    			out.println("Product not found");
	    		}
	    
	       		out.println("</html></body>");  
				connection.close();
			} 
			
		} catch (Exception e) {
				e.printStackTrace();
			}
		}
}
