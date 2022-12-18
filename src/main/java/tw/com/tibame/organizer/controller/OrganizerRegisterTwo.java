package tw.com.tibame.organizer.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tw.com.tibame.main.MailService;
import tw.com.tibame.organizer.model.*;


@WebServlet("/OrganizerRegisterTwo")
public class OrganizerRegisterTwo extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		HttpSession session1 = req.getSession();
		 
		String OAccount = req.getParameter("OAccount");
		String OPassword =  req.getParameter("OPassword");
		String organizerName = req.getParameter("organizerName");
		String windowEmail = req.getParameter("windowEmail");
		String windowName = req.getParameter("windowName");
		String windowPhone =  req.getParameter("windowPhone");
//		System.out.println("Oaccount1: " + OAccount);
		
		OrganizerVO ov1 = new OrganizerVO();
//		System.out.println(ov1);
		ov1.setOAccount(OAccount);
		ov1.setOpassword(OPassword);
		ov1.setOrganizerName(organizerName);
		ov1.setWindowName(windowName);
		ov1.setWindowPhone(windowPhone);
		ov1.setWindowEmail(windowEmail);
		
		OrganizerService os1 = new OrganizerService();
		System.out.println(os1);
		String isNameTaken;
		
		isNameTaken = os1.addOrganizer(ov1);
		req.setAttribute("isNameTaken", isNameTaken);
		
		String url = "/back-organizer-end/OrganizerRegisterDone.jsp";
		//若SESSION內已註冊過且寄出驗證碼的請求處理 要導到哪裡??
//		if(session1.getAttribute("authCode")!= null) {
//			System.out.println("already sent an authCode");
//			url = "/back-organizer-end/RegisterVerify.jsp";
//			RequestDispatcher rd = req.getRequestDispatcher(url);
//			rd.forward(req, res);
//			return;
//		}
		
		//if insert dao (register)is successful, use mail service function to send verification code
		if(isNameTaken.equals("Insert Success")) {
			System.out.println("send verification mail");
			MailService mailService = new MailService();
			String to = "52rondo@gmail.com";
			String subject = " TICK IT 帳號啟用";
			String ch_name = "親愛的使用者 " + OAccount;
			String authCode = mailService.genAuthCode();
			String messageText = "您好 " + ch_name + "\n\n\n\n\n" +
								"  您的帳號啟用碼為: " + authCode + "\n" ;
			mailService.sendMail(to, subject, messageText);
			//also forward req to verifying page
			System.out.print("authCode from OrganierRegisterTwo:" + authCode);
			session1.setAttribute("authCode", authCode);
			url = "/back-organizer-end/RegisterVerify.jsp";
			RequestDispatcher rd = req.getRequestDispatcher(url);
			rd.forward(req, res);
			return;
		}else {
			System.out.println(isNameTaken);
		}
		
//		res.sendRedirect(url);
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);
	}


}
