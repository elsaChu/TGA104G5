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
//		==========================修改廠商資料==========================
		if ("save".equals(action)) { // 來自memberCenter的請求
			
			System.out.println("update Data");

			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				System.out.println("### 0### ");
				OrganizerService organizerSvc = new OrganizerService();
				OrganizerVO organizerVO = (OrganizerVO) session.getAttribute("loginOrganizer"); // 表示已登入，取得organizerVO物件
				System.out.println("### into update ### ");
				// 1.接收請求參數，輸入格式的錯誤處理
			
				String organizerName = req.getParameter("organizerName");
				if (organizerName == null || organizerName.trim().length() == 0) {
					errorMsgs.add("請填寫廠商名稱");
				}
				System.out.println("### 1### ");
				String windowName = req.getParameter("windowName");
				if (windowName == null || windowName.trim().length() == 0) {
					errorMsgs.add("請填寫窗口姓名");
				}
				System.out.println("### 2### ");
				String windowPhone = req.getParameter("windowPhone");
				if (windowPhone == null || windowPhone.trim().length() == 0) {
					errorMsgs.add("請填寫窗口連絡電話");
				}
				System.out.println("### 3### ");
				String windowEmail = req.getParameter("windowEmail");
				if (windowEmail == null || windowEmail.trim().length() == 0) {
					errorMsgs.add("請填寫窗口聯絡電子郵件");
				}
				System.out.println("### 4### ");
				String taxIDNumber = req.getParameter("taxIDNumber");
				if (taxIDNumber == null || taxIDNumber.trim().length() == 0) {
					errorMsgs.add("請填寫公司統編");
				}
				System.out.println("### 5### ");
				String boss = req.getParameter("boss");
				if (boss == null || boss.trim().length() == 0) {
					errorMsgs.add("請填寫公司負責人姓名");
				}
				System.out.println("### 6### ");
				String organizerPhone = req.getParameter("organizerPhone");
				if (organizerPhone == null || organizerPhone.trim().length() == 0) {
					errorMsgs.add("請填寫公司電話");
				}
				System.out.println("### 7### ");
				
				
				organizerVO.setOrganizerName(organizerName);
				organizerVO.setWindowName(windowName);
				organizerVO.setWindowPhone(windowPhone);
				organizerVO.setWindowEmail(windowEmail);
				organizerVO.setTaxIDNumber(taxIDNumber);
				organizerVO.setBoss(boss);
				organizerVO.setOrganizerPhone(organizerPhone);
				
				
				if (errorMsgs != null && !errorMsgs.isEmpty()) {
					req.getRequestDispatcher("/back-organizer-end/register-login/organizerData.jsp").forward(req, res);
					return;
				}
				
				
				
		
				// 開始修改資料

				organizerVO = organizerSvc.update2(organizerVO);
				System.out.println("修改成功");
				// 新增完成，準備轉交
				String url = "/back-organizer-end/register-login/organizerData.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			
			} catch (Exception e) {
				System.out.println("### 錯誤### ");
				System.out.println("update exception :" + e);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-organizer-end/register-login/OrganizerLogin1.jsp");
				failureView.forward(req, res);
			}
			
		}
		
//		==========================修改廠商銀行資料==========================
		if ("bank".equals(action)) { 
			
			System.out.println("update Bank");

			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				OrganizerService organizerSvc = new OrganizerService();
				OrganizerVO organizerVO = (OrganizerVO) session.getAttribute("loginOrganizer"); // 表示已登入，取得organizerVO物件
				System.out.println("### into update ### ");
				// 1.接收請求參數，輸入格式的錯誤處理
			
				String accountName = req.getParameter("accountName");
				if (accountName == null || accountName.trim().length() == 0) {
					errorMsgs.add("請填寫戶名");
				}
				System.out.println("### 1### ");
				String accountNumber = req.getParameter("accountNumber");
				if (accountNumber == null || accountNumber.trim().length() == 0) {
					errorMsgs.add("請填寫銀行帳號");
				}
				System.out.println("### 2### ");
				String bankCode = req.getParameter("bankCode");
				String bankCodeReg = "^[0-9]{1,4}$";
				if (bankCode == null || bankCode.trim().length() == 0) {
					errorMsgs.add("請填寫銀行代碼");
				}else if (!bankCode.trim().matches(bankCodeReg)) {
					errorMsgs.add("銀行代碼格式不符合規定!");
				}
				System.out.println("### 3### ");
				String bankName = req.getParameter("bankName");
				if (bankName == null || bankName.trim().length() == 0) {
					errorMsgs.add("請填寫銀行名稱");
				}
				System.out.println("### 4### ");
				
				
				organizerVO.setAccountName(accountName);
				organizerVO.setAccountNumber(accountNumber);
				organizerVO.setBankCode(bankCode);
				organizerVO.setBankName(bankName);

				
				if (errorMsgs != null && !errorMsgs.isEmpty()) {
					req.getRequestDispatcher("/back-organizer-end/register-login/organizerBank.jsp").forward(req, res);
					return;
				}
				
				
				
		
				// 開始修改資料

				organizerVO = organizerSvc.update2(organizerVO);
				System.out.println("修改成功");
				// 新增完成，準備轉交
				String url = "/back-organizer-end/register-login/organizerBank.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			
			} catch (Exception e) {
				System.out.println("### 錯誤### ");
				System.out.println("update exception :" + e);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-organizer-end/register-login/OrganizerLogin1.jsp");
				failureView.forward(req, res);
			}
			
		}
	}

}
