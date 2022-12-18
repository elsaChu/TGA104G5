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
				//在這邊RETURN 即代表 後面的程式都不會再繼續執行了 因為整個DOFILTER方法停止 
				//其他層FILTER也不會執行 因為沒有CHAIN DOFILTER 會直接跳過
			}
		}else {
			System.out.println("loginDone == null");
			res1.sendRedirect(req1.getContextPath()+ "/back-organizer-end/OrganizerLogin1.jsp");
			return;
		}
		session.setAttribute("MyFilter", "ih");
		chain.doFilter(req, res);
		
		//若不把 chain.doFilter擺在最後 DOFILTER後還有其他程式 仍會執行 但會顯示在呈現網頁畫面之後(也就是畫面最下面)
	}


	public void destroy() {
		fc1 = null;
	}
}
