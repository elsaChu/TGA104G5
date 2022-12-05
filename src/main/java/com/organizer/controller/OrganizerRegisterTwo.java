package com.organizer.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.organizer.model.OrganizerService1;
import com.organizer.model.OrganizerVO1;


//@WebServlet("/OrganizerRegisterTwo")
@WebServlet("/OrganizerRegisterTwo")
public class OrganizerRegisterTwo extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		 
		String OAccount = req.getParameter("OAccount");
		String OPassword =  req.getParameter("OPassword");
		String organizerName = req.getParameter("organizerName");
		String windowEmail = req.getParameter("windowEmail");
		String windowName = req.getParameter("windowName");
		String windowPhone =  req.getParameter("windowPhone");
				
		OrganizerVO1 ov1 = new OrganizerVO1();
		System.out.println(ov1);
		ov1.setAccountName(OAccount);
		ov1.setOpassword(OPassword);
		ov1.setOrganizerName(organizerName);
		ov1.setWindowName(windowName);
		ov1.setWindowPhone(windowPhone);
		ov1.setWindowEmail(windowEmail);
		
		
		OrganizerService1 os1 = new OrganizerService1();
		System.out.println(os1);
		os1.addOrganizer(ov1);
		
	}


}
