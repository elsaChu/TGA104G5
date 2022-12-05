package com.product.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductOrderService;

@WebServlet("/ProductOrderController")
public class ProductOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ProductOrderService service = new ProductOrderService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

//接收資料
		String temp1 = request.getParameter("prodOrderNo");
		String temp2 = request.getParameter("number");
		String temp3 = request.getParameter("amountPrice");
		String temp4 = request.getParameter("prodTotal");
		String temp5 = request.getParameter("paymentDate");
		String temp6 = request.getParameter("receiverName");
		String temp7 = request.getParameter("receiverTel");
		String temp8 = request.getParameter("shippingAdd");
		String temp9 = request.getParameter("prodOrderStatus");
		String temp10 = request.getParameter("deliveryStatus");
		
//驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		request.setAttribute("errors", errors);

		
		
	}

}
