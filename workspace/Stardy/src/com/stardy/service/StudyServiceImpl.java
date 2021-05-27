package com.stardy.service;

import com.stardy.entity.Study;
import com.stardy.entity.view.StudyView;
import com.stardy.util.DatabaseUtil;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
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
      if (flag) {
         sql = "SELECT * FROM STUDY_LIST WHERE MEMBER_ID=" + memberId + " AND ROWNUM <= 10";

      } else
         sql = "SELECT * FROM STUDY_LIST WHERE NOT MEMBER_ID IN " + memberId + " AND ROWNUM <= 10";

      if (!flag && memberId.equals("")) {
         sql = "SELECT * FROM STUDY";
      }
      System.out.println(sql);

      Connection con = null;
      PreparedStatement pstmt = null;

      con = DatabaseUtil.getConnection();
      pstmt = con.prepareStatement(sql);
      ResultSet rs = pstmt.executeQuery();

      while (rs.next()) {

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
      if (rs.next()) {
         flag = true;
      }
      pstmt.close();
      con.close();
      rs.close();

      return flag;
   }

   public boolean isMember(int memberId, int studyId) throws SQLException {

      String sql = "SELECT * FROM JOINED_STUDY WHERE MEMBER_ID=" + memberId + " AND STUDY_ID=" + studyId;
      System.out.println(sql);
      System.out.println("스터디아이디 멤버아이디 " + memberId + " " + studyId);
      Connection con = null;
      Statement st = null;
      con = DatabaseUtil.getConnection();
      st = con.createStatement();
      ResultSet rs = st.executeQuery(sql);

      boolean flag = false;
      if (rs.next()) {
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

      if (rs.next()) {
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

         StudyView studyList = new StudyView(id, title, intro, open, limit, regDate, updateDate, dueDate, bg, path,
               member_Id, categoryId, cnt, name);

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

      if (rs.next()) {
         time = Integer.parseInt(rs.getString("TIMES"));
      }

      pstmt.close();
      con.close();
      rs.close();

      return time;
   }

//페이징 처리 리스트 가져오기 
   
   @Override
   public List<StudyView> getList() {
      
      return getList("","", 1);
   }


   @Override
   public List<StudyView> getList(int page) {


      return getList("","", page);

   }


   @Override
   public List<StudyView> getList(String category, String query, int page) {
      System.out.println("카테고리 프린트 : "+category);
      List<StudyView> list = new ArrayList<>();
      int count=0;
      String sql = "SELECT * " + "FROM (SELECT ROWNUM AS rnum, A.*"
            + "FROM (SELECT * FROM study_view WHERE CATEGORY_ID like '%'||?||'%' AND TITLE LIKE '%'||?||'%' ORDER BY ID DESC) A)"
            + "WHERE rnum >= ? AND rnum <=?";

      Connection con = DatabaseUtil.getConnection();

      try {
         PreparedStatement pstmt = con.prepareStatement(sql);
         int startPage = (page - 1) * 10 + 1;
         int endPage = startPage + 10 - 1;
         
         pstmt.setString(1, category);
         pstmt.setString(2, query);
         pstmt.setInt(3, startPage);
         pstmt.setInt(4, endPage);
         ResultSet rs = pstmt.executeQuery();

         while (rs.next()) {

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

            int cnt = rs.getInt("CNT");
            String name = rs.getString("NAME");

            StudyView studyView = StudyView.builder().id(id).title(title).intro(intro).open(open).limit(limit)
                  .regDate(regDate).updateDate(updateDate).dueDate(dueDate).bg(bg).path(path).memberId(memberId)
                  .categoryId(categoryId).cnt(cnt).name(name).build();

            list.add(studyView);
            count++;
            System.out.println("디비ㅇㅇ count 수"+count);
            
         }

         rs.close();
         pstmt.close();
         con.close();

      } catch (SQLException e) {

         e.printStackTrace();
      }

      return list;

   }
   
   // 총 레코드 수 구하기
   public int paging() {
      return paging("", "");
   }
      
   @Override
   public int paging(String category, String query) {

      Connection con = DatabaseUtil.getConnection();
      System.out.println("c:"+category +", q:"+query);
      String sql = "select count(*) count from study_view where category_id like '%'||?||'%'  and title like '%'||?||'%' ";
      System.out.println(sql);
      ResultSet rs = null;
      int totalContent = 0;

      try {
         PreparedStatement pstmt = con.prepareStatement(sql);
         pstmt.setString(1, category);
         pstmt.setString(2, query);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            System.out.println("enter");
            totalContent = rs.getInt("count");
         }
      } catch (SQLException e) {

         e.printStackTrace();
      }

      // 총 페이지 수

      return totalContent;
   }

}