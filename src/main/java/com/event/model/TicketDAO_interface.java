package com.event.model;

import java.util.List;

public interface TicketDAO_interface {
	public int insert();
	public int update();
	public List<TicketVO> selectByeventNumber();
}
