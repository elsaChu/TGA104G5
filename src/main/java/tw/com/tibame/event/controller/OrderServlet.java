package tw.com.tibame.event.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.tibame.event.model.EventVO;
import tw.com.tibame.event.model.OrderService;
import tw.com.tibame.staff.model.StaffService;
import tw.com.tibame.staff.model.StaffVO;

public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		System.out.println("action: " + request.getParameter("action"));
		response.setContentType("text/html; charset=UTF-8");

		if ("search".equals(action)) { // 來自listAllStaff.jsp的請求
//		System.out.println("in search" + action);
			List<String> errorMsgs = new LinkedList<String>();
//		先把errorMsgs new出來，再裝進去request
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			String searchByNumber = request.getParameter("searchByNumber");
			StaffService staffSvc = new StaffService();
			StaffVO orderEventVO = null;
//		Integer int_sbn = null;
//		try {
//			 轉型 STR轉INT，用PARSEINT()
//			int_ssn = Integer.valueOf(searchByNumber);
//			staffVO = staffSvc.findByNumber(int_sbn);
//			if(orderEventVO.getStaffNumber() == null) {
//				orderEventVO = null;
//			}
//		} catch (NumberFormatException e) {
//			errorMsgs.add("未輸入員工編號");
//		}
//		
//		System.out.println("staffVO servlet="+staffVO.toString());
//		if (staffVO == null && int_ssn != null) {
//			System.out.println("in null");
//			errorMsgs.add("此員工編號不存在，請再確認一次");
//		}
//
//		if (!errorMsgs.isEmpty()) {
//			RequestDispatcher failureView = request.getRequestDispatcher("/back-staff-end/staff/listAllStaff.jsp");
//			failureView.forward(request, response);
//			return;// 程式中斷
//		}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			request.setAttribute("orderEventVO", orderEventVO); // 資料庫取出的staffVO物件,存入request
			String url = "/back-staff-end/staff/ticketOrder.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 ticketOrder.jsp
			successView.forward(request, response);
		}
		
		if ("searchAllEvent".equals(action)) { // 來自listAllStaff.jsp的請求
//			System.out.println("in search" + action);
				List<String> errorMsgs = new LinkedList<String>();
//			先把errorMsgs new出來，再裝進去request
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				request.setAttribute("errorMsgs", errorMsgs);

				String findByOrganizerNumber = request.getParameter("findByOrganizerNumber");
				OrderService orderSvc = new OrderService();
				EventVO eventVO = null;
			Integer int_sbn = null;
//			try {
////				 轉型 STR轉INT，用PARSEINT()
//				int_ssn = Integer.valueOf(searchByNumber);
//				staffVO = staffSvc.findByNumber(int_sbn);
//				if(orderEventVO.getStaffNumber() == null) {
//					orderEventVO = null;
//				}
//			} catch (NumberFormatException e) {
//				errorMsgs.add("未輸入員工編號");
//			}
//			
//			System.out.println("staffVO servlet="+staffVO.toString());
//			if (staffVO == null && int_ssn != null) {
//				System.out.println("in null");
//				errorMsgs.add("此員工編號不存在，請再確認一次");
//			}
//	
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = request.getRequestDispatcher("/back-staff-end/staff/listAllStaff.jsp");
//				failureView.forward(request, response);
//				return;// 程式中斷
//			}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				request.setAttribute("eventVO", eventVO); // 資料庫取出的staffVO物件,存入request
				String url = "/back-staff-end/staff/ListAllEvent.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 ticketOrder.jsp
				successView.forward(request, response);
			}

	}
}
