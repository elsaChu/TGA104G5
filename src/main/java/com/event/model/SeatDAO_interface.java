package com.event.model;

public interface SeatDAO_interface {
	public int insert();
	public int update();
	public int updateByseatType();
	public SeatVO eventNumber();
}
