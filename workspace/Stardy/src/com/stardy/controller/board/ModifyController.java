package com.stardy.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stardy.entity.Board;
import com.stardy.entity.view.BoardView;
import com.stardy.service.BoardServiceImpl;

@WebServlet("/board/modify")
public class ModifyController extends HttpServlet{

	BoardServiceImpl boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int idx = 3;
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int id = -1;
		String title = "";
		String content = "";
		
		String id_ = request.getParameter("id");
		String title_ = request.getParameter("title");
		String content_ = request.getParameter("content");
		
		if(id_ != null && !id_.equals(""))
			id = Integer.parseInt(id_);
		if(title_ != null && !title_.equals(""))
			title = title_;
		if(content_ != null && !content_.equals(""))
			content = content_;
		
		BoardView board = boardService.read(id);
		board.setTitle(title);
		board.setContent(content);
		
		boardService.modify(board);
		
		response.sendRedirect("/board/read.jsp?id=" + id);
	}
}
