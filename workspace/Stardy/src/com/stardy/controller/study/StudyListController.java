package com.stardy.controller.study;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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

import com.stardy.entity.view.StudyList;
import com.stardy.util.DatabaseUtil;

@WebServlet("/study/list")
public class StudyListController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<StudyList> list = new ArrayList();

		String sql ="SELECT * FROM STUDY_VIEW";
		Connection con = DatabaseUtil.getConnection();
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			
			
			while(rs.next()) {
				   int id = rs.getInt("id");
				   String title = rs.getString("title");
				   String intro = rs.getString("intro");
				   String open = rs.getString("open");
				   String limit = rs.getString("limit");
				      
				   Date regDate = rs.getDate("regDate");
				   Date updateDate = rs.getDate("updateDate");
				   Date dueDate = rs.getDate("dueDate");
				      
				   String bg = rs.getString("bg");
				   String path = rs.getString("path");
				      
				   int memberId = rs.getInt("memberId");
				   int categoryId = rs.getInt("categoryId");
				   
				   int cnt=rs.getInt("cnt");
				   String name = rs.getString("name");
				   
				   
				   StudyList studyList = new StudyList(
						   id,title,intro,open,limit,regDate,updateDate,dueDate,bg,path,
						   memberId,categoryId,cnt,name
						   );
				   
				   list.add(studyList);
			}
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

