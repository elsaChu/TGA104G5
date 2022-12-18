package tw.com.tibame.event.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Base64;
import java.util.Enumeration;
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
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.hibernate.internal.build.AllowSysOut;
import org.json.JSONArray;
import org.json.JSONObject;

import tw.com.tibame.event.model.EventClassService;
import tw.com.tibame.event.model.EventClassVO;
import tw.com.tibame.event.model.EventService;
import tw.com.tibame.event.model.EventVO;
import tw.com.tibame.event.model.TicketService;
import tw.com.tibame.event.model.TicketVO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/UpdateEventServlet")
@MultipartConfig
public class UpdateEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doPost(req,res);
//	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		
		HttpSession session = req.getSession();
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		
		String action = req.getParameter("action");
		System.out.println("action="+action);
		if("getOne_updateEvent".equals(action)) { //要看連結過來的頁面寫什麼action
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/***************************1.接收請求參數****************************************/
			Integer eventNumber = Integer.valueOf(req.getParameter("eventNumber"));
			/***************************2.開始查詢資料****************************************/
			EventService eventSvc = new EventService();
			EventVO eventvo = eventSvc.getOneEvent(eventNumber);
			System.out.println("select"+eventvo.toString());
			Base64.Encoder encoder = Base64.getEncoder();
			String bigImg64 = "data:image/jpeg;base64,"+encoder.encodeToString(eventvo.getBigImg());
//			System.out.println(bigImg64);
			String smallImg64 = "";
			if(eventvo.getSmallImg() != null) {
				smallImg64 = "data:image/jpeg;base64,"+encoder.encodeToString(eventvo.getSmallImg());
			}
//			Map map = new HashMap();
//			map.put("bigImg", eventvo.getBigImg());
//			map.put("smallImg", eventvo.getSmallImg());
			
			//event class table
			EventClassService eventclassSvc = new EventClassService();
			List<EventClassVO> evclassList = eventclassSvc.selectByeventNumber(eventNumber);
//			String[] evclassValue = new String[evclassList.size()];
			System.out.println("CLASS LIST="+evclassList.toString());
			
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("evclassJSON", gson.toJson(evclassList));
			req.setAttribute("bigImg64", bigImg64);
			req.setAttribute("smallImg64", smallImg64);
			session.setAttribute("up_eventvo", eventvo);// 資料庫取出的eventvo物件,存入req
//			session.setAttribute("eventvoOLD", eventvo);
//			session.setAttribute("getOnePIC", map);
			String url = "/back-organizer-end/event/updateEvent1.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}
		
		if("update_page1".equals(action)) {
			System.out.println("in update_page1");
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			EventVO eventvo = (EventVO)session.getAttribute("up_eventvo");
			byte[] bigImgOld = eventvo.getBigImg();
			byte[] smallImgOld = eventvo.getSmallImg();
//			EventVO eventvoOLD = (EventVO)session.getAttribute("eventvoOLD");
//			byte[] bigImgOld = eventvoOLD.getBigImg();
//			byte[] smallImgOld = eventvoOLD.getSmallImg();
//			Map pic_session = (Map)session.getAttribute("getOnePIC");
//			byte[] bigImgOld = (byte[])pic_session.get("bigImg");
//			byte[] smallImgOld = (byte[])pic_session.get("smallImg");
//			
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
//				System.out.println("page1 date=" + eventStartDate);
			} catch (IllegalArgumentException e) {
				errorMsgs.add("請輸入日期!");
			}
			
			String eventenddatestr=req.getParameter("end_date").trim();
			java.sql.Timestamp eventEndDate = null;
			try {
				eventEndDate = java.sql.Timestamp.valueOf(eventenddatestr);
				if(eventStartDate !=null) {
					if(eventEndDate.compareTo(eventStartDate) == -1) {
						errorMsgs.add("日期區間不對!");
					}
				}
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
				if(bigImgOld.length < 0) {
					errorMsgs.add("請選擇封面圖片");
				}
			}
			
			String isON = req.getParameter("isON");
			Boolean isON_ = false;
			if ("on".equals(isON)) {
				isON_ = true;
			}
			
			String needSeat = req.getParameter("needSeat");
//			System.out.println(needSeat);
			Boolean needSeat_ = false;
			if ("on".equals(needSeat)) {
				needSeat_ = true;
			}
					
			String[] eventClassNumber = req.getParameterValues("eventClassNumber");
			if (eventClassNumber == null || eventClassNumber.length == 0) {
				errorMsgs.add("活動分類至少選擇一項");
			}

			//要回傳到新增頁面的資料內容
//			EventVO eventvo= new EventVO();
//			eventvo.setOrganizerNumber(organizerNumber);
			eventvo.setEventName(eventName);
			if(eventStartDate == null) {
				req.setAttribute("eventStartDate", "");
			}else {
				eventvo.setEventStartDate(eventStartDate);
				req.setAttribute("eventStartDate", eventStartDate);
			}
			
			if(eventEndDate == null) {
				req.setAttribute("eventEndDate", "");
			}else {
				eventvo.setEventEndDate(eventEndDate);
				req.setAttribute("eventEndDate", eventEndDate);
			}
			
			if(peopleNumber == null) {
				req.setAttribute("peopleNumber", "");
			}else {
				eventvo.setPeopleNumber(peopleNumber);
				req.setAttribute("peopleNumber", peopleNumber);
			}
			
			eventvo.setEventPlace(eventPlace);
			eventvo.setEventP2(eventP2);
			eventvo.setEventSummary(eventSummary);
			eventvo.setEventDescribe(eventDescribe);

			// Send the use back to the form, if there were errors
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("eventvo", eventvo);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-organizer-end/event/updateEvent1.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.第一頁資料帶至下一頁***************************************/
			//set other eventvo data
//			eventvo.setEventNumber(Integer.valueOf(eventvoOLD.getEventNumber()));
			eventvo.setIsON(isON_);
			eventvo.setNeedSeat(needSeat_);
//			eventvo.setCollectType(eventvoOLD.getCollectType());
//			eventvo.setBanner(eventvoOLD.getBanner());
//			eventvo.setFocus(eventvoOLD.getFocus());

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
//			if(smallimg == null) {
//				smallimg =smallImgOld;
//			}
			eventvo.setSmallImg(smallimg);
			
			//bigImg
			byte[] bigimg = null;
			if(filename != null && filename.length() !=0 && bigImg.getContentType() != null) {
				String name = bigImg.getName();
				InputStream in = bigImg.getInputStream();
				bigimg = new byte[in.available()];
				System.out.println("bigimg bytes = "+bigimg);
				in.read(bigimg);
				in.close();
			}
			if(bigimg != null) {
				eventvo.setBigImg(bigimg);
			}
			
			
			//set data go to page2
//			JSONArray eventCN = new JSONArray(Arrays.asList(eventClassNumber));
//			session.removeAttribute("getOnePIC");
			Map<String,Object> map=new HashMap<>();
			map.put("page1",eventvo);
			map.put("event_class", eventClassNumber);
			map.put("peopleNumber",eventvo.getPeopleNumber());
	
			System.out.println(filename);
			System.out.println(smallImgfilename);
			session.setAttribute("up_adddata", map);
//			System.out.println(eventenddatestr);
			session.setAttribute("up_maxDate", eventenddatestr);
			System.out.println(eventvo.toString());
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-organizer-end/event/updateEvent2.jsp");
			failureView.forward(req, res);
			return;
		}
		
		//select page2 tickets
		if("selectTickets".equals(action)) {
			/***************************1.接收請求參數****************************************/
			Integer eventNumber = Integer.valueOf(req.getParameter("eventNumber"));
			/***************************2.開始查詢資料****************************************/
			TicketService ticketSvc = new TicketService();
			List<TicketVO> ticketList= ticketSvc.selectTicketByEventNumber(eventNumber);
			System.out.println("select="+ticketList.toString());
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
	        Map<String,Object> result = new HashMap<>();
	        result.put("success", false);
	        if(ticketList.size() > 0) {
	        	result.put("success", true);
	        	result.put("tickets",gson.toJson(ticketList));
	        }else {
	        	result.put("noticket", "沒有票種資料");
	        }
	        PrintWriter out = res.getWriter();
	        out.print(gson.toJson(result));
	        out.flush();
	        return;
		}
		
		//update_page2
		if("update_page2".equals(action)) {
			System.out.println("in update_page2");
			Map map = (Map)session.getAttribute("up_adddata");
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
			String tickets = req.getParameter("tickets");
			System.out.println(tickets);
			
			res.setContentType("application/json");
	        
	        Map<String,Object> result = new HashMap<>();
	        result.put("success", false);
	        
			JSONObject json = new JSONObject(tickets);
			//get page2 data
			JSONArray jarr =new JSONArray();
			jarr = json.getJSONArray("ticket");
			
			JSONArray errticket =new JSONArray();
			boolean haserr = false;
			if(jarr.length() != 0) {
				
				int totalTicket = 0;
				for(int i = 0 ; i< jarr.length() ; i++) {
					JSONObject jsonO = jarr.getJSONObject(i);

					String ticket_name=jsonO.get("ticket_name").toString();
					if(ticket_name == null || ticket_name.trim().length() == 0) {
						result.put("ticket_namemsg","票種名稱: 請勿空白");
						haserr =true;
						
					}
//					result.put("ticket_name",ticket_name);
					
					Integer ticket_price = null;
					try {
						ticket_price = Integer.valueOf(jsonO.get("ticket_price").toString());
					} catch (NumberFormatException e) {
						result.put("ticket_pricemsg","價格:只能是數字");
						haserr =true;
						
					}
//					result.put("ticket_price",ticket_price);
							
					Integer ticket_quantity= null;
					try {
						ticket_quantity = Integer.valueOf(jsonO.get("ticket_quantity").toString());
						totalTicket = totalTicket + ticket_quantity;
					} catch (NumberFormatException e) {
						result.put("ticket_quantitymsg","數量:只能是數字");
						haserr =true;
					}
//					result.put("ticket_quantity",ticket_price);
					
					java.sql.Timestamp start_date= null;
					try {
						start_date = java.sql.Timestamp.valueOf(jsonO.get("start_date").toString());
						System.out.println("up_page2 date=" + start_date);
					} catch (IllegalArgumentException e) {
						System.out.println("go start exception");
						result.put("datemsg","請輸入日期!");
						haserr =true;
					}
//					result.put("start_date",start_date);
					
					java.sql.Timestamp end_date= null;
					try {
						end_date = java.sql.Timestamp.valueOf(jsonO.get("end_date").toString());
						System.out.println("up_page2 enddate=" + end_date);
						if(start_date !=null) {
							if(end_date.compareTo(start_date) == -1) {
								result.put("end_datemsgeday","日期區間不對!");
								haserr =true;
							}
							String maxDateStr =(String)session.getAttribute("up_maxDate");
//							System.out.println("str="+maxDateStr);
							Timestamp maxDate =Timestamp.valueOf(maxDateStr);
//							System.out.println("date="+maxDate);
//							System.out.println("end cop max ="+end_date.compareTo(maxDate));
							if(end_date.compareTo(maxDate) == 1) {
								result.put("max_dateerr","售票結束日期不可超過活動結束日期!");
								haserr =true;
							}
						}
					} catch (IllegalArgumentException e) {
						System.out.println("go end exception");
						result.put("datemsg","請輸入日期!");
						haserr =true;
					}
//					result.put("end_date",end_date);
					
					Integer ticket_min= null;
					try {
						ticket_min=Integer.valueOf(jsonO.get("ticket_min").toString());
					} catch (NumberFormatException e) {
						result.put("ticket_minmsg","最小限制販售數量:只能是數字");
						haserr =true;
					}
//					result.put("ticket_min",ticket_min);
					
					Integer ticket_max= null;
					try {
						ticket_max=Integer.valueOf(jsonO.get("ticket_max").toString());
						if(ticket_min != null) {
							if(ticket_min > ticket_max) {
								result.put("ticket_numerr","最小販售數量不可大於最大販售數量");
								haserr =true;
							}
						}
					} catch (NumberFormatException e) {
						result.put("ticket_maxmsg","最大限制販售數量:只能是數字");
						haserr =true;
					}
					if(haserr) {
						errticket.put(jsonO.getInt("record"));
					}
					haserr =false;
				}
				if(errticket.length() > 0) {
					result.put("errRecord",errticket);
				}
				
				
//				System.out.println(session.getAttribute("adddata").toString());
				
				EventVO eventvo =(EventVO)map.get("page1");
//				System.out.println(eventvo.getPeopleNumber());
				int pn=eventvo.getPeopleNumber();
				if(totalTicket > pn) {
					result.put("ticketTotalOut","票劵總數不可大於活動人數,活動人數:" + pn);
				}
			}else {
				result.put("noticket","沒有票種資料");
			}
			
			// Send the use back to the form, if there were errors
			if(result.size() > 1) {
				System.out.println(result.toString());
				System.out.println("up_page2 go error "+result.size());
			}else {
		        result.put("success", true);
		        session.setAttribute("up_tickets", tickets);
		        
		        EventVO eventvo =(EventVO)map.get("page1");
		        boolean needSeat =eventvo.getNeedSeat();
				/***************************2.判斷有無劃位 有資料帶至下一頁面 否 新增資料***************************************/	
				if(needSeat) {
					System.out.println("need seat="+needSeat);
					result.put("needSeat", needSeat);
					
					System.out.println("up_adddata="+((Map)session.getAttribute("up_adddata")).toString());
					System.out.println(session.getAttribute("up_tickets").toString());
				}else {
					System.out.println("not need="+needSeat);
					
					System.out.println("up_adddata="+((Map)session.getAttribute("up_adddata")).toString());
					System.out.println(session.getAttribute("up_tickets").toString());
					//get page1 data
					Map eventMap =(Map)session.getAttribute("up_adddata");
			
					String[] event_class=(String[]) eventMap.get("event_class");
					//get page2 data
//					String tickets=session.getAttribute("tickets").toString();
					
					// call service insert data
					EventService eventSvc = new EventService();
					int eventInsertOK = eventSvc.updateEvent(eventvo,event_class,tickets);	
					result.put("insertOK",eventInsertOK);
					session.removeAttribute("up_adddata");
					session.removeAttribute("up_tickets");
				}
			}
		    PrintWriter out = res.getWriter();
	        out.print(gson.toJson(result));
	        out.flush();
	        return;
		}
	}
}
