package tw.com.tibame.product.model;

import java.util.List;

public interface ProductOrderDAO_interface {
	
	public ProductOrderVO findByPrimaryKey(Integer prodOrderNo);
	
	public List<ProductOrderVO> getAll();
}
