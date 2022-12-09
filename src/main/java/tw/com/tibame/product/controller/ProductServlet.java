package tw.com.tibame.product.controller;

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

import tw.com.tibame.product.model.ProductImageVO;
import tw.com.tibame.product.model.ProductService;
import tw.com.tibame.product.model.ProductVO;

	@WebServlet("/ProductServlet")
	@MultipartConfig

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("text/html; charset=UTF-8");

		if ("insert".equals(action)) { // 來自addProduct.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage
			// view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer en = null;
			try {
				en = Integer.valueOf(req.getParameter("eventNumber").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("活動編號格式不正確");
			}

			Integer on = null;
			try {
				on = Integer.valueOf(req.getParameter("organizerNumber").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("廠商編號格式不正確");
			}

			String pn = req.getParameter("prodName");
			if (pn == null || pn.trim().length() == 0) {
				errorMsgs.add("請輸入商品名稱");
			}

			String ps = req.getParameter("prodSpec");
			if (ps == null || ps.trim().length() == 0) {
				errorMsgs.add("請輸入商品規格，若無規格請填「無」");
			}

			Integer up = null;
			try {
				up = Integer.valueOf(req.getParameter("unitPrice").trim());
			} catch (NumberFormatException e) {
				up = 0;
				errorMsgs.add("商品單價請填數字.");
			}

			Integer psk = null;
			try {
				psk = Integer.valueOf(req.getParameter("prodStock").trim());
			} catch (NumberFormatException e) {
				psk = 0;
				errorMsgs.add("庫存數量請填數字.");
			}

			String pdt = req.getParameter("prodDetails").trim();
			if (pdt == null || pdt.trim().length() == 0) {
				errorMsgs.add("商品詳情請勿空白");
			}

			String isPOn = req.getParameter("isPOn");
			Boolean ipo = Boolean.valueOf(isPOn);
			
			ProductVO prodVo = new ProductVO();
			prodVo.setEventNumber(en);
			prodVo.setOrganizerNumber(on);
			prodVo.setProdName(pn);
			prodVo.setProdSpec(ps);
			prodVo.setUnitPrice(up);
			prodVo.setProdStock(psk);
			prodVo.setProdDetails(pdt);
			prodVo.setIsPOn(ipo);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("ProductVO", prodVo); // 含有輸入格式錯誤的prodVo物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-organizer-end/product/addProduct.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ProductService prodSvc = new ProductService();
			prodVo = prodSvc.addProduct(en, on, pn, ps, up, psk, pdt, ipo);

			Part prodIMG = req.getPart("prodIMG");
			String Imgfilename = prodIMG.getSubmittedFileName();
			byte[] prodimg = null;
			if (Imgfilename != null && Imgfilename.length() != 0 && prodIMG.getContentType() != null) {
				String name = prodIMG.getName();
				InputStream in = prodIMG.getInputStream();
				prodimg = new byte[in.available()];
				in.read(prodimg);
				in.close();
			ProductImageVO prodimgvo = new ProductImageVO();
			prodimgvo.setProdIMGName(Imgfilename);
			prodimgvo.setProdIMG(prodimg);
			prodSvc.addProduct(en, on, pn, ps, up, psk, pdt, ipo);
			}

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-organizer-end/product/listAllProduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllProduct.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自updateProduct.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer en = null;
			try {
				en = Integer.valueOf(req.getParameter("eventNumber").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("活動編號格式不正確");
			}
			
			Integer on = null;
			try {
				on = Integer.valueOf(req.getParameter("organizerNumber").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("廠商編號格式不正確");
			}

			String pn = req.getParameter("prodName");
			if (pn == null || pn.trim().length() == 0) {
				errorMsgs.add("請輸入商品名稱");
			}

			String ps = req.getParameter("prodSpec");
			if (ps == null || ps.trim().length() == 0) {
				errorMsgs.add("請輸入商品規格，若無規格請填「無」");
			}

			Integer up = null;
			try {
				up = Integer.valueOf(req.getParameter("unitPrice").trim());
			} catch (NumberFormatException e) {
				up = 0;
				errorMsgs.add("商品單價請填數字.");
			}

			Integer psk = null;
			try {
				psk = Integer.valueOf(req.getParameter("prodStock").trim());
			} catch (NumberFormatException e) {
				psk = 0;
				errorMsgs.add("庫存數量請填數字.");
			}

			String pdt = req.getParameter("prodDetails").trim();
			if (pdt == null || pdt.trim().length() == 0) {
				errorMsgs.add("商品詳情請勿空白");
			}
			
			String isPOn = req.getParameter("isPOn");
			Boolean ipo = Boolean.valueOf(isPOn);

			ProductVO prodVo = new ProductVO();
			prodVo.setEventNumber(en);
			prodVo.setOrganizerNumber(on);
			prodVo.setProdName(pn);
			prodVo.setProdSpec(ps);
			prodVo.setUnitPrice(up);
			prodVo.setProdStock(psk);
			prodVo.setProdDetails(pdt);
			prodVo.setIsPOn(ipo);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("ProductVO", prodVo); // 含有輸入格式錯誤的prodVo物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-organizer-end/product/updateProduct.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/
			ProductService prodSvc = new ProductService();
			prodVo = prodSvc.updateProduct(en, on, pn, ps, up, psk, pdt, ipo);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("ProductVO", prodVo); // 資料庫update成功後,正確的的ProductVO物件,存入req
			String url = "/back-organizer-end/product/listOneProduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneProduct.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Display".equals(action)) { // 來自selectProduct.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String str = req.getParameter("prodNo");

			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入商品編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-organizer-end/product/selectProduct.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer prodNo = null;
			try {
				prodNo = Integer.valueOf(str);
			} catch (NumberFormatException e) {
				errorMsgs.add("商品編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-organizer-end/product/selectProduct.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ProductService prodSvc = new ProductService();
			ProductVO prodVo = prodSvc.getOneProduct(prodNo);
			if (prodVo == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-organizer-end/product/selectProduct.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("ProductVO", prodVo); // 資料庫取出的ProductVO物件,存入req
			String url = "/back-organizer-end/product/listOneProduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneProduct.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage
			// view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer pdnb = Integer.valueOf(req.getParameter("prodNo"));

			/*************************** 2.開始查詢資料 ****************************************/
			ProductService prodSvc = new ProductService();
			ProductVO prodVo = prodSvc.getOneProduct(pdnb);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("ProductVO", prodVo); // 資料庫取出的ProductVO物件,存入req
			String url = "/back-organizer-end/product/updateProduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}
	}
}