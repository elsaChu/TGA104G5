package com.order.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.order.service.ProductOrderService;
import com.order.service.ProductOrderServiceImpl;
import com.order.vo.ProductOrderVO;

@WebServlet("/ProductOrder/list")
public class ProductOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private ProductOrderService service;	
	@Override
	public void init() throws ServletException {
		service = new ProductOrderServiceImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

//		Integer number =  (Integer) request.getSession().getAttribute("number");
		Integer number = 5;  // 先寫死之後要串會員登入!					// 要去看會員功能寫甚麼字串
		List<ProductOrderVO> list = service.getByNumberOrder(number);
		request.setAttribute("orderlist", list);
		request.getRequestDispatcher("/front-end/order/selectByNumber.jsp").forward(request, response);
	}

}
