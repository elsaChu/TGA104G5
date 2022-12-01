package com.product.model;

import java.util.List;

public interface OrderDetailDAO {
	List<OrderDetailVO> getAll();
	
	public OrderDetailVO getPrimaryKey(Integer itemNo);
	public void insert(OrderDetailVO orderDetailVO);
	public void update(OrderDetailVO orderDetailVO);
	public boolean delete(Integer itemNo);
}
