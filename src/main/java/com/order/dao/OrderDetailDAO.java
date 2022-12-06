package com.order.dao;

import java.util.List;

import com.order.vo.OrderDetailVO;

public interface OrderDetailDAO {
	List<OrderDetailVO> getAll();
	
	public OrderDetailVO getPrimaryKey(Integer itemNo);					
	public List<OrderDetailVO> getByProdOrderNo(Integer prodOrderNo);	// 會員中心:查詢單筆訂單
	public void insert(OrderDetailVO orderDetailVO);					
	public void update(OrderDetailVO orderDetailVO);
	public boolean delete(Integer itemNo);
}
