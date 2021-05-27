package com.stardy.controller.mypage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.stardy.entity.Member;
import com.stardy.service.MemberService;
import com.stardy.service.MemberServiceImpl;
import com.stardy.util.Logger;
import com.stardy.util.UploadUtil;

@WebServlet("/mypage/upload")
@MultipartConfig(
    fileSizeThreshold = 1024*1024,
    maxFileSize = 1024*1024*50, //50메가
    maxRequestSize = 1024*1024*50*5 // 50메가 5개까지
)
public class CommonController extends HttpServlet{

	MemberService service = new MemberServiceImpl();
	Logger log = new Logger();
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		UploadUtil util = UploadUtil.create(request.getServletContext());

		/* 파일 저장 로직 */
		Part part = request.getPart("uploadFile");
		
		int memberId = (int) request.getSession().getAttribute("id");
		String uuid = UUID.randomUUID().toString();
		String fileName = part.getSubmittedFileName();
		String filePath = util.createFilePath();
		
		util.saveFiles(part, uuid, filePath);
		
		//MEMBER DB UPDATE logic
		Member member = service.get(memberId);
		
		member.setProfile(uuid + "_" + fileName);
		member.setPath(filePath);
		service.modify(member);
		
		request.getSession().setAttribute("profile", member.getProfile());
		request.getSession().setAttribute("path", member.getPath());
		
		out.print("upload success");
	}
}
