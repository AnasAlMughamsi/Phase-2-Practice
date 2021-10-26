package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginSerlvet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		HttpSession session = request.getSession();
		session.setAttribute("email", email);
		session.setAttribute("password", password);
		
		if(email.equals("Anas95_1@gmail.sa") && password.equals("112233")) {
			response.sendRedirect("Dashboard");

		} else {
			response.getWriter().write("Wrong email or password, please try again");
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
