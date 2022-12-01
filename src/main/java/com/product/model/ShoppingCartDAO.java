package com.product.model;

import java.util.List;

public interface ShoppingCartDAO {
	List<ShoppingCartVO> getAll();
	
	public ShoppingCartVO getPrimaryKey(Integer shoppingCartNo);
	public void insert(ShoppingCartVO shoppingCartVO);
	public void update(ShoppingCartVO shoppingCartVO);
	public boolean delete(Integer shoppingCartNo);
}
