package com.product.model;

import java.util.List;

public interface ProductOrderDAO {
	List<ProductOrderVO> getAll();
	
	public ProductImageVO getPrimaryKey(Integer prodOrderNo);
	public void insert(ProductOrderVO productOrderVO);
	public void update(ProductOrderVO productOrderVO);
	public boolean delete(Integer prodOrderNo);
}
