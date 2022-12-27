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
		
		OrganizerService os1 = new OrganizerService();
		OrganizerVO ov1 = new OrganizerVO();
		OrganizerVO ov2 = new OrganizerVO();
		ov1.setOAccount(OAccount);
		ov1.setOpassword(OPassword);
		
		System.out.println(os1);
		String loginStatus;
		String url;
		loginStatus = os1.login(ov1);
		Boolean aStatus = os1.checkActivation(OAccount);
		System.out.println(loginStatus);
		System.out.println("Activation Status(from organizer login servlet): " + aStatus);
		session.setAttribute("loginStatus", loginStatus);
		if(loginStatus != null) {
			if(loginStatus.equals("Success") && aStatus == true) {
				//之後改成進入廠商設定頁面 不會再進到LOGINDONEJSP
				url = "/back-organizer-end/product/addProduct.jsp";	
				ov2 = os1.getOneOrganizer(OAccount);
				session.setAttribute("loginOrganizer", ov2);
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				return;
			}else {
				if(loginStatus.equals("Success") && aStatus == false) {
					loginStatus = "帳號未成功啟用";
					session.setAttribute("loginStatus", loginStatus);
				}
				url = "/back-organizer-end/register-login/OrganizerLoginDone.jsp";
				RequestDispatcher rd = req.getRequestDispatcher(url);
				rd.forward(req, res);
				return;
			}
		}
		//use organizer account to get organizerVO
		
		url = "/back-organizer-end/register-login/OrganizerLogin1.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);
	}


}
