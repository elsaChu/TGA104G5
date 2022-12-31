package tw.com.tibame.product.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import tw.com.tibame.organizer.model.OrganizerVO;
import tw.com.tibame.product.model.*;

@WebServlet("/ProductServlet")
@MultipartConfig

public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		HttpSession session = req.getSession();
		String action = req.getParameter("action");

		if ("insert".equals(action)) { // request from addProduct.jsp
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
			OrganizerVO organizer = (OrganizerVO)session.getAttribute("loginOrganizer");
			if(organizer != null) {
				try {
					on = organizer.getOrganizerNumber();
				} catch (NumberFormatException e) {
					errorMsgs.add("廠商請重新登入");
				}
			}
			
			String pn = req.getParameter("prodName");
			String prodNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9 )]{2,100}$";
			// regular-expression
			if (pn == null || pn.trim().length() == 0) {
				errorMsgs.add("請輸入商品名稱");
			} else if (!pn.trim().matches(prodNameReg)) {
				errorMsgs.add("商品名稱只能是中、英文字母、數字和空格, 且長度必需在2到100之間");
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

			Collection<Part> parts = req.getParts();
			for (Part part: parts) {
				if("prodIMG".equals(part.getName())) {
					String filename = part.getSubmittedFileName();
					if(filename == null || filename.length() == 0 || part.getContentType() == null) {
							errorMsgs.add("請選擇商品圖片");
					}
				}
			}

			String isPOn = req.getParameter("isPOn");
			Boolean ipo = Boolean.valueOf(isPOn);
			if (ipo == false) {
				errorMsgs.add("請選擇「是」方可上架商品");
			}

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
				req.setAttribute("ProductVO", prodVo);
				req.setAttribute("setProdDetails", prodVo.getProdDetails());
				RequestDispatcher failureView = req.getRequestDispatcher("/back-organizer-end/product/addProduct.jsp");
				failureView.forward(req, res);
				System.out.println("error: is empty");
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ProductService prodSvc = new ProductService();

			List<ProductImageVO> imglist = new ArrayList<ProductImageVO>();
			   for (Part part : parts) {
			    if("prodIMG".equals(part.getName())) {
			     String filename = part.getSubmittedFileName(); // 上傳檔案名稱
			     if (filename != null && filename.length() != 0 && part.getContentType() != null) {
			      ProductImageVO productImgvo = new ProductImageVO();
			      InputStream in = part.getInputStream();
			      byte[] buf = new byte[in.available()];
			      in.read(buf);
			      in.close();
			      productImgvo.setProdIMG(buf);
			      productImgvo.setProdIMGName(filename);
			      imglist.add(productImgvo);
			     }
			    }
			   }
	
//			byte[] prodimg = null;
//			if (filename != null && filename.length() != 0 && prodImg.getContentType() != null) {
//				InputStream in = prodImg.getInputStream();
//				prodimg = new byte[in.available()];
//				in.read(prodimg);
//				in.close();
//				ProductImageVO prodimgvo = new ProductImageVO();
//				prodimgvo.setProdIMGName(filename);
//				System.out.println("filename=" + filename);
//				System.out.println(prodimg.length);
//				prodimgvo.setProdIMG(prodimg);
//			}
			
			prodSvc.addProduct(en, on, pn, ps, up, psk, pdt, ipo, imglist);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-organizer-end/product/listAllProduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // forward to listAllProduct.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // request from listAllProduct.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer pdnb = Integer.valueOf(req.getParameter("prodNo"));

			/*************************** 2.開始查詢資料 ****************************************/
			ProductService prodSvc = new ProductService();
			Integer on = null;
			OrganizerVO organizer = (OrganizerVO)session.getAttribute("loginOrganizer");
			if(organizer != null) {
				try {
					on = organizer.getOrganizerNumber();
				} catch (NumberFormatException e) {
					errorMsgs.add("廠商請重新登入");
				}
			}
			ProductVO prodVo = prodSvc.getOneProduct(pdnb, on);
			String prodimglist= prodSvc.showImage(pdnb);
			System.out.println(prodimglist.length());
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("ProductVO", prodVo); // 資料庫取出的ProductVO物件,存入req
			req.setAttribute("prodimglist", prodimglist);
			String url = "/back-organizer-end/product/updateProduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// forward to updateProduct.jsp
			successView.forward(req, res);
		}
		
		if ("update".equals(action)) { // request from updateProduct.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer en = null;
			try {
				en = Integer.valueOf(req.getParameter("eventNumber").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("活動編號格式不正確");
			}

			Integer on = null;
			OrganizerVO organizer = (OrganizerVO)session.getAttribute("loginOrganizer");
			if(organizer != null) {
				try {
					on = organizer.getOrganizerNumber();
				} catch (NumberFormatException e) {
					errorMsgs.add("廠商請重新登入");
				}
			}

			String pn = req.getParameter("prodName");
			String prodNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			// regular-expression
			if (pn == null || pn.trim().length() == 0) {
				errorMsgs.add("請輸入商品名稱");
			} else if (!pn.trim().matches(prodNameReg)) {
				errorMsgs.add("商品名稱只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
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

			Integer prodno = Integer.valueOf(req.getParameter("ProdNo").trim());

			ProductVO prodVo = new ProductVO();
			prodVo.setEventNumber(en);
			prodVo.setOrganizerNumber(on);
			prodVo.setProdName(pn);
			prodVo.setProdSpec(ps);
			prodVo.setUnitPrice(up);
			prodVo.setProdStock(psk);
			prodVo.setProdDetails(pdt);
			prodVo.setIsPOn(ipo);
			prodVo.setProdNo(prodno);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("ProductVO", prodVo);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-organizer-end/product/updateProduct.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/
			ProductService prodSvc = new ProductService();
			
			List<ProductImageVO> imglist = new ArrayList<ProductImageVO>();
			Collection <Part> parts = req.getParts();
			   for (Part part : parts) {
			    if("prodIMG".equals(part.getName())) {
			     String filename = part.getSubmittedFileName(); // 上傳檔案名稱
			     if (filename != null && filename.length() != 0 && part.getContentType() != null) {
			      ProductImageVO productImgvo = new ProductImageVO();
			      InputStream in = part.getInputStream();
			      byte[] buf = new byte[in.available()];
			      in.read(buf);
			      in.close();
			      productImgvo.setProdIMG(buf);
			      productImgvo.setProdIMGName(filename);
			      productImgvo.setProdNo(prodno);
			      imglist.add(productImgvo);
			     }
			    }
			   }
			
			prodVo = prodSvc.updateProduct(en, on, pn, ps, up, psk, pdt, ipo, prodno, imglist);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("ProductVO", prodVo); // 資料庫update成功後,正確的的ProductVO物件,存入req
			String url = "/back-organizer-end/product/updateProductSucceed.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // forward to updateProductSucceed
			successView.forward(req, res);
		}

		if ("getOne_For_Display".equals(action)) { // (search by product number) request from selectProduct.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			System.out.println("error.Msgs");

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String str = req.getParameter("prodNo");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入/選擇商品編號");
			}
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-organizer-end/product/selectProduct.jsp");
				failureView.forward(req, res);
				return;
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
				return;
			}
			
			/*************************** 2.開始查詢資料 *****************************************/
			ProductService prodSvc = new ProductService();
			OrganizerVO organizer = (OrganizerVO)session.getAttribute("loginOrganizer");
			Integer on = null;
			if(organizer != null) {
				try {
					on = organizer.getOrganizerNumber();
				} catch (NumberFormatException e) {
					errorMsgs.add("廠商請重新登入");
				}
			}
			ProductVO prodVo = prodSvc.getOneProduct(prodNo, on);
			if (prodVo == null) {
				errorMsgs.add("此商品編號不存在，請重新輸入");
			}
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-organizer-end/product/selectProduct.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("ProductVO", prodVo); // 資料庫取出的ProductVO物件,存入req
			String url = "/back-organizer-end/product/listOneProduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // forward to listOneProduct.jsp
			successView.forward(req, res);
		}
		
//		if ("getOneProductName_For_Display".equals(action)) { // (search by product name) request from selectProduct.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			System.out.println("error.Msgs");
//
//			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//			String pn = req.getParameter("prodName");
//			String prodNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//			// regular-expression
//			if (pn == null || pn.trim().length() == 0) {
//				errorMsgs.add("請輸入/選擇商品名稱");
//			} else if (!pn.trim().matches(prodNameReg)) {
//				errorMsgs.add("商品名稱只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//			}
//			
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-organizer-end/product/selectProduct.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//			
//			String prodName = null;
//			try {
//				prodName = String.valueOf(pn);
//			} catch (Exception e) {
//				errorMsgs.add("商品編號格式不正確");
//			}
//			
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-organizer-end/product/selectProduct.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//			
//			/*************************** 2.開始查詢資料 *****************************************/
//			ProductService prodSvc = new ProductService();
//			ProductVO prodVo = prodSvc.getOneProductByProductName(prodName);
//			if (prodVo == null) {
//				errorMsgs.add("查無資料");
//			}
//			
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = req
//						.getRequestDispatcher("/back-organizer-end/product/selectProduct.jsp");
//				failureView.forward(req, res);
//				return;
//			}
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("ProductVO", prodVo); // 資料庫取出的ProductVO物件,存入req
//			String url = "/back-organizer-end/product/listOneProduct.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // forward to listOneProduct.jsp
//			successView.forward(req, res);
//		}

		if ("getOneProductName_For_Display".equals(action)) { // (search by product name) request from selectProduct.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			String pdname = req.getParameter("prodName");
			
			/*************************** 2.開始查詢資料 ****************************************/
			ProductService prodSvc = new ProductService();
			OrganizerVO organizer = (OrganizerVO)session.getAttribute("loginOrganizer");
			Integer on = null;
			if(organizer != null) {
				try {
					on = organizer.getOrganizerNumber();
				} catch (NumberFormatException e) {
					errorMsgs.add("廠商請重新登入");
				}
			}
			List<ProductVO> prodVo = prodSvc.findByProductName(pdname, on);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("ProductVO", prodVo); // 資料庫取出的ProductVO物件,存入req
			String url = "/back-organizer-end/product/listSelectedProduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// forward to listSelectedProduct.jsp
			successView.forward(req, res);
		}
	}
}