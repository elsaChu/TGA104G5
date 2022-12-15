package tw.com.tibame.order.dao;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tibame.order.vo.OrderDetailVO;
import tw.com.tibame.order.vo.ShoppingCartVO;

@Repository
@Transactional
public class ShoppingCartDAOHibernate implements ShoppingCartDAO {
	
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return this.session;
	}

	@Override
	public List<ShoppingCartVO> getAll() {
		return this.getSession().createQuery("from ShoppingCartVO", ShoppingCartVO.class).list();
	}

	@Override
	public ShoppingCartVO getByPrimaryKey(Integer shoppingCartNo) {
		if(shoppingCartNo != null) {
			return this.getSession().get(ShoppingCartVO.class, shoppingCartNo);
		}
		return null;
	}

	@Override
	public ShoppingCartVO insert(ShoppingCartVO shoppingCartVO) {
		if(shoppingCartVO != null) {
			this.getSession().persist(shoppingCartVO);
			return shoppingCartVO;
		}
		return null;
	}

	@Override
	public ShoppingCartVO update(ShoppingCartVO shoppingCartVO) {
		if(shoppingCartVO != null && shoppingCartVO.getShoppingCartNo() != null) {
			ShoppingCartVO temp = this.getSession().get(ShoppingCartVO.class, shoppingCartVO.getShoppingCartNo());
			if(temp != null) {
				this.getSession().merge(shoppingCartVO);
				return shoppingCartVO;
			}
		}
		return null;
	}

	@Override
	public boolean delete(Integer shoppingCartNo) {
		if(shoppingCartNo != null) {
			ShoppingCartVO temp = this.getSession().get(ShoppingCartVO.class, shoppingCartNo);
			if(temp != null) {
				this.getSession().delete(temp);
				return true;
			}
		}
		return false;
	}

	@Override 
	public List<ShoppingCartVO> getByMemberNumber(Integer number) {
		if(number != null) {
			Query<ShoppingCartVO> query = getSession().createQuery("from ShoppingCartVO where number =: number", ShoppingCartVO.class); 
			return query.setParameter("number", number).list();
		}
		return null;
	}


}
