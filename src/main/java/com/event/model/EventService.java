package com.event.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EventService {
	private EventDAO_interface dao;
	public EventService() {
		dao = new EventJDBCDAO();
	}
	
	public int addEventNoSeat(String alldata) {
		JSONObject eventAllData = new JSONObject(alldata);
		
		//event table
		JSONObject event = eventAllData.getJSONObject("page1");
		EventVO eventvo = new EventVO();
		eventvo.setOrganizerNumber(Integer.valueOf(event.get("organizerNumber").toString()));
		eventvo.setEventName(event.get("eventName").toString());
		System.out.println("service date=" + event.get("eventStartDate").toString());
		
		eventvo.setEventStartDate(java.sql.Timestamp.valueOf(event.get("eventStartDate").toString()));
		eventvo.setEventEndDate(java.sql.Timestamp.valueOf(event.get("eventEndDate").toString()));
		eventvo.setPeopleNumber(Integer.valueOf(event.get("peopleNumber").toString()));
		eventvo.setEventPlace(event.getString("eventPlace"));
		eventvo.setEventP2(event.getString("eventP2"));
		eventvo.setEventSummary(event.getString("eventSummary"));
		eventvo.setEventDescribe("now no value"); //need debug
		eventvo.setCollectType(false);
		JSONArray bimgJarr = event.getJSONArray("bigImg");
		byte[] bimgb = new byte[bimgJarr.length()];
		for(int i=0 ; i< bimgJarr.length() ; i++) {
			bimgb[i] = (byte)((int)bimgJarr.get(i));
		}
		eventvo.setBigImg(bimgb);
		
		try {
			JSONArray smallImg =event.getJSONArray("smallImg");
			byte[] smallimgb = new byte[smallImg.length()];
			for(int i=0 ; i< smallImg.length() ; i++) {
				smallimgb[i] = (byte)((int)smallImg.get(i));
			}
			eventvo.setSmallImg(smallimgb);
		}catch(JSONException e) {
			
		}
		eventvo.setIsON(event.getBoolean("isON"));
		eventvo.setNeedSeat(event.getBoolean("needSeat"));
		eventvo.setSeatX(0);
		eventvo.setSeatY(0);
		//未開賣 販售中 已售罊 已結束
		Timestamp start=eventvo.getEventStartDate();
		java.sql.Date startdate = new java.sql.Date(start.getTime());
		java.sql.Time starttime = new java.sql.Time(start.getTime());
		
		Date toDay = new Date();
		java.sql.Date todate = new java.sql.Date(toDay.getTime());
		java.sql.Time totime = new java.sql.Time(toDay.getTime());
			
		if(startdate.compareTo(todate) > 0) {
			eventvo.setEventType("未開賣");
		}else if(startdate.compareTo(todate) == 0) {
			if(starttime.compareTo(totime) > 0) {
				eventvo.setEventType("未開賣");
			}else {
				eventvo.setEventType("販賣中");
			}
		}else {
			System.out.println("please check time start:" + start + " today:" + toDay);
			return 0;
		}
		//ticket table
		List<TicketVO> ticketlist = new ArrayList<TicketVO>();
		JSONArray ticket = eventAllData.getJSONArray("ticket");
		for(int i =0 ; i < ticket.length() ; i++) {
			JSONObject oneticket = ticket.getJSONObject(i);
			TicketVO ticketvo = new TicketVO();
			ticketvo.setTicketName(oneticket.getString("ticket_name"));
			ticketvo.setPrice(Integer.valueOf(oneticket.getString("ticket_price")));
			ticketvo.setTicketQuantity(Integer.valueOf(oneticket.getString("ticket_quantity")));
			ticketvo.setTicketStartTime(Timestamp.valueOf(oneticket.getString("start_date")));
			ticketvo.setTicketEndTime(Timestamp.valueOf(oneticket.getString("end_date")));
			ticketvo.setTicketMIN(Integer.valueOf(oneticket.getString("ticket_min")));
			ticketvo.setTicketMAX(Integer.valueOf(oneticket.getString("ticket_max")));
			ticketvo.setLimitTicket(true);
			//未開賣 販售中 已售罊
			Timestamp startticket=ticketvo.getTicketStartTime();
			java.sql.Date startticketdate = new java.sql.Date(startticket.getTime());
			
			Timestamp endticket=ticketvo.getTicketEndTime();
			java.sql.Date endticketdate = new java.sql.Date(endticket.getTime());
			
			if(startticketdate.compareTo(todate) > 0) {
				ticketvo.setTicketType("未開賣");
			}else if(startticketdate.compareTo(todate) <=0 && endticketdate.compareTo(todate) > 0) {
				ticketvo.setTicketType("販售中");
			}else {
				System.out.println("please check time start:" + start + " today:" + toDay);
				System.out.println("please check time end:" + start + " today:" + toDay);
				return 0;
			}
			ticketlist.add(ticketvo);
		}
		
		//event class table
		JSONArray event_class = eventAllData.getJSONObject("event_class").getJSONArray("myArrayList");
		List<EventClassVO> eventclasslist = new ArrayList<EventClassVO>();
		for(int i=0; i < event_class.length(); i++) {
			EventClassVO eventclassvo = new EventClassVO();
			eventclassvo.setEventClassNumber(Integer.valueOf(event_class.getString(i)));
			eventclasslist.add(eventclassvo);
		}
		
		
		
		int re =dao.insert(eventvo,ticketlist,eventclasslist);
		
		return re;
	}
}
