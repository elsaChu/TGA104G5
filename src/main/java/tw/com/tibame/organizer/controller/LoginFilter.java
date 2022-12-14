package tw.com.tibame.organizer.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebFilter(urlPatterns = { "/faces/Html/Employee","/faces/Html/Admin", "/faces/Html/Supervisor"})
//@WebFilter(
//        urlPatterns = "/admin",
//        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD}
//)
//@WebFilter(servletNames = {"MyOwnServlet", "UploadServlet"})
@WebFilter(
		urlPatterns = {"/back-organizer-end/OrganizerSelectAll.jsp"},
		dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD}
		)
public class LoginFilter extends HttpFilter implements Filter {
	private FilterConfig fc1;
	
	public void init(FilterConfig fConfig) throws ServletException {
		fc1 = fConfig;
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req1 = (HttpServletRequest)req;
		HttpServletResponse res1 = (HttpServletResponse) res;
		HttpSession session = req1.getSession();
		
		System.out.println("filter..start");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		out.print("<h2 style=\"color:white\"> login servlet running here </h2>");
		
		String loginDone = (String) session.getAttribute("loginStatus");
		System.out.println("loginStatus: " + loginDone);
		if(loginDone != null) {
			if(loginDone.equals("Success")) {
				out.print("<h2 style=\\\"color:white\\\">login is done</h2>");
				System.out.println("login is done");
			}else {
				out.print("<h2 style=\\\"color:white\\\">login not done</h2>");
				System.out.println("login not done");
				res1.sendRedirect(req1.getContextPath()+ "/back-organizer-end/OrganizerLogin1.jsp");
				return;
				//?????????RETURN ????????? ?????????????????????????????????????????? ????????????DOFILTER???????????? 
				//?????????FILTER??????????????? ????????????CHAIN DOFILTER ???????????????
			}
		}else {
			System.out.println("loginDone == null");
			res1.sendRedirect(req1.getContextPath()+ "/back-organizer-end/OrganizerLogin1.jsp");
			return;
		}
		session.setAttribute("MyFilter", "ih");
		chain.doFilter(req, res);
		
		//????????? chain.doFilter???????????? DOFILTER????????????????????? ???????????? ???????????????????????????????????????(????????????????????????)
	}


	public void destroy() {
		fc1 = null;
	}
}
