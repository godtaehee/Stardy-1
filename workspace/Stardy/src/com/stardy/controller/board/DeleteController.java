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
import com.stardy.service.BookmarkServiceImpl;
import com.stardy.service.LikeServiceImpl;
import com.stardy.service.ReplyServiceImpl;

@WebServlet("/board/delete")
public class DeleteController extends HttpServlet{

	BoardServiceImpl boardService = new BoardServiceImpl();
	//ReplyServiceImpl replyService = new ReplyServiceImpl();
	//BookmarkServiceImpl bookmarkService = new BookmarkServiceImpl();
	//LikeServiceImpl likeService = new LikeServiceImpl();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = -1;
		String id_ = request.getParameter("id");
		
		System.out.println(id_);
		
		if(id_ != null && !id_.equals(""))
			id = Integer.parseInt(id_);
		
		BoardView board = boardService.read(id);
		
		if(id != -1) {
			/* 좋아요 삭제 */
			//likeService.removeAll(id);
			/* 즐겨찾기 삭제 */
			//bookmarkService.removeAll(id);
			/* 댓글 삭제 */
			//replyService.removeAll(id);
			/* 게시글 삭제 */
			boardService.delete(id);
		}
		
		response.sendRedirect("/study/list.jsp?sid=" + board.getStudyId());
	}
}
