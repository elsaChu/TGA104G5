package com.staff.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.staff.model.StaffService;
import com.staff.model.staffVO;

//@WebServlet("/insertStaffServlet")
public class InsertStaffServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//		要抓值出來，，所以用.gerParameter來抓，如果是checkbox又不一樣

		String staffName = request.getParameter("staffName");
		String staffAccount = request.getParameter("staffAccount");
		String staffPassword = request.getParameter("staffPassword");
		String[] permissionNumber = request.getParameterValues("permissionNumber");

		StaffService staffService = new StaffService();
		staffService.insertStaff(staffName, staffAccount, staffPassword);

	}

}
