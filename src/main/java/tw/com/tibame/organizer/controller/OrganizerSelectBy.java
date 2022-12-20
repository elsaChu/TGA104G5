package tw.com.tibame.organizer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tw.com.tibame.organizer.model.*;



@WebServlet("/OrganizerSelectBy")
public class OrganizerSelectBy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		
		//get parameters from http request
		String keyword = req.getParameter("searchKeyword");
		String testHidden = req.getParameter("testHidden");
		System.out.println(testHidden);
		//send to service
		OrganizerService os1 = new OrganizerService();
		List<OrganizerVO> VO = os1.searchOrganizer(keyword);
		req.setAttribute("matchList", VO);
		String url = "/back-organizer-end/OrganizerSelectByResult.jsp";
//		res.sendRedirect(url);
		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, res);
	}
}
