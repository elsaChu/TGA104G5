package com.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.model.MemberDAO;
import com.member.model.MemberService;
import com.member.model.MemberVO;

public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res, MemberVO Member) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		
		// =============================單一查詢=============================//
				if ("getOne_For_Display".equals(action)) { // select_page.jsp請求

					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);

					// 1.接收請求參數，輸入格式的錯誤處理
					String str = req.getParameter("number");
					if (str == null || (str.trim()).length() == 0) {
						errorMsgs.add("請輸入會員編號");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/user/select_page.jsp");
						failureView.forward(req, res);
						return; // 程式中斷於此
					}

					Integer number = null;
					try {
						number = Integer.valueOf(str);
					} catch (Exception e) {
						errorMsgs.add("員工編號格式不正確");
					}

					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher("/user/select_page.jsp");
						failureView.forward(req, res);
						return;
					}

					// 2.查詢資料

					MemberService memberSvc = new MemberService();
					MemberVO memberVO = memberSvc.getOneMember(number);
					if (number == null) {
						errorMsgs.add("無此筆資料");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req.getRequestDispatcher(".jsp");
						failureView.forward(req, res);
						return;
					}

					// 3.查詢完成，準備轉交

					req.setAttribute("memberVO", memberVO); // 資料庫取出userVO物件，存入req
					String url = ".jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交給listOneUser.jsp
					successView.forward(req, res);
				}

				// =============================新增資料=============================//
				if ("insert".equals(action)) {

					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);

					// 1.接收請求參數，輸入格式的錯誤處理
					String email = req.getParameter("email");
					String emailReg = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
					if (email == null || email.trim().length() == 0) {
						errorMsgs.add("請填寫信箱");
					} else if (!email.trim().matches(emailReg)) {
						errorMsgs.add("請輸入正確信箱格式");
					}

					String password = req.getParameter("password").trim();
					if (password == null || password.trim().length() == 0) {
						errorMsgs.add("請輸入密碼");
					}

					String name = req.getParameter("name");
					String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					if (name == null || name.trim().length() == 0) {
						errorMsgs.add("請填寫姓名");
					} else if (!name.trim().matches(nameReg)) { // 以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
					}

					String phoneNumber = req.getParameter("phoneNumber");
					String phoneNumberReg = "^[0-9]{10}$";
					if (phoneNumber == null || phoneNumber.trim().length() == 0) {
						errorMsgs.add("請輸入電話號碼");
					} else if (!phoneNumber.trim().matches(phoneNumberReg)) {
						errorMsgs.add("電話號碼: 只能是數字 , 且長度必需是10");
					}

					String idNumber = req.getParameter("idNumber");
					String idNumberReg = "^[A-Z][12]\\d{8}$";
					if (idNumber == null || idNumber.trim().length() == 0) {
						errorMsgs.add("請輸入身分證");
					} else if (!idNumber.trim().matches(idNumberReg)) {
						errorMsgs.add("請符合身分證格式");
					}

					java.sql.Date birthday;
					try {
						birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
					} catch (IllegalArgumentException e) {
						birthday = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入西元日期");
					}

//					java.sql.Date userRegistration = null;
//					try {
//						userRegistration = java.sql.Date.valueOf(req.getParameter("userRegistration").trim());
//					} catch (IllegalArgumentException e) {
//						userRegistration = new java.sql.Date(System.currentTimeMillis());
//						errorMsgs.add("請輸入西元日期");
//					}
					MemberVO memberVO = new MemberVO();
					
					memberVO.setAccount(account);
					memberVO.setPassword(password);
					memberVO.setEmail(email);
					memberVO.setBirthday(birthday);
					memberVO.setName(name);
					memberVO.setPhoneNumber(phoneNumber);

					
					if (!errorMsgs.isEmpty()) {
						req.setAttribute("memberVO", memberVO);
						RequestDispatcher failureView = req.getRequestDispatcher(".jsp");
						failureView.forward(req, res);
						return;
					}
					// 開始新增資料
					MemberService memberSvc = new MemberService();
					memberVO = memberSvc.addMember(password,email,birthday,name,phoneNumber,idNumber);

					// 新增完成，準備轉交
					String url = ".jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交到總表listAllUser.jsp
					successView.forward(req, res);

				}

				if ("getOne_For_Update".equals(action)) { // 來自listAllUser.jsp的請求
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);

					// 1.接收請求參數
					Integer number = Integer.valueOf(req.getParameter("number"));

					// 2.開始查詢資料
					MemberService memberSvc = new MemberService();
					MemberVO memberVO = memberSvc.getOneMember(number);

					// 3. 查詢完成，準備轉交
					req.setAttribute("memberVO", memberVO);
					String url = ".jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_user_input.jsp
					successView.forward(req, res);
				}

				// =============================修改資料=============================//
				if ("update".equals(action)) { // 來自update_user_input.jsp的請求

					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					// 1.接收請求參數，輸入格式的錯誤處理
					Integer number = Integer.valueOf(req.getParameter("number").trim());

					String email = req.getParameter("email");
					String emailReg = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
					if (email == null || email.trim().length() == 0) {
						errorMsgs.add("請填寫信箱");
					} else if (!email.trim().matches(emailReg)) {
						errorMsgs.add("請輸入正確信箱格式");
					}

					String password = req.getParameter("password");
					String comfirmPassword = req.getParameter("password");
					if (password == null || password.trim().length() == 0) {
						errorMsgs.add("請輸入密碼");
					} else if (!password.equals(comfirmPassword)) {
						errorMsgs.add("兩次密碼需一致");
					}

					String name = req.getParameter("name");
					String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
					if (name == null || name.trim().length() == 0) {
						errorMsgs.add("請填寫姓名");
					} else if (!name.trim().matches(nameReg)) { // 以下練習正則(規)表示式(regular-expression)
						errorMsgs.add("姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
					}

					String phoneNumber = req.getParameter("phoneNumber");
					String phoneNumberReg = "^[0-9]{10}$";
					if (phoneNumber== null || phoneNumber.trim().length() == 0) {
						errorMsgs.add("請輸入電話號碼");
					} else if (!phoneNumber.trim().matches(phoneNumberReg)) {
						errorMsgs.add("電話號碼: 只能是數字 , 且長度必需是10");
					}

					String idNumber = req.getParameter("idNumber");
					String idNumberReg = "^[A-Z][12]\\d{8}$";
					if (idNumber == null || idNumber.trim().length() == 0) {
						errorMsgs.add("請輸入身分證");
					} else if (!idNumber.trim().matches(idNumberReg)) {
						errorMsgs.add("請符合身分證格式");
					}

					java.sql.Date birthday = null;
					try {
						birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
					} catch (IllegalArgumentException e) {
						birthday = new java.sql.Date(System.currentTimeMillis());
						errorMsgs.add("請輸入西元日期");
					}

//					java.sql.Timestamp userRegistration = null;
//					try {
//						userRegistration = java.sql.Timestamp.valueOf(req.getParameter("userRegistration").trim());
//					} catch (IllegalArgumentException e) {
//						userRegistration = new java.sql.Timestamp(System.currentTimeMillis());
//						errorMsgs.add("請輸入西元日期");
//					}
	
					// 開始修改資料
					MemberService memberSvc = new MemberService();
					MemberVO = memberSvc.updateMember(number,password,email,idNumberReg, birthday,name,phoneNumber,null, null, null, idNumber, idNumberReg, idNumberReg, idNumberReg);


					// 修改完成，準備轉交
					req.setAttribute("memberVO", MemberVO);
					String url = "/user/listOneUser.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
					successView.forward(req, res);
				}

				// =============================刪除資料=============================//
				if ("delete".equals(action)) {

					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);

					// 1.接收請求參數
					Integer number = Integer.valueOf(req.getParameter("number"));

					// 2.開始刪除資料
					MemberService memberSvc = new MemberService();
					memberSvc.deleteMember(number);

					// 3.刪除完成，準備轉交
					String url = ".jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後，轉交回送出刪除的來源網頁
					successView.forward(req, res);
				}
	}
}