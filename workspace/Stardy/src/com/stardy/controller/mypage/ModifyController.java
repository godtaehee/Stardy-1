package com.stardy.controller.mypage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stardy.entity.Member;
import com.stardy.service.MemberService;
import com.stardy.service.MemberServiceImpl;
import com.stardy.util.Logger;

@WebServlet("/mypage/modify")
public class ModifyController extends HttpServlet{

	MemberServiceImpl memberService = new MemberServiceImpl();
	Logger log = new Logger();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Integer loginId = (Integer) request.getSession().getAttribute("id");

		MemberService service = new MemberServiceImpl();

		Member member = service.get(loginId);
		
		request.setAttribute("member", member);
		
		request.getRequestDispatcher("/WEB-INF/views/mypage/modify.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = (int) request.getSession().getAttribute("id");
		Member member = memberService.get(id);
		
		
		String nickname = request.getParameter("nickname");
		String password = request.getParameter("password");
		
		String msg = "";
		boolean failure = false;
		
		/* 닉네임 변경 시 이미 존재하는지 검증 */
		if(nickname != null && !nickname.equals("")) {
			if(!checkNickname(nickname)) { //합격
				member.setNickname(nickname);				
			}
			else { //실패
				msg = "dupError";
				failure = true;
			}
		}
					
		
		/* Password 변경 시 널 또는 공백인지 검증 */
		if(!password.equals("") && password != null)
			member.setPassword(password);
		
		log.info("failure : " + failure);
		/* !failure */
		if(!failure) {
			memberService.modify(member);
			response.sendRedirect("/mypage/modify");
		}
		/* failure */
		else {
			response.sendRedirect("/mypage/modify?msg=" + msg);			
>>>>>>> refs/remotes/real/master
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String profile = request.getParameter("profile");
		
		StringBuilder sb = new StringBuilder();
		BufferedReader br = request.getReader();
		String line = "";
		
		while((line = br.readLine()) != null)
			sb.append(line);

		JsonParser parser = new JsonParser();
		JsonObject obj =  (JsonObject) parser.parse(sb.toString());
		
		String status = obj.get("status").getAsString();
		int id = (int) request.getSession().getAttribute("id");
		
		Member member = memberService.get(id);
		
		member.setStatus(status);
		log.info(member.toString());
		memberService.modify(member);
	}
	
	public boolean checkNickname(String nickname) {
		
		return memberService.isExistNick(nickname);
	}
}
