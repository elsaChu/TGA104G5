package tw.com.tibame.organizer.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.com.tibame.organizer.model.OrganizerService;
import tw.com.tibame.organizer.model.OrganizerVO;

@WebServlet("/OrganizerLogOut")
public class OrganizerLogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		
		//log out user
		String url;
		String loginStatus = "logged out";
		HttpSession session1 = req.getSession();
		session1.setAttribute("loginStatus", loginStatus);
		session1.invalidate();
		System.out.println("User has logged out (msg from servlet)");
		

			
		//之後改成進入廠商設定頁面 不會再進到LOGINDONEJSP
		url = "/back-organizer-end/register-login/OrganizerLogin1.jsp";	
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);
		return;
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req,res);
	}

}
