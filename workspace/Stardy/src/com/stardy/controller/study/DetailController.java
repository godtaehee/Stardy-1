package com.stardy.controller.study;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stardy.entity.view.BoardListContent;
import com.stardy.entity.view.StudyRegisterView;
import com.stardy.service.BoardService;
import com.stardy.service.BoardServiceImpl;
import com.stardy.service.StudyService;
import com.stardy.service.StudyServiceImpl;

@WebServlet("/study/board/detail")
public class DetailController extends HttpServlet {
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

     

        int id = Integer.parseInt(request.getParameter("id"));
    	

        int memberId = (int) request.getSession().getAttribute("id");
        
        
        



        StudyService studyService = new StudyServiceImpl();

        StudyRegisterView study = null;
        boolean isMember = false;
        boolean isRegistered = false;
        int duringTime = 0;
        
        try {
			study = studyService.getStudy(id);
	        isRegistered = studyService.isLeader(memberId, id);
	        isMember = studyService.isMember(memberId, id);
	        duringTime = studyService.getDuringTime(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        ServletContext app = request.getServletContext();
        String path = "/upload";
        
        
        
        path += File.separator;
        path += study.getPath();
        path += File.separator;
        path += study.getBg();
 
        path = path.replace("\\", "/");
        
        BoardService boardService = new BoardServiceImpl();

        List<BoardListContent> board = boardService.getList(id);

        request.setAttribute("memberId", memberId);
        request.setAttribute("study", study);
        request.setAttribute("isMember", isMember);
        request.setAttribute("isRegistered", isRegistered);
        request.setAttribute("board", board);
        request.setAttribute("duringTime", duringTime);
        request.setAttribute("detailPath", path);
        
        request.getRequestDispatcher("/WEB-INF/views/study/board/detail.jsp").forward(request,response);

    }
	


}
