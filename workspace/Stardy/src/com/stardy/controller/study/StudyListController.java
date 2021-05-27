package com.stardy.controller.study;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stardy.entity.view.StudyView;

import com.stardy.service.StudyService;
import com.stardy.service.StudyServiceImpl;

@WebServlet("/study/list")
public class StudyListController extends HttpServlet {


   @Override
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {      
      
      //카테고리 
      String category_=request.getParameter("c");
      //검색
      String query_=request.getParameter("q");
      //페이지
      String page_=request.getParameter("p");
      
      String category ="";
      if(category_ !=null)
         category = category_;
      
      String query ="";
      if(query_ !=null)
         query = query_;
      
      int page = 1;
      if(page_ !=null && !page_.equals(""))
         page =Integer.parseInt(page_);
      
      StudyService service = new StudyServiceImpl();
      
      //검색,카테고리 없는 첫번째 페이지
      
      List<StudyView>list = service.getList(category,query,page);
      
      //페이징할 숫자를 가져옴
      int total=service.paging(category,query);

      request.setAttribute("total", total);
      request.setAttribute("list", list);
      
      request
      .getRequestDispatcher("/WEB-INF/views/study/list.jsp")
      .forward(request, response);
      
      

   }

}