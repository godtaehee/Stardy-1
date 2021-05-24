package com.stardy.controller.root;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stardy.entity.Member;
import com.stardy.service.MemberServiceImpl;

@WebServlet("/signup")
public class SignupController extends HttpServlet{

	   static MemberServiceImpl service = new MemberServiceImpl();
	   
	   @Override
	   protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
	      
	      String nickname = request.getParameter("nick");
	      String email = request.getParameter("email");
	      String password = request.getParameter("password");
	      
	      boolean result = service.insertMember(Member.builder().email(email).nickname(nickname).password(password).build());

	      if(result)
	    	  response.sendRedirect("/login");
	      else
	    	  response.sendRedirect("/signup");
	   }
	   
	   @Override
	   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      
		   request.getRequestDispatcher("/signup.jsp").forward(request, response);
	   }

}
