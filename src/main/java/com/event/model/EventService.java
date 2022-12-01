package com.event.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

public class EventService {
	private EventDAO_interface dao;
	public EventService() {
		dao = new EventJDBCDAO();
	}
	
	public int addEvent(EventVO eventvo) {
		//total 17 column
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
		
		int re =dao.insert(eventvo);
		
		return re;
	}
	
	
	//廢棄改使用vo傳入
//	public void addEvent(String eventName, Integer organizerNumber, LocalDateTime start_date_par, LocalDateTime end_date_par, Integer peopleNumber_par, String eventPlace,
//			String eventP2, String eventSummary, String eventDescribe) {
//		
//		EventVO eventvo = new EventVO();
//
//		eventvo.setOrganizerNumber(1); //test
//		eventvo.setEventName(eventName);
//		eventvo.setEventStartDate(start_date_par);
//		eventvo.setEventEndDate(end_date_par);
//		eventvo.setPeopleNumber(peopleNumber_par);
//		eventvo.setEventPlace(eventPlace);
//		eventvo.setEventP2(eventP2);
//		eventvo.setEventSummary(eventSummary);
//		eventvo.setEventDescribe(eventDescribe);
//		
//		//判斷活動狀態
//		dao.insert(eventvo);
//		
//	}
}
