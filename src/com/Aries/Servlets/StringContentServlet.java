package com.Aries.Servlets;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/StringContentServlet.do")
public class StringContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("This is doGet");
		
		String userName = request.getParameter("UserName");
		String password = request.getParameter("PassWord");
		
		System.err.println(userName+":"+password);
		
		Writer out = response.getWriter();
		
		if (userName!=null||password!=null) {
			out.write("true");
		}
		else {
			
			out.write("false");
		}
	}

}
