package com.stardy.controller.friend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stardy.entity.Friend;
import com.stardy.entity.view.FollowerView;
import com.stardy.entity.view.FollowingView;
import com.stardy.service.FriendServiceImpl;
import com.stardy.util.Logger;

@WebServlet("/friends/*")
public class FriendRESTController extends HttpServlet{

	Logger log = new Logger();
	FriendServiceImpl service = new FriendServiceImpl();
	
	// friend/fr/{originId}
	// friend/fo/{originId}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String[] paths = request.getPathInfo().split("/");
		int originId = Integer.parseInt(paths[2]);
		if(paths[1].equals("fr")) {
			List<FollowingView> list = service.getFriends(originId);
			
			Gson gson = new GsonBuilder()
					   .setDateFormat("yyyy-MM-dd").create();
			String jsonList = gson.toJson(list);
			out.print(jsonList);
		}
		else {
			List<FollowerView> list = service.getFollowers(originId);
			
			Gson gson = new Gson();
			String jsonList = gson.toJson(list);
			out.print(jsonList);
		}
	}
	
	// friend/{targetId}
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/plainText; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String[] paths = request.getPathInfo().split("/");
		
		if(paths[1] == null || paths[1].equals("")) return;
		
		int targetId = Integer.parseInt(paths[1]);
		int originId = (int) request.getSession().getAttribute("id");
		
		log.info("친구 삭제 요청 : " + originId + "님 -> " + targetId + "님");
		
		int result = service.unfollow(originId, targetId);
		
		if(result == 1)
			out.print("success");
		else
			out.print("fail");
	}
	
	
	// friend/{targetId}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/plainText; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String[] paths = request.getPathInfo().split("/");
		
		int originId = (int) request.getSession().getAttribute("id");
		int targetId = Integer.parseInt(paths[1]);
		
		log.info("친구 등록 요청. 신청자 : " + originId +", 대상자 : " + targetId);
		
		int result = service.follow(originId, targetId);
		
		if(result == 1)
			out.print("success");
		else
			out.print("fail");
	}
}
