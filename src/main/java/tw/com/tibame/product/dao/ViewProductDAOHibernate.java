package tw.com.tibame.product.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tibame.product.vo.FilterProducts;
import tw.com.tibame.product.vo.ProductImage;
import tw.com.tibame.product.vo.ViewProductVO;

@Repository
@Transactional
public class ViewProductDAOHibernate implements ViewProductDAO {

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return this.session;
	}
	
	@Resource(name = "redisTemplate")
	RedisTemplate<Integer, Set<ViewProductVO>> redisTemplate;

	@Override
	public List<ViewProductVO> findAll() {
		List<ViewProductVO> result = new ArrayList<>();

		Query<ViewProductVO> query = getSession().createQuery("from ViewProductVO", ViewProductVO.class);
		result = query.getResultList();
		if (result != null && !result.isEmpty()) {
			return result;
		} else {
			return null;
		}
	}
	
	@Override
	public List<String> findAllEventType() {
		List<String> result = new ArrayList<>();
		Query<String> query = getSession()
				.createQuery("select eventClassName from EventType "
						+ "where eventClassState = true", String.class);
		result = query.list();
		return result;
	}

	@Override // 依活動分類篩選已上架商品，最後上架的顯示在前面
	public List<FilterProducts> findByEventClassName(String eventClassName) {
		List<FilterProducts> result = new ArrayList<>();
		
		if (eventClassName != null) {
			Query<FilterProducts> query = getSession().createQuery(
					  "from FilterProducts "
					  + "where eventClassName = :eventClassName "
					  + "	and isPOn = :isPOn "
					  + "order by prodNo desc", FilterProducts.class);
			query.setParameter("eventClassName", eventClassName);
			query.setParameter("isPOn", true);
			result = query.list();
			return result;
		}
		return null;
	}

	@Override // 已上架所有商品，最後上架的顯示在前面
	public List<ViewProductVO> findProductLaunch(boolean isPOn) {
		List<ViewProductVO> result = new ArrayList<>();
		if (isPOn == true) {
			Query<ViewProductVO> query = getSession().createQuery(
					"from ViewProductVO where isPOn = :isPOn  order by prodNo desc", ViewProductVO.class);
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

	@Override
	public List<ProductImage> findAllPic() {
		List<ProductImage> result = new ArrayList<>();

		Query<ProductImage> query = getSession().createQuery("from ProductImage", ProductImage.class);
		result = query.getResultList();
		if (result != null && !result.isEmpty()) {
			return result;
		} else {
			return null;
		}
	}

	@Override
	public ProductImage findPicByProdIMGID(Integer prodIMGID) {
		if (prodIMGID != null) {
			return getSession()
					.createQuery("from ProductImage where prodIMGID = :prodIMGID", ProductImage.class)
					.setParameter("prodIMGID", prodIMGID)
					.getSingleResult();
		}
		return null;
	}

	@Override
	public ProductImage findMainPic(Integer prodNo) {
		try {
			if (prodNo != null) {
				return getSession()
						.createQuery("from ProductImage where prodNo = :prodNo", ProductImage.class)
						.setParameter("prodNo", prodNo)
						.setMaxResults(1)
						.getSingleResult();
			}
		} catch (NoResultException e) {
		}
		return null;
	}
	
	@Override
	public List<Integer> findProdImageIdByProdNo(Integer prodNo) {
		List<Integer> result = new ArrayList<>();
		
		if (prodNo != null) {
			Query<Integer> query = getSession()
					.createQuery("select prodIMGID from ProductImage where prodNo = :prodNo", Integer.class)
					.setParameter("prodNo", prodNo);
			result = query.list();
			return result;
		}
		return null;
	}

	@Override
	public ProductImage update(ProductImage productImage) {
		if(productImage != null && productImage.getProdNo() != null) {
			this.getSession().merge(productImage);
			return productImage;
		}
		return null;
	}
	
	@Override
	public Integer findStock(ViewProductVO viewProductVO) {
		if(viewProductVO != null) {
			return getSession()
					.createQuery("select prodStock from ViewProductVO where prodNo = :prodNo" , Integer.class)
					.setParameter("prodNo", viewProductVO.getProdNo())
					.getSingleResult();
		}
		return null;
	}
	
	
//	public ViewProductVO insert(Integer number, ViewProductVO viewProductVO) {
//		Integer prodNo = viewProductVO.getProdNo();
//		String prodName = viewProductVO.getProdName();
//		Integer unitPrice = viewProductVO.getUnitPrice();
//		SetOperations set = redisTemplate.opsForSet();
//			set.add(prodNo, viewProductVO.getProdNo());
//			set.add(prodName, viewProductVO.getProdName());
//			set.add(unitPrice, viewProductVO.getUnitPrice());
//			Set<ViewProductVO> resuleSet = redisTemplate.opsForSet().members(number);
//					
//				
//		
//		
//		redisTemplate.opsForSet().set(number, set);
//		
//		
//		return viewProductVO;
//		
//	}
	
//	public ViewProductVO addRecentlyViewed(Integer number, ViewProductVO viewProductVO) {
//	if (number != null && viewProductVO != null) {
//		redisTemplate.opsForList().set(number, 0, null);
//	}
//	return null;
//}

	


}
