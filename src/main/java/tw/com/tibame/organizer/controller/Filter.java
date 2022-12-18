package tw.com.tibame.organizer.controller;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/Filter")
public class Filter extends HttpFilter {
  
	public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpSession session1 = req.getSession();
		Integer memberId = (Integer) session1.getAttribute("login");
		if(memberId == null) {
			//未登入
			req.getRequestDispatcher("login.jsp").forward(req, res);
		}else {
			//已登入
			super.doFilter(req, res, chain);
		}
		
		chain.doFilter(req, res);
	}


}
