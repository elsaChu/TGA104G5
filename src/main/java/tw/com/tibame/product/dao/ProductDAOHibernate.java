package tw.com.tibame.product.dao;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tibame.product.vo.ProductVO;

@Repository
@Transactional
public class ProductDAOHibernate implements ProductDAO {

	@PersistenceContext
	private Session session;

	@Override
	public Session getSession() {
		return this.session;
	}

	@Override
	public ProductVO findByProdNo(Integer prodNo) {
		if (prodNo != null && prodNo > 0) {
			ProductVO productVO = this.getSession().get(ProductVO.class, prodNo);
			return productVO;
		}
		return null;
	}

	@Override
	public ProductVO update(ProductVO productVO) {
		if (productVO != null && productVO.getProdNo() != null) {
			ProductVO temp = this.getSession().get(ProductVO.class, productVO.getProdNo());
			if (temp != null) {
				return (ProductVO) this.getSession().merge(productVO);
			}
		}
		return null;
	}

}
