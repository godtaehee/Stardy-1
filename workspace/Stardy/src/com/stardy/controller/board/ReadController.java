package com.stardy.controller.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stardy.entity.Like;
import com.stardy.entity.view.BoardView;
import com.stardy.service.BoardService;
import com.stardy.service.BoardServiceImpl;
import com.stardy.service.BookmarkService;
import com.stardy.service.BookmarkServiceImpl;
import com.stardy.service.LikeService;
import com.stardy.service.LikeServiceImpl;

@WebServlet("/study/board/read")
public class ReadController extends HttpServlet{

	BoardService boardService = new BoardServiceImpl();
	BookmarkService bookmarkService = new BookmarkServiceImpl();
	LikeService likeService = new LikeServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id_ = request.getParameter("id");
		int id = 0;
		
		if(id_ != null && !id_.equals(""));
			id = Integer.parseInt(id_);
			
		BoardView boardView = boardService.read(id);
		int studyId = boardView.getStudyId();
		
		int next = boardService.getNext(id, studyId);
		int prev = boardService.getPrev(id, studyId);
		Integer loginId = (Integer) request.getSession().getAttribute("id");
		
		boolean isSub = bookmarkService.isSub(loginId, id);
		boolean isLike = likeService.isLike(new Like(loginId, id));
		
		request.setAttribute("boardView", boardView);
		request.setAttribute("next", next);
		request.setAttribute("prev", prev);
		request.setAttribute("isSub", isSub);
		request.setAttribute("isLike", isLike);
		
		request.getRequestDispatcher("/study/board/read.jsp").forward(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}