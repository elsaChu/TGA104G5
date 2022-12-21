package tw.com.tibame.product.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tibame.product.vo.ViewProductVO;

@Repository
@Transactional
public class ViewProductDAOHibernate implements ViewProductDAO {
	
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return this.session;
	}
	

	@Override
	public List<ViewProductVO> findAll() {
		List<ViewProductVO> result = new ArrayList<>();
		
		Query<ViewProductVO> query = getSession().createQuery("from ViewProductVO", ViewProductVO.class);
		result = query.getResultList();
		if(result != null && !result.isEmpty()) {
			return result;
		} else {
			return null;
		}
	}

	@Override	// 依活動分類篩選已上架商品
	public List<ViewProductVO> findByEventType(ViewProductVO vo) {
		List<ViewProductVO> result = new ArrayList<>();
		String eventType = vo.getEventType();
		if (eventType != null) {
			Query<ViewProductVO> query = getSession().createQuery(
					"from ViewProductVO where eventType = :eventType and isPOn = :isPOn", ViewProductVO.class); 
			query.setParameter("eventType", eventType);
			query.setParameter("isPOn", vo.getIsPOn());
			result = query.list();
			return result;
		}
		return null;
	}
	
	@Override	// 已上架所有商品
	public List<ViewProductVO> findProductLaunch(boolean isPOn) {
		List<ViewProductVO> result = new ArrayList<>();
		if (isPOn == true) {
			Query<ViewProductVO> query = getSession().createQuery("from ViewProductVO where isPOn = :isPOn", ViewProductVO.class); 
			query.setParameter("isPOn", isPOn);
			result = query.list();
			return result;
		}
		return null;
	}
	
	@Override
	public ViewProductVO findByPrimaryKey(Integer prodNo) {
		if (prodNo != null) {
			ViewProductVO viewProductVO = this.getSession().get(ViewProductVO.class, prodNo);
			return viewProductVO;
		}
		return null;
	}

}
