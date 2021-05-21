package com.stardy.controller.root;

import java.io.IOException;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.stardy.entity.Member;

import com.stardy.service.MemberServiceImpl;
import com.stardy.util.Logger;

@WebServlet("/findpw")
public class FindpwController extends HttpServlet {

	static MemberServiceImpl service = new MemberServiceImpl();
	static Logger log = new Logger();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String memberEmail = request.getParameter("memberEmail");
		
		response.setCharacterEncoding("UTF-8");
		
		// 존재하는 이메일인지 확인
		Member m = service.getUser(memberEmail);
		if (m == null) {
			log.info("이메일 없음");
			request.setAttribute("msg", "이메일 정보가 맞지 않습니다.");
			request.setAttribute("historyBack", true);
			request.getRequestDispatcher("/findPw.jsp").forward(request, response);
			return;
		}

		// 메일 서버를 설정해줌
		String host = "smtp.naver.com";
		String user = "der93@naver.com";
		String password = "penguin31";
		String page = "http://localhost:8080/";
		
		
		// 메일 받을 주소
		String to_email = m.getEmail();
		System.out.println(to_email);

		// SMTP 서버 정보를 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.ssl.enable", "true");

		// SMTP 서버 정보와 사용자 정보를 기반으로 session클래스의 인스턴스를 생성한다.
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});


		
		// Message 클래스의 객체를 사용하여 수신자와 내용,제목의 메세지를 작성한다.
		// Transport 클래스를 사용하여 작성한 메세지를 전달한다.
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email)); // 사용자 메일에 전송

			// 메일 제목과 내용 
			
			message.setSubject("[Stardy] 비밀번호 변경 메일입니다.");
			message.setContent("<h1>비밀번호 변경요청</h1>" +"<br>"+"안녕하세요." +"<br>"
			+ "<p>비밀번호 변경 요청이 발생했음을 알려드립니다.</p>" + "<br>" + 
					"<p>비밀번호를 변경하려면 아래 링크를 방문해주세요<p>"+"<br>"
			+ "<a href='" + page +"changePw.jsp?email=" + to_email + "'>비밀번호 변경하기</a>","text/html;charset=utf-8");
			
			Transport.send(message);
			System.out.println("이메일 전송 완료");
			
			request.getSession().setAttribute("userMail", to_email);
			request.getRequestDispatcher("/findPw2.jsp").forward(request, response);

			

		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

//	//인증 코드 생성 
//	private String code() {
//		String[] str = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
//				"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
//				"1", "2", "3", "4", "5", "6", "7", "8", "9"};
//		String newCode = new String();
//		
//		for(int i=0;i<8;i++) {
//			int random =(int)(Math.random()*str.length);
//			newCode += str[random];
//		}
//		return newCode;
//				
//		}
		
	}


