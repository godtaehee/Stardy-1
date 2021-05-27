//package com.stardy.controller.study;
//
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.stardy.entity.view.StudyView;
//import com.stardy.util.DatabaseUtil;
//
//@WebServlet("/study/list")
//public class StudyListController extends HttpServlet{
//	
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		
//		request
//		.getRequestDispatcher("/WEB-INF/views/study/board/list.jsp")
//		.forward(request, response);
//		
//	}
//
//}
