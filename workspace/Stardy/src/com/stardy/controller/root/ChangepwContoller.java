package com.stardy.controller.root;


import java.io.IOException;
import java.sql.SQLException;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.stardy.entity.Member;
import com.stardy.service.MemberService;
import com.stardy.service.MemberServiceImpl;
import com.stardy.util.Logger;

@WebServlet("/changePw")
public class ChangepwContoller extends HttpServlet {
	
	static MemberService service = new MemberServiceImpl();
	static Logger log = new Logger();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		String msg = request.getParameter("msg");
		
		if(msg != null)
			request.setAttribute("msg", msg);
		
		request.getRequestDispatcher("/WEB-INF/views/changePw.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String newPw = (String)request.getParameter("newPw");
		String checkPw = (String)request.getParameter("checkPw");
		String email = (String)session.getAttribute("userMail");
	
		int result = 0;

		
		
		System.out.println("email : " + email);
		
		//result = service.changPwByEmail(newPw, email);
		
		
		
		if(Integer.parseInt(newPw)!= Integer.parseInt(checkPw)) {
			System.out.println("비밀번호 틀림");
		}
		else {
			result = service.changPwByEmail(newPw, email);
			System.out.println("비밀번호 변경 완료");
			response.sendRedirect("/login");
			
		}

		

	}
	
}
