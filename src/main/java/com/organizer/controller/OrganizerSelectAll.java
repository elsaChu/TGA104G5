package com.organizer.controller;

import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.organizer.model.OrganizerService;
import com.organizer.model.OrganizerVO;


	//@WebServlet("/OrganizerSelectAll")
@WebServlet("/OrganizerSelectAll")
public class OrganizerSelectAll extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		
//		OrganizerVO ov1 = new OrganizerVO();
		OrganizerService oi1 = new OrganizerService();
		List<OrganizerVO> saList = oi1.selectAll();
//		System.out.println(saList);
		
//		使用SESSION來儲存attribute(setAttribute()),只要網頁不關下面會一直跑出SELECTALL的結果，不論刷新幾次
		HttpSession session = req.getSession();
		session.setAttribute("saAttribute", saList);
		
//		但用request來儲存ATTRIBUTE，RD.FORWARD()出去之後就抓不到了 該怎辦
//		req.setAttribute("saAttribute", saList);
		
		String url = "/back-organizer-end/organizer/OrganizerSelectAll.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);
	
		
	}

}

	
	

