package tw.com.tibame.product.model;

import java.util.List;

public class ProductOrderService {

	private ProductOrderDAO_interface dao;
	
	public ProductOrderService() {
		dao = new ProductOrderJdbcDAO();
	}
	
	public ProductOrderVO getOneProductOrder(Integer prodOrderNo) {
		return dao.findByPrimaryKey(prodOrderNo);
	}
	
	public List<ProductOrderVO> getAll() {
		return dao.getAll();
	}
}
