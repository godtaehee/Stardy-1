package com.stardy.controller.root;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stardy.entity.Member;
import com.stardy.service.MemberServiceImpl;
import com.stardy.util.Logger;

@WebServlet("/login")
public class LoginController extends HttpServlet{
	
	MemberServiceImpl service = new MemberServiceImpl();
	Logger log = new Logger();

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String msg = request.getParameter("msg");
		
		if(msg != null)
			request.setAttribute("msg", msg);
		
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//http://apis.data.go.kr/B490007/qualExamSchd/getQualExamSchdList?dataFormat=xml&serviceKey=Dd0KvyVNEovbfedeANo%2FSUGBakb8v66k8qm8YZDl9Q2rg3HGjl7n7wjAkeIUVdA2N0ax84WGHiyfYY7LfkIKqQ%3D%3D&implYy=2020&numOfRows=10&pageNo=1
		//test
		/*
		 * String folder = "/upload/";
		 * 
		 * File file = new File(folder, "2020/05/18");
		 * 
		 * if(!file.exists()) { file.mkdirs(); file.createNewFile(); }
		 * System.out.println(file.getAbsolutePath().toString());
		 */
		//test
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Member result = service.login(email, password);
		
		if(result != null) {
			log.info("로그인 성공");
			
			request.getSession().setAttribute("nickname", result.getNickname());
			request.getSession().setAttribute("id", result.getId());
			
			request.setAttribute("msg", "success");
			response.sendRedirect("/index2");
		}
		else {
			request.setAttribute("msg", "fail");
			response.sendRedirect("/login");
			
		}
	}
}