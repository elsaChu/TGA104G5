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
	
	public int addEvent(String alldata) { //no seat
		int re = addEvent(alldata,0,0,null);
		return re;
	}
	public int addEvent(String alldata,Integer xVal,Integer yVal,String[] seatIdListary) {
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
		try {
			JSONArray bimgJarr = event.getJSONArray("bigImg");
			byte[] bimgb = new byte[bimgJarr.length()];
			for(int i=0 ; i< bimgJarr.length() ; i++) {
				bimgb[i] = (byte)((int)bimgJarr.get(i));
			}
			eventvo.setBigImg(bimgb);
		}catch(JSONException e) {
			System.out.println("json no bigImg data please check");
		}
		
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
		eventvo.setSeatX(xVal);
		eventvo.setSeatY(yVal);
		//未開賣 販售中 已售罊(新增無需使用) 已結束
		Timestamp start=eventvo.getEventStartDate();
		Timestamp end=eventvo.getEventEndDate();
		Date toDay = new Date();

		if(start.compareTo(toDay) > 0) {
			eventvo.setEventType("未開賣");
		}else if(start.compareTo(toDay) <= 0 && end.compareTo(toDay) > 0) {
			eventvo.setEventType("販售中");
		}else {
			eventvo.setEventType("已結束");
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
			//未開賣 販售中 已售罊 已結束
			Timestamp startticket=ticketvo.getTicketStartTime();
			Timestamp endticket=ticketvo.getTicketEndTime();
			
			if(startticket.compareTo(toDay) > 0) {
				ticketvo.setTicketType("未開賣");
			}else if(startticket.compareTo(toDay) <=0 && endticket.compareTo(toDay) > 0) {
				ticketvo.setTicketType("販售中");
			}else {
				ticketvo.setTicketType("已結束");
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
		
		//seat table
		List<SeatVO> seatlist = new ArrayList<SeatVO>();
		if(eventvo.getNeedSeat()) {
			for(int i=0; i < seatIdListary.length ; i++) {
				SeatVO seatvo = new SeatVO();
				seatvo.setSeatNumber(i+1);
				seatvo.setSeatSet(Integer.valueOf(seatIdListary[i]));
				seatlist.add(seatvo);
			}
		}
		
		int re =dao.insert(eventvo,ticketlist,eventclasslist,seatlist);
		
		return re;
	}
}
