package com.stardy.controller.bookmark;

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
import com.stardy.entity.Board;
import com.stardy.entity.view.SubView;
import com.stardy.service.BookmarkServiceImpl;

@WebServlet("/sub/*")
public class SubController extends HttpServlet{

	BookmarkServiceImpl service = new BookmarkServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String[] paths = request.getPathInfo().split("/");
		for(String path : paths)
			System.out.println(path);
		
		int memberId = (int) request.getSession().getAttribute("id");
		int page = Integer.parseInt(paths[1]);
				
		List<SubView> list = service.getSubs(memberId, page);
		
		Gson gson = new GsonBuilder()
				   .setDateFormat("yyyy-MM-dd").create();
		
		String json = gson.toJson(list);
		
		out.println(json);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pathInfo = request.getPathInfo(); /* /sub/{id} */
		String[] vals = pathInfo.split("/");
		
		int memberId = (int) request.getSession().getAttribute("id");
		int boardId = Integer.parseInt(vals[1]);
		
		service.add(memberId, boardId);
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pathInfo = request.getPathInfo(); /* /sub/{id} */
		String[] vals = pathInfo.split("/");
		
		int memberId = (int) request.getSession().getAttribute("id");
		int boardId = Integer.parseInt(vals[1]);
		
		service.remove(memberId, boardId);
	}
	
}
