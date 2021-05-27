package com.stardy.controller.root;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stardy.entity.view.StudyRegisterView;
import com.stardy.service.StudyService;
import com.stardy.service.StudyServiceImpl;

@WebServlet("/index")
public class IndexController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		
		int memberId = 0;

        if(request.getSession().getAttribute("id") != null)
            memberId = (int) request.getSession().getAttribute("id");
    

        StudyService studyService = new StudyServiceImpl();

        List<StudyRegisterView> myStudy = null;
        List<StudyRegisterView> notInStudy = null;
        boolean haveStudy = false;

        if(memberId != 0){
            myStudy = studyService.getMyStudyList(memberId);
            notInStudy = studyService.getNotMyStudyList(memberId);
            haveStudy = (myStudy.size() == 0) ? false : true;
        }

        if(memberId == 0) {
            haveStudy = false;
            myStudy = new ArrayList<>();
            notInStudy = new ArrayList<>();
        }
        


        request.setAttribute("memberId", memberId);
        request.setAttribute("myStudy", myStudy);
        request.setAttribute("notInStudy", notInStudy);
        request.setAttribute("haveStudy", haveStudy);

        request.getRequestDispatcher("/WEB-INF/views/index2.jsp").forward(request, resp);
		
	}
}
