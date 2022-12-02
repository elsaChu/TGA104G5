package com.product.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.product.model.ProductImageVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;

// @WebServlet("/AddProductServlet")
@MultipartConfig

public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);

		Integer organizerNumber = null;
		organizerNumber = Integer.valueOf("1"); // 等串起來要改

		/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		String eventNumber = req.getParameter("eventNumber");
		Integer en = null;
		try {
			en = Integer.valueOf(eventNumber);
		} catch (NumberFormatException e) {
			errorMsgs.add("請輸入活動編號");
		}

		String prodName = req.getParameter("prodName");
		if (prodName == null || prodName.trim().length() == 0) {
			errorMsgs.add("商品名稱: 請勿空白");
		}

		String prodSpec = req.getParameter("prodSpec");
		if (prodSpec == null || prodSpec.trim().length() == 0) {
			errorMsgs.add("商品規格: 請勿空白，若無特殊規格請填「無」");
		}

		String unitPrice = req.getParameter("unitPrice");
		Integer up = null;
		try {
			up = Integer.valueOf(unitPrice);
		} catch (NumberFormatException e) {
			errorMsgs.add("請輸入商品單價");
		}

		String prodStock = req.getParameter("prodStock");
		Integer ps = null;
		try {
			ps = Integer.valueOf(prodStock);
		} catch (NumberFormatException e) {
			errorMsgs.add("請輸入庫存數量");
		}

		String prodDetails = req.getParameter("prodDetails");

		String isPOn = req.getParameter("isPOn");
		Boolean ipo = Boolean.valueOf(isPOn);

		
		ProductVO prodvo = new ProductVO();
		prodvo.setOrganizerNumber(organizerNumber);
		prodvo.setEventNumber(en);
		prodvo.setProdName(prodName);
		prodvo.setProdSpec(prodSpec);
		prodvo.setUnitPrice(up);
		prodvo.setProdStock(ps);
		prodvo.setProdDetails(prodDetails);
		prodvo.setIsPOn(ipo);
		
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("prodvo", prodvo);
			RequestDispatcher failureView = req.getRequestDispatcher("/back-organizer-end/product/addProduct.jsp");
			failureView.forward(req, res);
			return;
		}
		
		/*************************** 2.開始新增資料 ***************************************/
		ProductService prodSvc = new ProductService();


		Part prodIMG = req.getPart("prodIMG");
		String Imgfilename = prodIMG.getSubmittedFileName();
		byte[] prodimg = null;
		if (Imgfilename != null && Imgfilename.length() != 0 && prodIMG.getContentType() != null) {
			String name = prodIMG.getName();
			InputStream in = prodIMG.getInputStream();
			prodimg = new byte[in.available()];
			in.read(prodimg);
			in.close();
		}
//		
		ProductImageVO prodimgvo = new ProductImageVO();
		prodimgvo.setProdIMGName(Imgfilename);
		prodimgvo.setProdIMG(prodimg);

		prodSvc.addProduct(prodvo);

//		/***************************3.新增完成,準備轉交(Send the Success view)***********/
//		String url = "/emp/listAllEmp.jsp";
//		RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//		successView.forward(req, res);	

	}

}
