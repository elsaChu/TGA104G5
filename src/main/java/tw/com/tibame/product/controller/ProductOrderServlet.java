package tw.com.tibame.product.controller;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

import tw.com.tibame.product.model.*;

@WebServlet("/ProductOrderServlet")

public class ProductOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // (search by product order number) request from
													// selectProductOrder.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage
			// view.
			req.setAttribute("errorMsgs", errorMsgs);

			System.out.println("error.Msgs");

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String str = req.getParameter("prodOrderNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入/選擇商品訂單編號");
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-organizer-end/product/selectProductOrder.jsp");
				failureView.forward(req, res);
				return;
			}

			Integer prodOrderNo = null;
			try {
				prodOrderNo = Integer.valueOf(str);
			} catch (NumberFormatException e) {
				errorMsgs.add("商品訂單編號格式不正確");
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-organizer-end/product/listOneProductOrder.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ProductOrderService prodOrderSvc = new ProductOrderService();
			ProductOrderVO productorderVO = prodOrderSvc.getOneProductOrder(prodOrderNo);
			if (productorderVO == null) {
				errorMsgs.add("查無資料");
			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-organizer-end/product/listOneProductOrder.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("ProductOrderVO", productorderVO); // 資料庫取出的ProductOrderVO物件,存入req
			String url = "/back-organizer-end/product/listOneProductOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // forward to listOneProductOrder.jsp
			successView.forward(req, res);
		}
	}
}
