package com.stardy.controller.study;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/study/create")
public class StudyCreateController extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int memberId = 0;

        if(request.getSession().getAttribute("id") != null)
            memberId = (int) request.getSession().getAttribute("id");
  
        request.setAttribute("memberId", memberId);

        request.getRequestDispatcher("/WEB-INF/views/study/create.jsp").forward(request,response);

    }

}
