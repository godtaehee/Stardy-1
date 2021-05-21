package com.stardy.service;

import com.stardy.entity.Study;
import com.stardy.entity.view.StudyView;
import com.stardy.util.DatabaseUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudyServiceImpl implements StudyService {
	

    public List<Study> getList(boolean flag, String memberId) throws SQLException {

        List<Study> list = new ArrayList<>();
        
        // 내가 가입한 스터디 목록 true , 내가 가입하지 않은 스터디 목록 false

        String sql = "";
        if(flag) {
        	sql = "SELECT * FROM STUDY_LIST WHERE MEMBER_ID="+memberId + " AND ROWNUM <= 10";

        }else
        	sql = "SELECT * FROM STUDY_LIST WHERE NOT MEMBER_ID IN " + memberId + " AND ROWNUM <= 10";

        if(!flag && memberId.equals("")) {
        	sql = "SELECT * FROM STUDY";
        }
        System.out.println(sql);

        Connection con = null;
        PreparedStatement pstmt = null;

            con = DatabaseUtil.getConnection();
            pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

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
            	
            	int member_Id = rs.getInt("MEMBER_ID");
            	int categoryId = rs.getInt("CATEGORY_ID");
           

                Study study = new Study();
                
                study.setId(id);
                study.setTitle(title);
                study.setIntro(intro);
                study.setOpen(open);
                study.setLimit(limit);
                study.setRegDate(regDate);
                study.setUpdateDate(updateDate);
                study.setDueDate(dueDate);
                study.setBg(bg);
                study.setPath(path);
                study.setMemberId(member_Id);
                study.setCategoryId(categoryId);
                
                list.add(study);
            }


        pstmt.close();
        con.close();
        rs.close();
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


    public StudyView getStudy(int id) throws SQLException {
        String sql = "SELECT * FROM STUDY_LIST WHERE ID=?";

        Connection con = null;
        PreparedStatement pstmt = null;

        con = DatabaseUtil.getConnection();
        pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next()) {
            String title = rs.getString("TITLE");
            String intro = rs.getString("INTRO");
            String open = rs.getString("OPEN");
            String limit = rs.getString("LIMIT");
            Date regDate = rs.getDate("REGDATE");
            Date updateDate = rs.getDate("UPDATEDATE");
            Date dueDate = rs.getDate("DUEDATE");
            String bg = rs.getString("BG");
            String path = rs.getString("PATH");
            int member_Id = rs.getInt("MEMBER_ID");
            int categoryId = rs.getInt("CATEGORY_ID");
            int cnt = rs.getInt("CNT");
            String name = rs.getString("NAME");

            StudyView studyList = new StudyView(id,title,intro,open,limit,regDate,updateDate,dueDate,bg,path,member_Id,categoryId,cnt,name);

            pstmt.close();
            con.close();
            rs.close();

            return studyList;
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


}
	