package tw.com.tibame.product.model;

import java.util.*;

public interface ProductDAO_interface {
	public int insert(ProductVO prodVo);

	public int update(ProductVO prodVo);

	public int delete(Integer prodNo);

	public ProductVO findByPrimaryKey(Integer prodNo);

	public List<ProductVO> getAll();
}