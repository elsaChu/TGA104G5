package com.event.model;

import java.util.List;

public interface EventTypeDAO_interface {
	public int insert(EventTypeVO eventTypevo);
	public int update(EventTypeVO eventTypevo);
	public List<EventTypeVO> selectAll();
	public List<EventTypeVO> selectByEventNumber(int eventNumber);
	public List<EventTypeVO> selectTypeIsON();
}
