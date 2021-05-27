package com.stardy.controller.study;

import com.stardy.entity.Category;
import com.stardy.util.DatabaseUtil;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/study/join")
public class StudyJoinController extends HttpServlet  {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // TODO Auto-generated method stub"

        request.setCharacterEncoding("utf-8");

        int sId = Integer.parseInt(request.getParameter("studyId"));
        int mId = Integer.parseInt(request.getParameter("memberId"));
        int flag = 0;

        String sql = "INSERT INTO JOINED_STUDY(STUDY_ID, MEMBER_ID) VALUES (?, ?)";

        try {
            Connection con = DatabaseUtil.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, sId);
            pstmt.setInt(2, mId);
            flag = pstmt.executeUpdate();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(flag == 1)
            response.sendRedirect("/WEB-INF/views/study/board/detail.jsp?id="+sId);
    }


}
