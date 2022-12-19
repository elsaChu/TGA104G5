package tw.com.tibame.event.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import tw.com.tibame.event.model.OrderService;

/**
 * Servlet implementation class FrontendCheckTicketServlet
 */
@WebServlet("/FrontendCheckTicketServlet")
public class FrontendCheckTicketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontendCheckTicketServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	    String ticketNumber = request.getParameter("ticket");
	    OrderService orderService = new OrderService();
	    
	    boolean result = orderService.updateTicketToUsed(Integer.parseInt(ticketNumber));
	    String res = "";
	    if(result) {
	        res = "ticket use success!";
	    }else {
	        res = "ticket invalid or order cancel";
	    }
	    
	    PrintWriter out = response.getWriter();
        out.print(res);
        out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
