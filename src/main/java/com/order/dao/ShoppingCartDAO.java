package com.order.dao;

import java.util.List;

import com.order.vo.ShoppingCartVO;

public interface ShoppingCartDAO {
	List<ShoppingCartVO> getAll();
	
	public ShoppingCartVO getPrimaryKey(Integer shoppingCartNo);
	public void insert(ShoppingCartVO shoppingCartVO);
	public void update(ShoppingCartVO shoppingCartVO);
	public boolean delete(Integer shoppingCartNo);
}
