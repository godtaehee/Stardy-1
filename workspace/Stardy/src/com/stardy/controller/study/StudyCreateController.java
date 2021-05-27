package com.stardy.controller.study;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.stardy.entity.Category;
import com.stardy.service.StudyService;
import com.stardy.service.StudyServiceImpl;
import com.stardy.util.DatabaseUtil;
import com.stardy.util.UploadUtil;

@MultipartConfig(
	fileSizeThreshold = 1024*1024,
	maxFileSize = 1024*1024*50,
	maxRequestSize = 1024*1024*50*5
)
@WebServlet("/study/create")
public class StudyCreateController extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int memberId = 0;

        if(request.getSession().getAttribute("id") != null)
            memberId = (int) request.getSession().getAttribute("id");
  
        request.setAttribute("memberId", memberId);

        request.getRequestDispatcher("/WEB-INF/views/study/create.jsp").forward(request,response);

    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");

		String uuid = UUID.randomUUID().toString();

		StudyService studyService = new StudyServiceImpl();

		String title = request.getParameter("title");
		int memberId = Integer.parseInt(request.getParameter("memberId"));
		String category_str = request.getParameter("category");
		int category = Category.valueOf(category_str).ordinal();
		int limit = Integer.parseInt(request.getParameter("limit"));
		int open = (request.getParameter("open") == null) ? 0 : 1;


		// 파트 가져오기
		Part fPart = request.getPart("imgFile");

		// 내가 올린 파일 이름
		String fileName = fPart.getSubmittedFileName();
		System.out.println("빈 ㄴㅇㄹㅁㄴㄹㄴㅁ" + fileName);

		// 입력을 위한 InputStream 생성
		InputStream fis = fPart.getInputStream();

		//뭔지 모름
		ServletContext application = request.getServletContext();
		UploadUtil uploadUtil = UploadUtil.create(application);




		String realFileName = uuid + "_" + fileName;
		if(fileName.equals(""))
			realFileName = null;
		String dayPath = uploadUtil.createFilePath();
		String realPath = File.separator + dayPath;

		uploadUtil.saveFiles(fPart, uuid, realPath);

		String duedate_str = request.getParameter("duedate");
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		String intro = request.getParameter("intro");

		Date duedate = null;
		try {
			duedate = transFormat.parse(duedate_str);
		} catch (java.text.ParseException e1) {
			e1.printStackTrace();
		}

		java.sql.Date sqlDate = new java.sql.Date(duedate.getTime());


		   String sql = "INSERT INTO STUDY(TITLE, MEMBER_ID, CATEGORY_ID, LIMIT, OPEN, DUEDATE, INTRO, BG, PATH) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";



		   int flag = 0;

	        try {
				System.out.println(realPath);
				System.out.println(fileName);
	            Connection con = DatabaseUtil.getConnection();
		        PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, title);
		        pstmt.setInt(2, memberId);
		        pstmt.setInt(3, category);
		        pstmt.setInt(4, limit);
		        pstmt.setInt(5, open);
		        pstmt.setDate(6, sqlDate);
		        pstmt.setString(7, intro);
		        pstmt.setString(8, realFileName);
		        pstmt.setString(9, dayPath);
		        flag = pstmt.executeUpdate();
		        pstmt.close();
		        con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	        studyService.insertJoinedStudy(memberId,title);

	        if(flag == 1)
	        	response.sendRedirect("/study/list?success=1");

	}
}
