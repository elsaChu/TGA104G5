package tw.com.tibame.organizer.controller;

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

import tw.com.tibame.organizer.model.*;


@WebServlet("/OrganizerUpdateOne")
public class OrganizerUpdateOne extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String whichO = req.getParameter("whichRow");
		
		OrganizerService oi1 = new OrganizerService();
		OrganizerVO vo1 = new OrganizerVO();
		vo1 = oi1.getOneOrganizer(Integer.parseInt(whichO));
//		System.out.println(vo1);
		req.setAttribute("selected", vo1);
		
		String url = "/back-organizer-end/OrganizerUpdateInsert.jsp";
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);
//		res.sendRedirect(url);
		
	}
}

	
	

