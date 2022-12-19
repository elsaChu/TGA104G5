package tw.com.tibame.event.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.google.gson.Gson;

import tw.com.tibame.event.model.EventVO;
import tw.com.tibame.event.model.OrderService;

@WebServlet("/FrontendEventServlet")
public class FrontendEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String eventNumber = request.getParameter("eventNumber");
	    if(eventNumber != null) {
	    	OrderService orderService = new OrderService();
	        request.setAttribute("event", orderService.queryEventByEventNumber(Integer.parseInt(eventNumber)));
	        //跳轉頁面進入，清空session
	        HttpSession session = request.getSession();
	        session.removeAttribute("selectEventInfo");
	    }
	    
	    request.getRequestDispatcher("/front-end/event/selectEvent.jsp").forward(request, response);
	   
	}

//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//	    
//	}

}
