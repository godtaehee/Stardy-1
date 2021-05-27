package com.stardy.controller.bookmark;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.ProcessHandle.Info;
import java.math.BigInteger;
import java.time.LocalDate;
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
import com.stardy.util.Logger;

@WebServlet("/sub/*")
public class SubController extends HttpServlet{

	BookmarkServiceImpl service = new BookmarkServiceImpl();
	Logger log = new Logger();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		int memberId = (int) request.getSession().getAttribute("id");
		
		String[] paths = request.getPathInfo().split("/");
		for(String path : paths)
			log.info(path);
		
		//즐겨찾기 게시글의 개수를 반환 /sub/cnt
		if(paths[1].equals("total")) {
			
			int total = service.getTotal(memberId);
			String json = "{\"total\":" + total + "}";
			out.print(json);
			
		}
		//즐겨찾기 게시글 목록을 반환 /sub/${page}
		else {
			
			int page = Integer.parseInt(paths[1]);
					
			List<SubView> list = service.getSubs(memberId, page);
			
			Gson gson = new GsonBuilder()
					   .setDateFormat("yyyy-MM-dd").create();
			
			String json = gson.toJson(list);
			
			out.println(json);
		}
>>>>>>> refs/remotes/real/master
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
