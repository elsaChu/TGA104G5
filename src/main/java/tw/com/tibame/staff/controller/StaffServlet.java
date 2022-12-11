package tw.com.tibame.staff.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.com.tibame.staff.model.StaffService;
import tw.com.tibame.staff.model.staffVO;

//@WebServlet("/insertStaffServlet")
public class StaffServlet extends HttpServlet {
	
//	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
//		response.setContentType("text/html; charset=UTF-8");

		
		if ("getOne_Staff_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = request.getParameter("staffNumber");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				
				Integer staffNumber = null;
				try {
					staffNumber = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("員工編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/emp/select_page.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				StaffService staffSvc = new StaffService();
				staffVO staffVO = staffSvc.getOneStaff(staffNumber);
				if (staffVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-staff-end/staff/selectPage.jsp");
					failureView.forward(request, response);
					return;//程式中斷
				}
				
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				request.setAttribute("empVO", staffVO); // 資料庫取出的staffVO物件,存入request
				String url = "/back-staff-end/staff/listOneStaff.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url); // 成功轉交 listOneStaff.jsp
				successView.forward(request, response);
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllStaff.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer staffNumber = Integer.valueOf(request.getParameter("staffNumber"));
				
				/***************************2.開始查詢資料****************************************/
				StaffService staffSvc = new StaffService();
				staffVO staffVO = staffSvc.getOneStaff(staffNumber);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				request.setAttribute("staffVO", staffVO);
				// 資料庫取出的staffVO物件,存入request
				String url = "/back-staff-end/staff/updateStaff.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				// 成功轉交 updateStaff.jsp
				successView.forward(request, response);
		}
		
		
		
		
		if ("insert".equals(action)) { // 來自insertStaff.jsp的請求
			System.out.println("in insert");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//		要抓值出來，，所以用.gerParameter來抓，如果是checkbox又不一樣

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

			staffVO staffVO = new staffVO();
			staffVO.setStaffName(staffName);
			staffVO.setStaffAccount(staffAccount);
			staffVO.setStaffPassword(staffPassword);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				request.setAttribute("staffVO", staffVO); // 含有輸入格式錯誤的staffVO物件,也存入request
				RequestDispatcher failureView = request.getRequestDispatcher("/back-staff-end/staff/insertStaff.jsp");
				failureView.forward(request, response);
				return;// 程式中斷
			}

			/*************************** 2.開始新增資料 *****************************************/
			StaffService staffService = new StaffService();
			staffService.insertStaff(staffName, staffAccount, staffPassword);
			if (staffVO == null) {
				errorMsgs.add("查無資料");
			}
//			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = request.getRequestDispatcher("/back-staff-end/staff/insertStaff.jsp");
				failureView.forward(request, response);
				return;// 程式中斷
			}

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/main_frame/index_Staff.jsp";
			response.sendRedirect(request.getContextPath() + url);
			// 新增成功後轉交index_Staff.jsp

		}
		
		if ("delete".equals(action)) { // 來自listAllStaff.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer staffNumber = Integer.valueOf(request.getParameter("staffNumber"));
				
				/***************************2.開始刪除資料***************************************/
				StaffService staffSvc = new StaffService();
				staffSvc.deleteStaff(staffNumber);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/emp/listAllEmp.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(request, response);
		}
		
		
if ("update".equals(action)) { // 來自updateStaff.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			request.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer staffNumber = Integer.valueOf(request.getParameter("staffNumber").trim());
				
				String staffName = request.getParameter("staffName");
				String staffNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
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

				staffVO staffVO = new staffVO();
//				staffVO.setStaffNumber(staffNumber);
				staffVO.setStaffName(staffName);
				staffVO.setStaffAccount(staffAccount);
				staffVO.setStaffPassword(staffPassword);
			

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					request.setAttribute("staffVO", staffVO); // 含有輸入格式錯誤的empVO物件,也存入request
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-staff-end/staff/updateStaff.jsp");
					failureView.forward(request, response);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				StaffService staffSvc = new StaffService();
				staffVO = staffSvc.updateStaff(staffNumber, staffName, staffAccount, staffPassword);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				request.setAttribute("staffVO", staffVO); // 資料庫update成功後,正確的的staffVO物件,存入request
				String url = "/back-staff-end/staff/listOneStaff.jsp";
				RequestDispatcher successView = request.getRequestDispatcher(url);
				// 修改成功後,轉交/back-staff-end/staff/listAllStaff.jsp
				successView.forward(request, response);
		}
		
		

	}
}
