package com.product.model;

public class ProductService {
	private ProductDAO_interface dao;
	public ProductService() {
		dao = new ProductJDBCDAO();
	}
	public void addProduct(ProductVO prodvo) {
	int re = dao.insert(prodvo);
		
//		return 0;
	}
}