package com.event.model;

import java.util.List;

public interface OrderDAO_interface {
	public int insert();
	public int update();
	public List<OrderVO> selectByEventNumber();
	public List<OrderVO> number();
	public List<OrderVO> selectByDate();
	public List<OrderVO> selectByOrderType();
	public List<OrderVO> selectByNumber();
	
}
