package com.event.model;

import java.util.List;

public class EventTypeService {
	private EventTypeDAO_interface dao;
	public EventTypeService() {
		dao = new EventTypeJDBCDAO();
	}
	
	public List<EventTypeVO> selectAll(){
		
		return null;
	}
	
}
