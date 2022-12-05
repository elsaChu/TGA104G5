package com.event.model;

import java.util.List;

public interface EventClassDAO_interface {
	public List<EventClassVO> selectByeventNumber();
	public int update();
	public int insert();
}
