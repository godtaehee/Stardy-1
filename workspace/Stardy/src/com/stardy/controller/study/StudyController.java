package com.stardy.controller.study;

import com.stardy.entity.Category;
import com.stardy.entity.Member;
import com.stardy.entity.Study;
import com.stardy.entity.view.StudyList;
import com.stardy.util.DatabaseUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/study/target")
public class StudyController extends HttpServlet {

    public int getStudyId(int memberId, String title) {
    	
    	   String sql = "SELECT ID FROM STUDY WHERE MEMBER_ID=" + memberId +" AND TITLE='"+title+"'";


    	   int id = 0;
           

		   
	        try {
	            Connection con = DatabaseUtil.getConnection();
		        PreparedStatement pstmt = con.prepareStatement(sql);
		        ResultSet rs = pstmt.executeQuery();
		        
		        if(rs.next()) {
		        	id = rs.getInt("ID");
		        }
		        
		        pstmt.close();
		        con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


    	
    	return id;
    }

    public void insertJoinedStudy(int memberId, String title) {
    	
    		int studyId =  getStudyId(memberId, title);
    		System.out.println("스터디 아이디 " + studyId);
    	
    	   String sql = "INSERT INTO JOINED_STUDY(STUDY_ID, MEMBER_ID) VALUES (?, ?)";


    		
		   int flag = 0;
		   
	        try {
	            Connection con = DatabaseUtil.getConnection();
		        PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, studyId);
		        pstmt.setInt(2, memberId);
		        flag = pstmt.executeUpdate();
		        pstmt.close();
		        con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


    	
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// TODO Auto-generated method stub"
		
		request.setCharacterEncoding("utf-8");
		
		System.out.println(request.getParameter("open"));
		
		String title = request.getParameter("title");
		int memberId = Integer.parseInt(request.getParameter("memberId"));
		String category_str = request.getParameter("category");
		int category = Category.valueOf(category_str).ordinal();
		int limit = Integer.parseInt(request.getParameter("limit")); 
		int open = 0;
		if(request.getParameter("open") == null)
			open = 0;
		else
			open = 1;
//		int open = Integer.parseInt(request.getParameter("open"));
		
		
	
		Date date_now = new Date(System.currentTimeMillis());
		
		SimpleDateFormat format_now =  new SimpleDateFormat("yyyy/MM/ddHHmmss");

		

		
		String format_str = format_now.format(date_now);
		

		
		String path = "../upload/" + format_str.substring(0,4) + "/" + format_str.substring(4,6) + "/" + format_str.substring(6,8) + "/" + format_str.substring(8,10) + "/" + format_str.substring(10,12)+"/" + format_str.substring(12); 
	
		File folder = new File(".");
		
		
		if(folder.mkdirs()) {
			try {
				
				System.out.println("폴더 생성완료");
			}
			catch(Exception e) {
				e.getStackTrace();
			}
		}else {
			System.out.println("이미 폴더가 있습니다.");
		}
		
//		Desktop.getDesktop().open(folder);
		
		
		String duedate_str = request.getParameter("duedate");
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		String intro = request.getParameter("intro");
		
		Date duedate = null;
		try {
			duedate = transFormat.parse(duedate_str);
		} catch (java.text.ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		java.sql.Date sqlDate = new java.sql.Date(duedate.getTime());
		
		
		   String sql = "INSERT INTO STUDY(TITLE, MEMBER_ID, CATEGORY_ID, LIMIT, OPEN, DUEDATE, INTRO) VALUES (?, ?, ?, ?, ?, ?, ?)";


	
		   int flag = 0;
		   
	        try {
	            Connection con = DatabaseUtil.getConnection();
		        PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, title);
		        pstmt.setInt(2, memberId);
		        pstmt.setInt(3, category);
		        pstmt.setInt(4, limit);
		        pstmt.setInt(5, open);
		        pstmt.setDate(6, sqlDate);
		        pstmt.setString(7, intro);
		        flag = pstmt.executeUpdate();
		        pstmt.close();
		        con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	        insertJoinedStudy(memberId,title);

	        
	        
	        if(flag == 1)
	            response.sendRedirect("/study/list.jsp?success=1");
	}
	
	boolean isMember(int memberId, int studyId) {
		
 	   String sql = "SELECT * FROM JOINED_STUDYID ";


 	   int id = 0;
        

		   
	        try {
	            Connection con = DatabaseUtil.getConnection();
		        PreparedStatement pstmt = con.prepareStatement(sql);
		        ResultSet rs = pstmt.executeQuery();
		        
		        if(rs.next()) {
		        	id = rs.getInt("ID");
		        }
		        
		        pstmt.close();
		        con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		
		return true;
	}

}

