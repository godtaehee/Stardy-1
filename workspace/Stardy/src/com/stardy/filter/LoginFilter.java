package com.stardy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		boolean isLogin = false;
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		Integer loginId = (Integer) req.getSession().getAttribute("id");
		String nickname = (String) req.getSession().getAttribute("nickname");
		
		if(loginId != null && nickname != null)
			isLogin = true;
		
		if(isLogin) {
			chain.doFilter(request, response);
		}
		else {
			String referer = req.getHeader("Referer");
			String url = referer == null? "" : referer.substring(referer.indexOf("8080/") + 4);
			resp.sendRedirect("/login.jsp?url=" + url);
		}
	}

}
