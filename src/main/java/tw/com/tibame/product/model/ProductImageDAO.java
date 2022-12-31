package tw.com.tibame.product.model;

import java.sql.Connection;
import java.util.List;

public interface ProductImageDAO {
	List<ProductImageVO> getAll();
	public ProductImageVO getPrimaryKey(Integer prodIMGID);
	public void insert(ProductImageVO productImageVO, Connection con);
	public void update(ProductImageVO productImageVO);
//	public boolean delete(Integer prodIMGID);
	public List<ProductImageVO> selectProdImage(Integer prodNo);
	public boolean delete(Integer prodNo, Connection con);
}
