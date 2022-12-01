package com.product.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;
import com.product.model.ProductVO;


// @WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		
		Integer organizerNumber = null;
		organizerNumber = Integer.valueOf("1"); //等串起來要改

		String eventNumber = req.getParameter("eventNumber");
		String prodName = req.getParameter("prodName");
		String prodSpec = req.getParameter("prodSpec");
		String unitPrice = req.getParameter("unitPrice");
		String prodStock = req.getParameter("prodStock");
		String prodDetails = req.getParameter("prodDetails");
		String isPOn = req.getParameter("isPOn");
		
		/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		
		/***************************2.開始新增資料***************************************/
		ProductService prodSvc = new ProductService();
		ProductVO prodvo = new ProductVO();
		Integer up = Integer.valueOf(unitPrice);
		Integer ps = Integer.valueOf(prodStock);
		Boolean ipo = Boolean.valueOf(isPOn);
		
		prodvo.setOrganizerNumber(organizerNumber);
		prodvo.setEventNumber(organizerNumber);
		prodvo.setProdName(prodName);
		prodvo.setProdSpec(prodSpec);
		prodvo.setUnitPrice(up);
		prodvo.setProdStock(ps);
		prodvo.setProdDetails(prodDetails);
		prodvo.setIsPOn(ipo);
//		
		prodSvc.addProduct(prodvo);

//		/***************************3.新增完成,準備轉交(Send the Success view)***********/
//		String url = "/emp/listAllEmp.jsp";
//		RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//		successView.forward(req, res);	
		
		
		
	}

}
