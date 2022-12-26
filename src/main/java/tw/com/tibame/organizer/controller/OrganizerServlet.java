package tw.com.tibame.organizer.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import tw.com.tibame.member.model.MemberService;
import tw.com.tibame.member.model.MemberVO;
import tw.com.tibame.organizer.model.OrganizerService;
import tw.com.tibame.organizer.model.OrganizerVO;

@WebServlet("/OrganizerServlet")
public class OrganizerServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		HttpSession session = req.getSession();
		
		if ("update".equals(action)) { // 來自memberCenter的請求

			System.out.println("update");

			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				MemberService memberSvc = new MemberService();
				MemberVO memberVO = (MemberVO) session.getAttribute("memberVO"); // 表示已登入，取得memberVO物件
				System.out.println("### into update ### 1");

				// 1.接收請求參數，輸入格式的錯誤處理
			
				String name = req.getParameter("name");
				String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (name == null || name.trim().length() == 0) {
					errorMsgs.add("請填寫姓名");
				} else if (!name.trim().matches(nameReg)) { // 以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
				}	
				System.out.println("### into update ### 2");
				String email = req.getParameter("email");
				String emailReg = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
				if (email == null || email.trim().length() == 0) {
					errorMsgs.add("請填寫信箱");
				} else if (!email.trim().matches(emailReg)) {
					errorMsgs.add("請輸入正確信箱格式");
				} else if(memberVO.getEmail().equals(email)) {
					
				} else {
					errorMsgs.add("此信箱已註冊過");
				}
				System.out.println("### into update ### 3");
				String phoneNumber = req.getParameter("phoneNumber");
				String phoneNumberReg = "^[0-9]{10}$";
				if (phoneNumber == null || phoneNumber.trim().length() == 0) {
					errorMsgs.add("請輸入電話號碼");
				} else if (!phoneNumber.trim().matches(phoneNumberReg)) {
					errorMsgs.add("電話號碼: 只能是數字 , 且長度必需是10");
				}
				System.out.println("### into update ### 4");
				String IDNumber = req.getParameter("IDNumber");
				String IDNumberReg = "^[A-Z][12]\\d{8}$";
				 if (!IDNumber.trim().matches(IDNumberReg)) {
					 errorMsgs.add("請符合身分證格式");
				}

				java.sql.Date birthday = null;
				try {
					birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
				} catch (IllegalArgumentException e) {
					birthday = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入西元日期");
				}
				System.out.println("### into update ### 5");
				
				
				String str= req.getParameter("subscription");
				boolean subscription = Boolean.parseBoolean(str);  
				
				System.out.println("### into update ### 6");
				
				memberVO.setEmail(email);
				memberVO.setBirthday(birthday);
				memberVO.setName(name);
				memberVO.setPhoneNumber(phoneNumber);
				memberVO.setSubscription(subscription);
				memberVO.setIDNumber(IDNumber);
				
				System.out.println("### into update ### 7");
				if (errorMsgs != null && !errorMsgs.isEmpty()) {
					req.getRequestDispatcher("memberCentre.jsp").forward(req, res);
					return;
				}
				
				System.out.println("### into update ### 8");
				
		
				// 開始修改資料

				memberVO = memberSvc.update(memberVO);
				System.out.println("修改成功");
				// 新增完成，準備轉交
				String url = "memberCentre.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			
			} catch (Exception e) {
				System.out.println("update exception :" + e);
				RequestDispatcher failureView = req.getRequestDispatcher("index.jsp");
				failureView.forward(req, res);
			}
			
		}
	}
}
