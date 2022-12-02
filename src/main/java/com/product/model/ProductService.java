package com.product.model;

public class ProductService {
	private ProductDAO_interface dao;
	public ProductService() {
		dao = new ProductJDBCDAO();
	}
	public ProductVO addProduct(ProductVO prodvo) {
//		ProductVO prodvo = new ProductVO();
		dao.insert(prodvo);
		return prodvo;
	}
	
	public ProductVO updateProduct(Integer prodNo, Integer eventNumber, Integer organizerNumber, 
			String prodName, String prodSpec, Integer unitPrice, Integer prodStock, String prodDetails, Float prodScore, Boolean isPOn) {
		ProductVO prodvo = new ProductVO();
		
		
		
		return prodvo;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}