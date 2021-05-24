package com.stardy.controller.study;

import java.io.IOException;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stardy.entity.view.StudyView;
import com.stardy.util.DatabaseUtil;

@WebServlet("/study/list")
public class StudyListController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<StudyView> list = new ArrayList<>();

		String sql ="SELECT * FROM STUDY_VIEW";
		Connection con = DatabaseUtil.getConnection();
		int count=0;
		
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			while(rs.next()) {
				   int id = rs.getInt("ID");
				   String title = rs.getString("TITLE");
				   String intro = rs.getString("INTRO");
				   String open = rs.getString("OPEN");
				   String limit = rs.getString("LIMIT");
				      
				   Date regDate = rs.getDate("REGDATE");
				   Date updateDate = rs.getDate("UPDATEDATE");
				   Date dueDate = rs.getDate("DUEDATE");
				      
				   String bg = rs.getString("BG");
				   String path = rs.getString("PATH");
				      
				   int memberId = rs.getInt("MEMBER_ID");
				   int categoryId = rs.getInt("CATEGORY_ID");
				   
				   int cnt=rs.getInt("CNT");
				   String name = rs.getString("NAME");
				   
				   
				   StudyView studyView = StudyView.builder()
						   .id(id)
						   .title(title)
						   .intro(intro)
						   .open(open)
						   .limit(limit)
						   .regDate(regDate)
						   .updateDate(updateDate)
						   .dueDate(dueDate)
						   .bg(bg)
						   .path(path)
						   .memberId(memberId)
						   .categoryId(categoryId)
						   .cnt(cnt)
						   .name(name)
						   .build();
   				   
				   list.add(studyView);
				   count++;
				  
			}
			System.out.println("스터디 개수 : "+count);
			rs.close();
			st.close();
			con.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		request.setAttribute("list", list);
		
		request
		.getRequestDispatcher("/study/list.jsp")
		.forward(request, response);
		
	}	

}

