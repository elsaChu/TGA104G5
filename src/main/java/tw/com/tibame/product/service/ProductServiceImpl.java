package tw.com.tibame.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tibame.product.dao.ProductDAO;
import tw.com.tibame.product.vo.ProductVO;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;

	public ProductServiceImpl(ProductDAO productDAO) {
		super();
		this.productDAO = productDAO;
	}

	@Override
	public ProductVO findByProdNo(Integer prodNo) {
		if(prodNo != null && prodNo > 0) {
			return productDAO.findByProdNo(prodNo);
		}
		return null;
	}

	@Override
	public ProductVO update(ProductVO productVO) {
		Integer prodNo = productVO.getProdNo();
		if (prodNo != null) {
			return productDAO.update(productVO);
		}
		return null;
	}
}
