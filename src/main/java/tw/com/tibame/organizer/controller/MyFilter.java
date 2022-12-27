package tw.com.tibame.organizer.controller;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

//@WebFilter(urlPatterns = { "/faces/Html/Employee","/faces/Html/Admin", "/faces/Html/Supervisor"})
//@WebFilter(
//        urlPatterns = "/admin",
//        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD}
//)
//@WebFilter("/MyFilter")
public class MyFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		System.out.println("filter..start");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();
		req.setAttribute("MyFilter", "ih");
//		out.print("hi");
		
		// pass the request along the filter chain
		//FILTER執行完 繼續執行FILTERCHAIN的下一個FILTER 若沒有FILTER則繼續執行REQUEST(拜訪網頁本身)
		//可以拿掉看看則 REQ只執行到這個FILTER的指令結束
		chain.doFilter(req, res);
		//若不把 chain.doFilter擺在最後 DOFILTER後還有其他程式 仍會執行 但會顯示在呈現網頁畫面之後(也就是畫面最下面)
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}
}
