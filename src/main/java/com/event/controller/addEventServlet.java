package com.event.controller;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.event.model.EventClassService;
import com.event.model.EventService;
import com.event.model.EventTypeService;
import com.event.model.EventVO;
import com.google.gson.Gson;


//@WebServlet("/addEmpServlet")
@MultipartConfig
public class addEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		
		String action = req.getParameter("action");
		if("page1".equals(action)) {
			System.out.println("in page1");
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
				
			Integer organizerNumber = null;
			try {
				organizerNumber = Integer.valueOf("1"); //等串起來要改
			} catch (NumberFormatException e) {
				errorMsgs.add("未登入請重新登入");
			}
				
			String eventName = req.getParameter("eventName");
			if (eventName == null || eventName.trim().length() == 0) {
				errorMsgs.add("活動名稱: 請勿空白");
			}
			
			java.sql.Timestamp eventStartDate = null;
			try {
				eventStartDate = java.sql.Timestamp.valueOf(req.getParameter("start_date").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.add("請輸入日期!");
			}
			
			java.sql.Timestamp eventEndDate = null;
			try {
				eventEndDate = java.sql.Timestamp.valueOf(req.getParameter("end_date").trim());
			} catch (IllegalArgumentException e) {
				errorMsgs.add("請輸入日期!");
			}
			
			Integer peopleNumber = null;
			try {
				peopleNumber = Integer.valueOf(req.getParameter("peopleNumber").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("活動人數： 只能是數字");
			}

			String eventPlace = req.getParameter("eventPlace");
			if (eventPlace == null || eventPlace.trim().length() == 0) {
				errorMsgs.add("活動地點： 請勿空白");
			}
			
			String eventP2 = req.getParameter("eventP2");
			if (eventPlace == null || eventPlace.trim().length() == 0) {
				errorMsgs.add("活動地址： 請勿空白");
			}
			
			String eventSummary = req.getParameter("eventSummary");
			if (eventPlace == null || eventPlace.trim().length() == 0) {
				errorMsgs.add("活動簡介： 請勿空白");
			}
			
			String eventDescribe = req.getParameter("eventDescribe");
			if (eventPlace == null || eventPlace.trim().length() == 0) {
				errorMsgs.add("活動描述： 請勿空白");
			}
			
			Part bigImg = req.getPart("bigImg");
			String filename = bigImg.getSubmittedFileName();
			if(filename == null || filename.length() ==0 || bigImg.getContentType() == null) {
				errorMsgs.add("請選擇封面圖片");
			}
			
			Boolean collectType = Boolean.valueOf(req.getParameter("collectType"));
			if (collectType == null) {
				errorMsgs.add("請選擇收集資料類型");
			}
			
			String isON = req.getParameter("isON");
			Boolean isON_ = false;
			if (isON != null) {
				isON_ = true;
			}
			
			String needSeat = req.getParameter("needSeat");
			Boolean needSeat_ = false;
			Integer seatX = 0;
			Integer seatY = 0;
			if (needSeat != null) {
				needSeat_ = true;
				
				
//				try {
//					seatX = Integer.valueOf(req.getParameter("seatX").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("只能是數字");
//				}
//				
//				
//				try {
//					seatY = Integer.valueOf(req.getParameter("seatY").trim());
//				} catch (NumberFormatException e) {
//					errorMsgs.add("只能是數字");
//				}
			}
			
			String[] eventClassNumber = req.getParameterValues("eventClassNumber");
			if (eventClassNumber == null || eventClassNumber.length == 0) {
				errorMsgs.add("活動分類至少選擇一項");
			}
			
			//要回傳到新增頁面的資料內容
			EventVO eventvo= new EventVO();
			eventvo.setOrganizerNumber(organizerNumber);
			eventvo.setEventName(eventName);
			eventvo.setEventStartDate(eventStartDate);
			eventvo.setEventEndDate(eventEndDate);
			eventvo.setPeopleNumber(peopleNumber);
			eventvo.setEventPlace(eventPlace);
			eventvo.setEventP2(eventP2);
			eventvo.setEventSummary(eventSummary);
			eventvo.setEventDescribe(eventDescribe);
			eventvo.setCollectType(collectType);
//			eventvo.setSeatX(seatX);
//			eventvo.setSeatY(seatY);

			
			// Send the use back to the form, if there were errors
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("eventvo", eventvo);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-organizer-end/event/addEvent1.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.開始新增資料***************************************/
			eventvo.setIsON(isON_);
			eventvo.setNeedSeat(needSeat_);

			
			Part smallImg = req.getPart("smallImg");
			String smallImgfilename = smallImg.getSubmittedFileName();
			byte[] smallimg = null;
			if(smallImgfilename != null && smallImgfilename.length() !=0 && smallImg.getContentType() != null) {
				String name = smallImg.getName();
				InputStream in = smallImg.getInputStream();
				smallimg = new byte[in.available()];
				in.read(smallimg);
				in.close();
			}
			eventvo.setSmallImg(smallimg);
			
			//bigImg
			byte[] bigimg = null;
			if(filename != null && filename.length() !=0 && bigImg.getContentType() != null) {
				String name = bigImg.getName();
				InputStream in = bigImg.getInputStream();
				bigimg = new byte[in.available()];
				in.read(bigimg);
				in.close();
			}	
			eventvo.setBigImg(bigimg);
			
			
			Map<String,Object> map=new HashMap<>();
			map.put("input1",eventvo);
			Gson gson=new Gson();
			
			
			System.out.println(filename);
			System.out.println(smallImgfilename);
			// call service
//			EventService eventSvc = new EventService();

//			int eventInsertOK = eventSvc.addEvent(eventvo);
			//if event insert success
			//call EventClassService insert

			
			req.setAttribute("adddata", gson.toJson(map));
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-organizer-end/event/addEvent2.jsp");
			failureView.forward(req, res);
			return;
		}
		
		//page2
		if("page2".equals(action)) {
			System.out.println("in page2");
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String alldata = req.getParameter("alldata");
			//1.get page2 data and out error context
			//2.if isOn true go page3 else go insert and return organizer page.
			
			System.out.println(alldata);
		}
		
	}
}
