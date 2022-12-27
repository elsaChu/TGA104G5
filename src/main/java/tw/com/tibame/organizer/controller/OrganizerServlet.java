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
		
		if ("save".equals(action)) { // 來自memberCenter的請求

			System.out.println("update");

			List<String> errorMsgs = new LinkedList<>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {

				OrganizerService organizerSvc = new OrganizerService();
				OrganizerVO organizerVO = (OrganizerVO) session.getAttribute("organizerVO"); // 表示已登入，取得staffVO物件
				System.out.println("### into update ### ");

				// 1.接收請求參數，輸入格式的錯誤處理
			
				String organizerName = req.getParameter("organizerName");
				if (organizerName == null || organizerName.trim().length() == 0) {
					errorMsgs.add("請填寫廠商名稱");
				}
				String windowName = req.getParameter("windowName");
				if (windowName == null || windowName.trim().length() == 0) {
					errorMsgs.add("請填寫窗口姓名");
				}
				String windowPhone = req.getParameter("windowPhone");
				if (windowPhone == null || windowPhone.trim().length() == 0) {
					errorMsgs.add("請填寫窗口連絡電話");
				}
				String windowEmail = req.getParameter("windowEmail");
				if (windowEmail == null || windowEmail.trim().length() == 0) {
					errorMsgs.add("請填寫窗口聯絡電子郵件");
				}
				String taxIDNumber = req.getParameter("taxIDNumber");
				if (taxIDNumber == null || taxIDNumber.trim().length() == 0) {
					errorMsgs.add("請填寫公司統編");
				}
				String boss = req.getParameter("boss");
				if (boss == null || boss.trim().length() == 0) {
					errorMsgs.add("請填寫公司負責人姓名");
				}
				String organizerPhone = req.getParameter("organizerPhone");
				if (organizerPhone == null || organizerPhone.trim().length() == 0) {
					errorMsgs.add("請填寫公司電話");
				}
				
				
				organizerVO.setOrganizerName(organizerName);
				organizerVO.setWindowName(windowName);
				organizerVO.setWindowPhone(windowPhone);
				organizerVO.setWindowEmail(windowEmail);
				organizerVO.setTaxIDNumber(taxIDNumber);
				organizerVO.setBoss(boss);
				organizerVO.setOrganizerPhone(organizerPhone);
				
				
				if (errorMsgs != null && !errorMsgs.isEmpty()) {
					req.getRequestDispatcher("organizerData.jsp").forward(req, res);
					return;
				}
				
				
				
		
				// 開始修改資料

				organizerVO = organizerSvc.update2(organizerVO);
				System.out.println("修改成功");
				// 新增完成，準備轉交
				String url = "organizerData.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
			
			} catch (Exception e) {
				System.out.println("update exception :" + e);
				RequestDispatcher failureView = req.getRequestDispatcher("loginStaff.jsp");
				failureView.forward(req, res);
			}
			
		}
	}
}
