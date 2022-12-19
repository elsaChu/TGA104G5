package tw.com.tibame.staff.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import tw.com.tibame.staff.model.StaffService;
import tw.com.tibame.staff.model.StaffVO;

@WebServlet("/StaffServlet")
public class StaffServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	@SuppressWarnings("unused")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		System.out.println("action: " + request.getParameter("action"));
		response.setContentType("text/html; charset=UTF-8");

//		if ("getOne_Staff_Display".equals(action)) { // 來自select_page.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			request.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			String str = request.getParameter("staffNumber");
//			if (str == null || (str.trim()).length() == 0) {
//				errorMsgs.add("請輸入員工編號");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = request.getRequestDispatcher("/emp/select_page.jsp");
//				failureView.forward(request, response);
//				return;// 程式中斷
//			}
//
//			Integer staffNumber = null;
//			try {
//				staffNumber = Integer.valueOf(str);
//			} catch (Exception e) {
//				errorMsgs.add("員工編號格式不正確");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = request.getRequestDispatcher("/back-staff-end/staff/selectPage.jsp");
//				failureView.forward(request, response);
//				return;// 程式中斷
//			}
//
//			/*************************** 2.開始查詢資料 *****************************************/
//			StaffService staffSvc = new StaffService();
//			StaffVO staffVO = staffSvc.getOneStaff(staffNumber);
//			if (staffVO == null) {
//				errorMsgs.add("查無資料");
//			}
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				RequestDispatcher failureView = request.getRequestDispatcher("/back-staff-end/staff/selectPage.jsp");
//				failureView.forward(request, response);
//				return;// 程式中斷
//			}
//
//			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
//			request.setAttribute("empVO", staffVO); // 資料庫取出的staffVO物件,存入request
//			String url = "/back-staff-end/staff/listOneStaff.jsp";
//			RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneStaff.jsp
//			successView.forward(request, response);
//		}

		if ("search".equals(action)) { // 來自listAllStaff.jsp的請求
			System.out.println("in search" + action);
			List<String> errorMsgs = new LinkedList<String>();
//			先把errorMsgs new出來，再裝進去request
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			String searchStaffNumber = request.getParameter("searchStaffNumber");
			StaffService staffSvc = new StaffService();
			
			Integer int_ssn = null;
			StaffVO staffVO = null;
			try {
				// 轉型 STR轉INT，用PARSEINT()
				int_ssn = Integer.valueOf(searchStaffNumber);
				staffVO = staffSvc.findByStaffNumber(int_ssn);
				if(staffVO.getStaffNumber() == null) {
					staffVO = null;
				}
			} catch (NumberFormatException e) {
				errorMsgs.add("未輸入員工編號");
			}
			
//			System.out.println("staffVO servlet="+staffVO.toString());
			if (staffVO == null && int_ssn != null) {
//				System.out.println("in null");
				errorMsgs.add("此員工編號不存在，請再確認一次");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request.getRequestDispatcher("/back-staff-end/staff/listAllStaff.jsp");
				failureView.forward(request, response);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			request.setAttribute("staffVO", staffVO); // 資料庫取出的staffVO物件,存入request
			String url = "/back-staff-end/staff/listOneStaff.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneStaff.jsp
			successView.forward(request, response);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllStaff.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer staffNumber = Integer.valueOf(request.getParameter("staffNumber"));
			System.out.println("getOne_For_Update @staffNumber:" + staffNumber);
			/*************************** 2.開始查詢資料 ****************************************/
			StaffService staffSvc = new StaffService();
			StaffVO staffVO = staffSvc.getOneStaff(staffNumber);
			System.out.println("getOne_For_Update @staffVO:" + staffVO);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			request.setAttribute("staffVO", staffVO);
			// 資料庫取出的staffVO物件,存入request
			String url = "/back-staff-end/staff/updateStaff.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			// 成功轉交 updateStaff.jsp
			System.out.println("getOne_For_Update @url:" + url);
			System.out.println("getOne_For_Update @request:" + request);
			System.out.println("getOne_For_Update @successView:" + successView);
			successView.forward(request, response);
		}

		if ("insert".equals(action)) { // 來自insertStaff.jsp的請求
			System.out.println("in insert");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			// 要抓值出來，，所以用.gerParameter來抓，如果是checkbox又不一樣
			String staffName = request.getParameter("staffName");
			String staffNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
			if (staffName == null || staffName.trim().length() == 0) {
				errorMsgs.add("員工姓名: 請勿空白");
			} else if (!staffName.trim().matches(staffNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("員工姓名: 只能是中、英文字母 , 且長度必需在2到10之間");
			}

			String staffAccount = request.getParameter("staffAccount");
			String staffAccountReg = "^[a-zA-Z_0-9]{6-20}$";
			if (staffAccount == null || staffAccount.trim().length() == 0) {
				errorMsgs.add("員工帳號: 請勿空白");
			}

			String staffPassword = request.getParameter("staffPassword");
			String staffPasswordReg = "^(?=.*d)(?=.*d)(?=.*[a-zA-Z])[da-zA-Z]{6-20}$";
			// 密碼中間同時含字母和數字且長度在6~20之間
			if (staffPassword == null || staffPassword.trim().length() == 0) {
				errorMsgs.add("員工密碼: 請勿空白");
			}

			String[] permissionNumber = request.getParameterValues("permissionNumber");
//			for(String a:permissionNumber) {
//				System.out.println(a);
//			}
			
			StaffVO staffVO = new StaffVO();
			staffVO.setStaffName(staffName);
			staffVO.setStaffAccount(staffAccount);
			staffVO.setStaffPassword(staffPassword);


			StaffService staffSvc = new StaffService();
			staffVO = staffSvc.getOneByAccount(staffAccount);
			if (staffVO != null) {
				errorMsgs.add("帳號或密碼重複，請再檢查一次帳號或密碼");

			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("staffVO", staffVO); // 含有輸入格式錯誤的staffVO物件,也存入request
				RequestDispatcher failureView = request.getRequestDispatcher("/back-staff-end/staff/insertStaff.jsp");
				failureView.forward(request, response);
				return;// 程式中斷
			}

			/*************************** 2.開始新增資料 *****************************************/
//			StaffService staffService = new StaffService();
			staffSvc.insertStaff(staffName, staffAccount, staffPassword,permissionNumber);
//			if (staffVO == null) {
//				errorMsgs.add("查無資料");
//			}
//			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request.getRequestDispatcher("/back-staff-end/staff/insertStaff.jsp");
				failureView.forward(request, response);
				return;// 程式中斷
			}

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-staff-end/staff/insertStaff.jsp";
			response.sendRedirect(request.getContextPath() + url);
			// 新增成功後轉交/back-staff-end/staff/insertStaff.jsp

		}

		if ("delete".equals(action)) { // 來自listAllStaff.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer staffNumber = Integer.valueOf(request.getParameter("staffNumber"));

			/*************************** 2.開始刪除資料 ***************************************/
			StaffService staffSvc = new StaffService();
			staffSvc.deleteStaff(staffNumber);

//			JOptionPane optionPane = new JOptionPane("勝敗乃兵家常事，大俠如果還需要此員工，請再新增一次！" );
//			JDialog dialog = optionPane.createDialog("Tips");
//			dialog.setAlwaysOnTop(true); // to show top of all other application
//			dialog.setVisible(true); // to visible the dialog

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back-staff-end/staff/listAllStaff.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(request, response);
		}

		if ("update".equals(action)) { // 來自updateStaff.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer staffNumber = Integer.valueOf(request.getParameter("staffNumber").trim());
			System.out.println("update @ staffNumber" + staffNumber);

			String staffName = request.getParameter("staffName");
			String staffNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (staffName == null || staffName.trim().length() == 0) {
				errorMsgs.add("員工姓名: 請勿空白");
			} else if (!staffName.trim().matches(staffNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("員工姓名: 只能是中、英文字母 , 且長度必需在2到10之間");
			}
			System.out.println("update @ staffName" + staffName);

			String staffAccount = request.getParameter("staffAccount");
			System.out.println("update @ staffAccount" + staffAccount);
			String staffAccountReg = "^[a-zA-Z_0-9]{6-20}$";
			if (staffAccount == null || staffAccount.trim().length() == 0) {
				errorMsgs.add("員工帳號: 請勿空白");
			}

			String staffPassword = request.getParameter("staffPassword");
			System.out.println("update @ staffPassword" + staffPassword);
			String staffPasswordReg = "^(?=.*d)(?=.*d)(?=.*[a-zA-Z])[da-zA-Z]{6-20}$";
			// 密碼中間同時含字母和數字且長度在6~20之間
			if (staffPassword == null || staffPassword.trim().length() == 0) {
				errorMsgs.add("員工密碼: 請勿空白");
			}

			StaffVO staffVO = new StaffVO();
			System.out.println("1" + staffVO);
			staffVO.setStaffNumber(staffNumber);
			staffVO.setStaffName(staffName);
			staffVO.setStaffAccount(staffAccount);
			staffVO.setStaffPassword(staffPassword);
			System.out.println("2" + staffVO);

			StaffService staffSvc = new StaffService();
			StaffVO staffVO2 = staffSvc.getOneByAccount(staffAccount);
			if (staffVO2 != null) {
				errorMsgs.add("帳號或密碼重複，請再檢查一次帳號或密碼");

			}

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("staffVO", staffVO); // 含有輸入格式錯誤的staffVO物件,也存入request
				System.out.println("3" + staffVO);
				RequestDispatcher failureView = request.getRequestDispatcher("/back-staff-end/staff/updateStaff.jsp");
				failureView.forward(request, response);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			StaffService staffSvc2 = new StaffService();
			System.out.println("update @ 2.開始修改資料");
			staffVO = staffSvc2.updateStaff(staffNumber, staffName, staffAccount, staffPassword);
			System.out.println("update @ updateStaff執行完畢");
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			request.setAttribute("staffVO", staffVO); // 資料庫update成功後,正確的的staffVO物件,存入request
			String url = "/back-staff-end/staff/listAllStaff.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			// 修改成功後,轉交/back-staff-end/staff/listAllStaff.jsp
			successView.forward(request, response);
		}

	}
}
