package com.product.model;

import java.util.List;

public interface ProductDAO_interface {
	public OrderDetailVO getPrimaryKey(Integer prodNo);
	public int insert(ProductVO productVO);
	public void update(ProductVO productVO);
	public boolean delete(Integer prodNo);
	public List<ProductVO> getAll();
}
