package com.stardy.controller.board;

import com.stardy.entity.Board;
import com.stardy.entity.Files;
import com.stardy.entity.Study;
import com.stardy.service.BoardService;
import com.stardy.service.BoardServiceImpl;
import com.stardy.util.DatabaseUtil;
import com.stardy.util.UploadUtil;

import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@WebServlet("/study/write")
@MultipartConfig(
	    fileSizeThreshold = 1024*1024,
	    maxFileSize = 1024*1024*50, //50메가
	    maxRequestSize = 1024*1024*50*5 // 50메가 5개까지
	)

public class WriteController extends HttpServlet {
	
	BoardService service = new BoardServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	
	request.setCharacterEncoding("UTF-8");
		
		UploadUtil util = UploadUtil.create(request.getServletContext());

		/* 파일 저장 로직 */
		List<Part> parts = (List<Part>) request.getParts();
		List<Files> files = null;
		
		
		if(!parts.isEmpty()) {
			files = new ArrayList<>();
			
			for(Part part : parts) {
				
				if(!part.getName().equals("uploadFile")) continue;
				if(part.getSize() == 0) continue; //파일을 올리지 않아도 데이터가 날아오는데, 이때 파일의 데이터가 0
				
				String fileName = part.getSubmittedFileName();
				String uuid = UUID.randomUUID().toString();
				String filePath = util.createFilePath();
				
				util.saveFiles(part, uuid, filePath);
				
				Files file = Files.builder()
						.name(fileName)
						.path(filePath)
						.uuid(uuid)
						.build();
				
				System.out.println(file);
				files.add(file);
			}
		}
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int studyId = Integer.parseInt(request.getParameter("sId"));
		int memberId = (int) request.getSession().getAttribute("id");
		
		System.out.println(title);
		System.out.println(content);
		System.out.println(studyId);
		System.out.println(memberId);
		
		
		
		Board board = Board.builder()
				.title(title)
				.content(content)
				.memberId(memberId)
				.studyId(studyId)
				.build();
		
		int result = service.write(board, files);
				
		response.sendRedirect("/study/board/detail?id="+studyId);

    	


    }
}