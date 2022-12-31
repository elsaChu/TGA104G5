package tw.com.tibame.product.model;

import java.sql.Connection;
import java.util.*;

public interface ProductDAO_interface {
	public int insert(ProductVO prodVo, List<ProductImageVO> imglist);

	public int update(ProductVO prodVo, List<ProductImageVO> imglist);

//	public int delete(Integer prodNo);

	public ProductVO findByPrimaryKey(Integer prodNo, Integer on);
	
//	public ProductVO findByProductName(String prodName);
	public List<ProductVO> findByProductName(String pdname, Integer on);

	public List<ProductVO> getAll();
	
	public List<ProductVO> getAllByOrganizer(Integer OrganizerNumber);

}