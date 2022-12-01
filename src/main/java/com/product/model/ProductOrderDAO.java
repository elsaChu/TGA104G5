package com.product.model;

import java.util.List;

public interface ProductOrderDAO {
	List<ProductOrderVO> getAll();
	
	public List<ProductOrderVO> getByNumber(Integer number);		// 會員中心 - 查詢單筆訂單
	public ProductOrderVO getPrimaryKey(Integer prodOrderNo);	// 會員中心 - 查詢所有訂單
	public void insert(ProductOrderVO productOrderVO);			
	public void update(ProductOrderVO productOrderVO);			// 會員中心 - 修改收件資訊
//	public boolean delete(Integer prodOrderNo);					// 用不到(?)
}
