package com.stardy.controller.board;

import com.stardy.entity.Study;
import com.stardy.util.DatabaseUtil;
import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;


@WebServlet("/study/write")
public class WriteController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    	req.setCharacterEncoding("utf-8");
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        int id = (int) req.getSession().getAttribute("id");
        String nickname = (String) req.getSession().getAttribute("nickname");
      
        int sid = Integer.parseInt(req.getParameter("id"));
    

        String sql = "INSERT INTO BOARD(TITLE, CONTENT, MEMBER_ID, STUDY_ID) VALUES (?, ?, ?, ?)";


        Connection con = DatabaseUtil.getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);

        pstmt.setString(1, title);
        pstmt.setString(2, content);
        pstmt.setInt(3, id);
        pstmt.setInt(4, sid);


        int flag = pstmt.executeUpdate();

        System.out.println(flag);
        if(flag == 1)

            resp.sendRedirect("/study/board/detail.jsp?id=" + sid);




        pstmt.close();
        con.close();


    }
}
