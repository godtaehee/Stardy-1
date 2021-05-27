package com.stardy.service;

import com.stardy.entity.Study;
import com.stardy.entity.view.StudyRegisterView;
import com.stardy.entity.view.StudyView;
import com.stardy.util.DatabaseUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudyServiceImpl implements StudyService {
	
	public List<StudyRegisterView> getMyStudyList(int memberIdParam) {
		
		List<StudyRegisterView> list = new ArrayList<>();
		
		if(memberIdParam == 0) 
			return list;
		
		String sql = "SELECT * FROM STUDY_REGISTER_VIEW WHERE MEMBER_ID=" + memberIdParam;
		
		try(
				Connection con = DatabaseUtil.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				) {
			
			while(rs.next()) {
				
				int studyId = rs.getInt("STUDY_ID");
				int memberId = rs.getInt("MEMBER_ID");
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				int leader = rs.getInt("LEADER");
				int categoryId = rs.getInt("CATEGORY_ID");
				int limit = rs.getInt("LIMIT");
				String open = rs.getString("OPEN");
				Date dueDate = rs.getDate("DUEDATE");
				String intro = rs.getString("INTRO");
				Date regDate = rs.getDate("REGDATE");
				Date updateDate = rs.getDate("UPDATEDATE");
				String bg = rs.getString("BG");
				String path = rs.getString("PATH");
				int cnt = rs.getInt("CNT");
				String name = rs.getString("NAME");
				
				StudyRegisterView study = new StudyRegisterView(studyId, memberId, id, title, leader, categoryId, limit, open,
						dueDate, intro, regDate, updateDate, bg, path, cnt, name);
				
				list.add(study);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;

	}
	
	public List<StudyRegisterView>  getNotMyStudyList(int memberIdParam) {
		
		List<StudyRegisterView> list = new ArrayList<>();
		
		if(memberIdParam == 0) 
			return list;
		
		String sql = "SELECT * FROM STUDY_REGISTER_VIEW WHERE NOT MEMBER_ID IN " + memberIdParam;
		
		try(
				Connection con = DatabaseUtil.getConnection();
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery(sql);
				) {
			
			while(rs.next()) {
				
				int studyId = rs.getInt("STUDY_ID");
				int memberId = rs.getInt("MEMBER_ID");
				int id = rs.getInt("ID");
				String title = rs.getString("TITLE");
				int leader = rs.getInt("LEADER");
				int categoryId = rs.getInt("CATEGORY_ID");
				int limit = rs.getInt("LIMIT");
				String open = rs.getString("OPEN");
				Date dueDate = rs.getDate("DUEDATE");
				String intro = rs.getString("INTRO");
				Date regDate = rs.getDate("REGDATE");
				Date updateDate = rs.getDate("UPDATEDATE");
				String bg = rs.getString("BG");
				String path = rs.getString("PATH");
				int cnt = rs.getInt("CNT");
				String name = rs.getString("NAME");
				
				StudyRegisterView study = new StudyRegisterView(studyId, memberId, id, title, leader, categoryId, limit, open,
						dueDate, intro, regDate, updateDate, bg, path, cnt, name);
				
				list.add(study);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;

	}

    
    public int getCrnt(Study study) throws SQLException {

		String sql = "SELECT COUNT(MEMBER_ID) CNT FROM JOINED_STUDY WHERE STUDY_ID=" + study.getId();
        Connection con = null;
        PreparedStatement pstmt = null;
        con = DatabaseUtil.getConnection();
        pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		
        int cnt = rs.getInt("CNT");
        
        pstmt.close();
        con.close();
        rs.close();
    		
    	
    	return cnt;
             
    }
    
    public String getLeader(int id) throws SQLException {
		
		String sql = "SELECT NICKNAME FROM MEMBER WHERE ID=" + id;
        Connection con = null;
        PreparedStatement pstmt = null;
        con = DatabaseUtil.getConnection();
        pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
		
        String nickName = rs.getString("NICKNAME");
        
        pstmt.close();
        con.close();
        rs.close();
    		
    	
    	return nickName;
             
    }
    
    public boolean isLeader(int memberId, int studyId) throws SQLException {
		
  		String sql = "SELECT * FROM STUDY WHERE MEMBER_ID=" + memberId + " AND ID=" + studyId;
          Connection con = null;
          PreparedStatement pstmt = null;
          con = DatabaseUtil.getConnection();
          pstmt = con.prepareStatement(sql);
  		ResultSet rs = pstmt.executeQuery();
  		
  		boolean flag = false;
  		if(rs.next()) {
  			flag = true;
  		}
  		
          pstmt.close();
          con.close();
          rs.close();
      		
      	
      	return flag;
               
      }
    
    public boolean isMember(int memberId, int studyId) throws SQLException {
    	
    	String sql = "SELECT * FROM JOINED_STUDY WHERE MEMBER_ID="+memberId+" AND STUDY_ID="+studyId;
    	System.out.println(sql);
  		System.out.println("스터디아이디 멤버아이디 " +memberId + " " + studyId);
        Connection con = null;
        Statement st = null;
        con = DatabaseUtil.getConnection();
        st = con.createStatement();
    		ResultSet rs = st.executeQuery(sql);
    		
    		boolean flag = false;
    		if(rs.next()) {
    			System.out.println("됬냐안됬");
    			flag = true;
    		}
    		
            st.close();
            con.close();
            rs.close();
        		
        	
        	return flag;
    	
    }


    public StudyRegisterView getStudy(int sId) throws SQLException {
        String sql = "SELECT * FROM STUDY_REGISTER_VIEW WHERE ID=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        con = DatabaseUtil.getConnection();
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, sId);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next()) {
        	int studyId = rs.getInt("STUDY_ID");
			int memberId = rs.getInt("MEMBER_ID");
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			int leader = rs.getInt("LEADER");
			int categoryId = rs.getInt("CATEGORY_ID");
			int limit = rs.getInt("LIMIT");
			String open = rs.getString("OPEN");
			Date dueDate = rs.getDate("DUEDATE");
			String intro = rs.getString("INTRO");
			Date regDate = rs.getDate("REGDATE");
			Date updateDate = rs.getDate("UPDATEDATE");
			String bg = rs.getString("BG");
			String path = rs.getString("PATH");
			int cnt = rs.getInt("CNT");
			String name = rs.getString("NAME");
			
			StudyRegisterView study = new StudyRegisterView(studyId, memberId, id, title, leader, categoryId, limit, open,
					dueDate, intro, regDate, updateDate, bg, path, cnt, name);
			
            pstmt.close();
            con.close();
            rs.close();

            return study;
        }

        return null;

    }


    public int getDuringTime(int id) throws SQLException {
        //
        String sql = "SELECT TRUNC(DUEDATE) - TRUNC(REGDATE) AS TIMES FROM STUDY WHERE ID=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        con = DatabaseUtil.getConnection();
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        int time = 0;

        if(rs.next()) {
            time = Integer.parseInt(rs.getString("TIMES"));
        }

        pstmt.close();
        con.close();
        rs.close();

        return time;
    }

    
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


}
	