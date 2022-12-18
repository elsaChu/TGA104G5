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

@WebServlet("/OrganizerLogin")
public class OrganizerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		HttpSession session = req.getSession();
		
		String OAccount = req.getParameter("OAccount");
		String OPassword =  req.getParameter("OPassword");
		System.out.println("Oaccount1: " + OAccount);
		
		OrganizerVO ov1 = new OrganizerVO();
		ov1.setOAccount(OAccount);
		ov1.setOpassword(OPassword);
		
		OrganizerService os1 = new OrganizerService();
		System.out.println(os1);
		String loginStatus;
		String url;
		loginStatus = os1.login(ov1);
		System.out.println(loginStatus);
		session.setAttribute("loginStatus", loginStatus);
		url = "/back-organizer-end/OrganizerLogin1.jsp";
		if(loginStatus != null) {
			if(loginStatus.equals("Success")) {
				//之後改成進入廠商設定葉面 不會再進到LOGINDONEJSP
				url = "/back-organizer-end/OrganizerSelectAll.jsp";			
			}else {
				url = "/back-organizer-end/OrganizerLoginDone.jsp";
			}
		}
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);
	}


}
