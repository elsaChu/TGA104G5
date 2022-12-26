package tw.com.tibame.product.dao;

import org.hibernate.Session;

import tw.com.tibame.product.vo.ProductVO;

public interface ProductDAO {

	Session getSession();

	ProductVO findByProdNo(Integer prodNo);

	ProductVO update(ProductVO productVO);

}