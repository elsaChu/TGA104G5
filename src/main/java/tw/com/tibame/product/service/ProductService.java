package tw.com.tibame.product.service;

import tw.com.tibame.product.vo.ProductVO;

public interface ProductService {

	ProductVO findByProdNo(Integer prodNo);

	ProductVO update(ProductVO productVO);

}