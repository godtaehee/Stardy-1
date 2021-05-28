package com.stardy.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class LoginFilter implements Filter{

	private final String[] uris = {"/board/", "/study/", "/mypage/"};
	
	private boolean isAuthRequired(String userUri) {
		
		for(String uri : uris)
			if(userUri.startsWith(uri))
				return true;
		
		return false;
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		boolean isLogin = false;
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		Integer loginId = (Integer) req.getSession().getAttribute("id");
		String nickname = (String) req.getSession().getAttribute("nickname");
		String userUri = req.getRequestURI();
		
		if(isAuthRequired(userUri)) 
		{
			if(loginId != null && nickname != null)
				isLogin = true;
			
			if(isLogin) 
			{
				chain.doFilter(request, response);
			}
			else 
			{
				resp.sendRedirect("/login?uri=" + userUri);
				return;
			}
		}
		else 
		{
			chain.doFilter(request, response);
		}
	}

}
