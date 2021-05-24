package com.stardy.controller.mypage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.stardy.entity.Friend;
import com.stardy.entity.Member;
import com.stardy.service.FriendServiceImpl;
import com.stardy.service.MemberService;
import com.stardy.service.MemberServiceImpl;
import com.stardy.util.Logger;

@WebServlet("/mypage/friend-modify")
public class FriendsModifyController extends HttpServlet{

	FriendServiceImpl service = new FriendServiceImpl();
	Logger log = new Logger();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer loginId = (Integer) request.getSession().getAttribute("id");

		MemberService service = new MemberServiceImpl();

		Member member = service.get(loginId);
		
		request.setAttribute("member", member);
		
		request.getRequestDispatcher("/mypage/friend-modify.jsp").forward(request, response);			

	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
