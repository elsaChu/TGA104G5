package com.product.model;

import java.util.List;

public interface ProductImageDAO {
	List<ProductImageVO> getAll();
	
	public ProductImageVO getPrimaryKey(Integer prodIMGID);
	public void insert(ProductImageVO productImageVO);
	public void update(ProductImageVO productImageVO);
	public boolean delete(Integer prodIMGID);
}
