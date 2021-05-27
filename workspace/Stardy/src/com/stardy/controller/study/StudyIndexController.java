package com.stardy.controller.study;

import com.stardy.entity.view.StudyRegisterView;
import com.stardy.entity.view.StudyView;
import com.stardy.service.StudyService;
import com.stardy.service.StudyServiceImpl;
import com.stardy.util.DatabaseUtil;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/index2")
public class StudyIndexController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

        request.getRequestDispatcher("/WEB-INF/views/index2.jsp").forward(request,response);

    }
}
