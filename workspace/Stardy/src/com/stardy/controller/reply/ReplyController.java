package com.stardy.controller.reply;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stardy.entity.Reply;
import com.stardy.entity.view.ReplyView;
import com.stardy.service.ReplyServiceImpl;
import com.stardy.util.Logger;

import lombok.extern.log4j.Log4j;

@WebServlet("/replies/*")
public class ReplyController extends HttpServlet{

	ReplyServiceImpl replyService = new ReplyServiceImpl();
	Logger log = new Logger();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		String[] paths = request.getPathInfo().split("/");
		
		//댓글의 개수 조회
		if(paths[1].equals("get")) {
			
			int boardId = Integer.parseInt(paths[2]);
			
			int count = replyService.count(boardId);
			out.println(count);
		}
		//게시글의 댓글 조회 (page와 bid)
		else {
			int boardId = Integer.parseInt(paths[1]);
			int page = Integer.parseInt(paths[2]);
			
			log.info("board_id : " + boardId + ", page : " + page);
			
			List<ReplyView> list = replyService.getList(boardId, page);
			
//		JSONArray array = new JSONArray();
//		
//		list.stream().forEach(reply -> {
//			JSONObject obj = new JSONObject();
//			obj.put("content", reply.getContent());
//			obj.put("writer", reply.getWriter());
//			obj.put("rid", reply.getRid());
//			obj.put("bid", reply.getBid());
//			obj.put("regDate", reply.getRegDate().toString());
//			obj.put("email", reply.getEmail());
//			
//			array.add(obj);
//		});
			
			Gson gson = new GsonBuilder()
					   .setDateFormat("yyyy-MM-dd").create();
			String json = gson.toJson(list);
			
			out.println(json);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		log.info(request.getPathInfo());
		
		
		if(request.getPathInfo().split("/") [1].equals("add")) {
		
			/* JSON 데이터 받아오는 과정 */
			StringBuffer sb = new StringBuffer();
			BufferedReader br = request.getReader();
			String line = null;
			
			while((line = br.readLine()) != null) {
				sb.append(line);
			}
			log.info(sb.toString());
			/* JSON 데이터 받아오는 과정 */
			
			JSONParser parser = new JSONParser();
			try {
				JSONObject obj = (JSONObject) parser.parse(sb.toString());
				String content = (String) obj.get("content");
				int memberId = (int) request.getSession().getAttribute("id");
				int boardId = Integer.parseInt(String.valueOf(obj.get("id")));
				
				Reply reply = Reply.builder().memberId(memberId).content(content).boardId(boardId).build();
				
				replyService.register(reply);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		
		BufferedReader br = request.getReader();
		StringBuilder sb = new StringBuilder();
		String line = "";
		
		while( (line = br.readLine()) != null )
			sb.append(line);
		
		System.out.println(sb); //{"id":"39","content":"ㅎㅇㅎㅇawda"}
>>>>>>> refs/remotes/real/master
		
		try {
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(sb.toString());
			String content = (String) obj.get("content");
			int id = Integer.parseInt(String.valueOf(obj.get("id")));
			
			int result = replyService.modify(id, content);
			
			response.getWriter().println(result == 1? "success":"failure");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String[] paths = request.getPathInfo().split("/");
	
		int rid = Integer.parseInt(paths[1]);
		
		replyService.remove(rid);
		log.info(rid + "번 댓글을 삭제했습니다. 요청자 : " + (String) request.getSession().getAttribute("nickname"));

	}
}
