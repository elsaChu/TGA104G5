package tw.com.tibame.product.model;

import java.util.*;

public interface ProductDAO_interface {
	public int insert(ProductVO prodVo, ProductImageVO prodimgvo);

	public int update(ProductVO prodVo);

//	public int delete(Integer prodNo);

	public ProductVO findByPrimaryKey(Integer prodNo);
	
//	public ProductVO findByProductName(String prodName);
	public List<ProductVO> findByProductName(String pdname);

	public List<ProductVO> getAll();
}