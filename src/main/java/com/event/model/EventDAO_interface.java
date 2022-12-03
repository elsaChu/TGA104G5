package com.event.model;

import java.util.List;

public interface EventDAO_interface {
	public int insert(EventVO eventVO);
	public List<EventVO> selectAll();
	public EventVO selectByeventNumber();
	public EventVO selectByeventName();
	public List<EventVO> selectByorganizerNumber();
	public List<EventVO> selectByeventType();
	public List<EventVO> selectByDate();
	public int update();
	
	
}
