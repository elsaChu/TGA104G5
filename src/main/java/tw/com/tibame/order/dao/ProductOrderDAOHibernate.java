package tw.com.tibame.order.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import tw.com.tibame.order.vo.ProductOrderVO;
@Repository
public class ProductOrderDAOHibernate implements ProductOrderDAO {

	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return this.session;
	}
	
	@Override
	public List<ProductOrderVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override // 會員中心 - 查詢所有訂單
	public List<ProductOrderVO> getByNumber(Integer number) {
		List<ProductOrderVO> result = new ArrayList<>();
		if (number != null) {
			Query<ProductOrderVO> query = getSession().createQuery("from ProductOrderVO where number =: number", ProductOrderVO.class); 
			query.setParameter("number", number);
			result = query.list();
			return result;
		}
		return null;
	}

	@Override // 會員中心 - 查詢單筆訂單
	public ProductOrderVO getPrimaryKey(Integer prodOrderNo) {
		if (prodOrderNo != null) {
			ProductOrderVO obj = this.getSession().get(ProductOrderVO.class, prodOrderNo);
			return obj;
		}
		return null;
	}

	@Override // 新增訂單                              //ProdOrderNo是auto_increment需要檢查其他欄位嗎?????
	public ProductOrderVO insert(ProductOrderVO productOrderVO) { 
		if (productOrderVO != null && productOrderVO.getProdOrderNo() != null) {
			ProductOrderVO temp = this.getSession().get(ProductOrderVO.class, productOrderVO.getProdOrderNo());
			if (temp == null) {
				this.getSession().persist(productOrderVO);
				return productOrderVO;
			}
		}
		return null;
	}

	@Override // 更新收件資訊及狀態
	public ProductOrderVO update(ProductOrderVO productOrderVO) {
		if (productOrderVO != null && productOrderVO.getProdOrderNo() != null) {
			ProductOrderVO temp = this.getSession().get(ProductOrderVO.class, productOrderVO);
			if (temp != null) {
				return (ProductOrderVO) this.getSession().merge(productOrderVO);
			}
		}
		return null;
	}

}
