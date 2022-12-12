package tw.com.tibame.member.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.tibame.member.model.MemberService;
import tw.com.tibame.member.model.MemberVO;

@WebServlet("/front-end/member/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		MemberService memberSvc = new MemberService();

				// =============================新增資料=============================//
				if ("insert".equals(action)) {

					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					
					// 1.接收請求參數，輸入格式的錯誤處理
					String account = req.getParameter("account");
					String accountReg = "^[a-zA-Z]\\w{7,19}$";
					if (account == null || account.trim().length() == 0) {
						errorMsgs.add("請輸入帳號");
					} else if (!account.trim().matches(accountReg)) {
						errorMsgs.add("帳號開頭只能是英文字母，且長度為8~20之間");
					}	else if(memberSvc.findByAccount(account).size() > 0) {
						errorMsgs.add("此帳號已註冊過");
					}
					
					String email = req.getParameter("email");
					String emailReg = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
					if (email == null || email.trim().length() == 0) {
						errorMsgs.add("請填寫信箱");
					} else if (!email.trim().matches(emailReg)) {
						errorMsgs.add("請輸入正確信箱格式");
					} else if(memberSvc.findByEmail(email).size() > 0) {
						errorMsgs.add("此信箱已註冊過");
					}

					String password = req.getParameter("password").trim();
					if (password == null || password.trim().length() == 0) {
						errorMsgs.add("請輸入密碼");
					}
				
					String password2 = req.getParameter("password2").trim();
					if (!password2.equals(password)) {
						errorMsgs.add("密碼需一致");
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
					} else if(memberSvc.findByPhoneNumber(phoneNumber).size() > 0) {
						errorMsgs.add("此電話號碼已註冊過");
					}

//					String idNumber = req.getParameter("idNumber");
//					String idNumberReg = "^[A-Z][12]\\d{8}$";
//					if (idNumber == null || idNumber.trim().length() == 0) {
//						errorMsgs.add("請輸入身分證");
//					} else if (!idNumber.trim().matches(idNumberReg)) {
//						errorMsgs.add("請符合身分證格式");
//					}

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
						RequestDispatcher failureView = req.getRequestDispatcher("register.jsp");
						failureView.forward(req, res);
						return;
					}
					// 開始新增資料
//					MemberService memberSvc = new MemberService();
					memberVO = memberSvc.insertMember(account,password,email,birthday,name,phoneNumber);

					// 新增完成，準備轉交
					String url = "registerPass.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);

				}

	}
}