package com.order.dao;

import java.util.List;

import com.order.vo.ProductOrderVO;

public interface ProductOrderDAO {
	List<ProductOrderVO> getAll();
	
	public List<ProductOrderVO> getByNumber(Integer number);		// 會員中心 - 查詢單筆訂單
	public ProductOrderVO getPrimaryKey(Integer prodOrderNo);		// 會員中心 - 查詢所有訂單
	public ProductOrderVO insert(ProductOrderVO productOrderVO);			
	public ProductOrderVO update(ProductOrderVO productOrderVO);	// 會員中心 - 修改收件資訊
//	public boolean delete(Integer prodOrderNo);						// 用不到(?)
}
