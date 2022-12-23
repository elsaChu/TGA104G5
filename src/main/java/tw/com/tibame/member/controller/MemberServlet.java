package tw.com.tibame.member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import tw.com.tibame.member.model.MailService;
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
		res.setCharacterEncoding("UTF-8");
		PrintWriter out = res.getWriter();
		String action = req.getParameter("action");
		HttpSession session = req.getSession();

				// =============================會員註冊=============================//
				if ("insert".equals(action)) {

					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					MemberService memberSvc = new MemberService();
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
					} else if(memberSvc.getEmail(email).size() > 0) {
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
						RequestDispatcher failureView = req.getRequestDispatcher("memberRegister.jsp");
						failureView.forward(req, res);
						return;
					}
					// 開始新增資料
//					MemberService memberSvc = new MemberService();
					memberVO = memberSvc.insertMember(account,password,email,birthday,name,phoneNumber);

					// 新增完成，準備轉交
					String url = "memberRegisterPass.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);

				}
				// =============================會員登入=============================//
				if ("loginForTickit".equals(action)) {
					List<String> errorMsgs = new LinkedList<>();
					req.setAttribute("errorMsgs", errorMsgs);

					try {

						MemberVO memberVO = new MemberVO();
						MemberService memberSvc = new MemberService();

						// 確認輸入的值
						String account = req.getParameter("account");
						if (account == null ||account.trim().length() == 0) {
							errorMsgs.add("請輸入帳號");
						}
						System.out.println("使用者輸入的帳號: " + account); // 使用者輸入的帳號


						String password = req.getParameter("password");
						if (password == null || password.trim().length() == 0) {
							errorMsgs.add("請輸入密碼");
						}
						System.out.println("使用者輸入的密碼: " + password); // 使用者輸入的密碼
						// 設定UserService傳入資訊

						memberVO = memberSvc.findByAccount2(account);
						String tickitPwd = memberSvc.pwdhash(password);

						if(memberVO == null) {
							errorMsgs.add("帳號或密碼錯誤");
						}

						String PasswordCheck = memberVO.getPassword();
						if (!PasswordCheck.equals(tickitPwd)) {
							errorMsgs.add("帳號或密碼錯誤");
						}

						// 確認資料有誤，印出錯誤資料並跳回原頁
						if (!errorMsgs.isEmpty()) {
							session.setAttribute("memberVO", memberVO);
							RequestDispatcher failureView = req.getRequestDispatcher("memberLogin.jsp");
							failureView.forward(req, res);
							return;
						}

						// 確認資料無誤，則設定
						session.setAttribute("memberVO", memberVO);
						System.out.println("be login...");
						String location = (String) session.getAttribute("location"); // 看看有無來源網頁

						if (location != null) { // 代表有來源網頁
							session.removeAttribute("location"); // 有來源網頁:重導至來源網頁
							res.sendRedirect(location);
							return;
						}
						System.out.println("正確無誤，導至會員中心");
						RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/memberCentre.jsp");
						successView.forward(req, res);
						return;

					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("錯誤了");
						RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/memberLogin.jsp");
						failureView.forward(req, res);
					}
				}
//					if (session.getAttribute("memberVO") == null) {
//					RequestDispatcher dispatcher = req.getRequestDispatcher("index.jsp");
//					dispatcher.forward(req, res);
//					return;
//					
//					} else if (session.getAttribute("memberVO") != null) {
//					RequestDispatcher dispatcher = req.getRequestDispatcher("memberCentre.jsp");
//					dispatcher.forward(req, res);
//					return;
//					}
		
				
				// ===================================================忘記密碼=========================================================//

				if ("forgotPasswordForTickit".equals(action)) {
					System.out.println("into memberForgotPassword");
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);

					try {
						MemberVO memberVO = new MemberVO();
						MemberService memberSvc = new MemberService();

						// 確認傳入的值
						String email = req.getParameter("email").trim();
						System.out.println("使用者輸入的Email:" + email);
						String emailReg = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
						// 錯誤處理
						if (email == null || (email.trim().length() == 0)) {
							errorMsgs.add("請輸入信箱");
						} else if (!email.trim().matches(emailReg)) {
							errorMsgs.add("請輸入正確信箱格式");
						} else if (memberSvc.getEmail(email).size() <= 0) {
							errorMsgs.add("無此信箱，請輸入註冊時信箱");
						}
						System.out.println("信箱通過驗證：" + email);

						memberVO = memberSvc.findByEmail(email);

						// 確認資料有誤，印出錯誤資料並跳回原頁
						if (!errorMsgs.isEmpty()) {
							session.setAttribute("memberVO", memberVO);
							RequestDispatcher failureView = req.getRequestDispatcher("memberForgotPassword.jsp");
							failureView.forward(req, res);
							return;
						}
						// 確認資料無誤，則設定
						MailService mailService = new MailService();
						String newPassword = mailService.genAuthCode();
						String subject = "忘記密碼重新設定";
						String messageText = "Hello!" + memberVO.getName() + "您的新密碼 ：「 " + newPassword + "  」";
						mailService.sendMail(email, subject, messageText);
						
						memberVO.setEmail(email); 
						memberVO.setPassword(newPassword); 
						System.out.println(newPassword);
						memberVO = memberSvc.updateMember(memberVO);
						System.out.println("forgotPasswordSuccess");

						// 設定成功，轉交回登入畫面
						String url = "memberForgotPasswordPass.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);
						
//						out.println("<meta http-equiv='refresh' content='0;URL=http://localhost:8080/TGA104G5/front-end/member/memberLogin.jsp>");
//						out.println("<script> alert('請至信箱查看您的新密碼!');</script>");

					} catch (Exception e) {
						session.setAttribute("memberVO", "");
						RequestDispatcher failureView = req.getRequestDispatcher("memberLogin.jsp");
						failureView.forward(req, res);
					}

				}
				
				// ===================================================修改資料=========================================================//
				
				if ("update".equals(action)) { // 來自memberCenter的請求

					System.out.println("update");

					Map<String, String> errorMsgs = new HashMap<String, String>();
					req.setAttribute("errorMsgs", errorMsgs);

					try {

						MemberService memberSvc = new MemberService();
						MemberVO memberVO = (MemberVO) session.getAttribute("memberVO"); // 表示已登入，取得memberVO物件
						System.out.println("### into update ### 1");

						// 1.接收請求參數，輸入格式的錯誤處理
					
						String name = req.getParameter("name");
						String nameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
						if (name == null || name.trim().length() == 0) {
							errorMsgs.put("name", "請填寫姓名");
						} else if (!name.trim().matches(nameReg)) { // 以下練習正則(規)表示式(regular-expression)
							errorMsgs.put("userName", "姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
						}	
						System.out.println("### into update ### 2");
						String email = req.getParameter("email");
						String emailReg = "^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$";
						if (email == null || email.trim().length() == 0) {
							errorMsgs.put("email", "請填寫信箱");
						} else if (!email.trim().matches(emailReg)) {
							errorMsgs.put("email", "請輸入正確信箱格式");
						} else if(memberVO.getEmail().equals(email)) {
							
						} else {
							errorMsgs.put("email", "此信箱已註冊過");
						}
						System.out.println("### into update ### 3");
						String phoneNumber = req.getParameter("phoneNumber");
						String phoneNumberReg = "^[0-9]{10}$";
						if (phoneNumber == null || phoneNumber.trim().length() == 0) {
							errorMsgs.put("phoneNumber", "請輸入電話號碼");
						} else if (!phoneNumber.trim().matches(phoneNumberReg)) {
							errorMsgs.put("phoneNumber", "電話號碼: 只能是數字 , 且長度必需是10");
						}
						System.out.println("### into update ### 4");
//						String IDNumber = req.getParameter("idNumber");
//						String IDNumberReg = "^[A-Z][12]\\d{8}$";
//						 if (!IDNumber.trim().matches(IDNumberReg)) {
//							errors.put("IDNumber" ,"請符合身分證格式");
//						}

						java.sql.Date birthday = null;
						try {
							birthday = java.sql.Date.valueOf(req.getParameter("birthday").trim());
						} catch (IllegalArgumentException e) {
							birthday = new java.sql.Date(System.currentTimeMillis());
							errorMsgs.put("birthday" ,"請輸入西元日期");
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
//						memberVO.setIdNumber(IDNumber);
						
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
				// ===================================================會員登出=========================================================//
				
				if("logout".equals(action)) {
					   try {
					    System.out.println("into logout");
					    session.removeAttribute("memberVO");
					   // session.removeAttribute("hotelVO");
					    
//					    HashMap<String, String> userInfoMap = new HashMap<String, String>();
//					    userInfoMap.put("check", "2"); // 表登出，回到首頁，右上方顯示「註冊」及「登入

					    session.invalidate(); // 清除用戶端與伺服器之間的會話資料
					    
//					    JSONObject obj = new JSONObject(userInfoMap);
//					    PrintWriter out = res.getWriter(); 已經宣告在doPost執行時
//					    out.println(obj);
//					    System.out.println(obj + "logout");
//					    return;
					 // 新增完成，準備轉交
						String url = "memberCentre.jsp";
						RequestDispatcher successView = req.getRequestDispatcher(url);
						successView.forward(req, res);

					    
					   }catch(Exception e) {
					    e.getStackTrace();
					    RequestDispatcher failureView = req.getRequestDispatcher("index.jsp");
					    failureView.forward(req, res);
					   }
					   
					  }
				
				// ===================================================確認狀態=========================================================//	
				if ("checkLogin".equals(action)) {
					   try {
					    MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
					  //  HotelVO hotelVO = (HotelVO) session.getAttribute("hotelVO");
					    
					    HashMap<String, String> userInfoMap = new HashMap<String, String>();
					    userInfoMap.put("check", "2");
					    
					    if(memberVO != null) {
					     userInfoMap.put("check", "1");
					     userInfoMap.put("url", "MemberCenter.jsp");
					    } 
					    
					    JSONObject obj = new JSONObject(userInfoMap);
					    out.println(obj);
					    System.out.println(obj);
					    
					   }catch(Exception e) {
					    e.printStackTrace();
					    RequestDispatcher failureView = req.getRequestDispatcher("index.html");
					    failureView.forward(req, res);
					   }
					  }
				

	// ===================================================會員查詢=========================================================//
					if ("search".equals(action)) {
					
					System.out.println("search member");
					
					List<String> errorMsgs = new LinkedList<String>();
					req.setAttribute("errorMsgs", errorMsgs);
					
					String str = req.getParameter("number");
					if (str == null || (str.trim().length() == 0)) {
						errorMsgs.add("請輸入會員編號");
						}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("memberList.jsp");
						failureView.forward(req, res);
						return;
					}
					Integer number = null;
					try {
						number = Integer.valueOf(str);
					} catch (Exception e) {
						errorMsgs.add("員工編號只能是數字");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("memberList.jsp");
						failureView.forward(req, res);
						return;
					}
					/***************************2.開始查詢資料*****************************************/
					MemberService memberSvc = new MemberService();
					MemberVO memberVO = memberSvc.findByPrimaryKey(number);
					if(memberVO == null) {
						errorMsgs.add("查無此資料");
					}
					if (!errorMsgs.isEmpty()) {
						RequestDispatcher failureView = req
								.getRequestDispatcher("memberList.jsp");
						failureView.forward(req, res);
						return;
					}	
					/***************************3.查詢完成,準備轉交(Send the Success view)*************/
					req.setAttribute("memberVO", memberVO); 
					String url = "memberGetOne.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url); 
					successView.forward(req, res);
					}
	}
}