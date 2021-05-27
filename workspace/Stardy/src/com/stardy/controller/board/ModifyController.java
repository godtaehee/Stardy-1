package com.stardy.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stardy.entity.Board;
import com.stardy.entity.view.BoardView;
import com.stardy.service.BoardService;
import com.stardy.service.BoardServiceImpl;

@WebServlet("/study/board/modify")
public class ModifyController extends HttpServlet{

	BoardService boardService = new BoardServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String bid_ = request.getParameter("id");
		int bid = 0;
		
		if(bid_ != null && !bid_.equals(""));
			bid = Integer.parseInt(bid_);
			
		BoardView boardView = boardService.read(bid);
				
		request.setAttribute("boardView", boardView);
		request.setAttribute("files", boardService.getFiles(bid));
		
		request.getRequestDispatcher("/WEB-INF/views/study/board/modify.jsp").forward(request, response);
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
		
		response.sendRedirect("/study/board/read?id=" + id);
	}
}
