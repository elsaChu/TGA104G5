package tw.com.tibame.order.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tibame.order.vo.ShoppingCartVO;
import tw.com.tibame.order.vo.ShowShoppingCartVO;

@Repository
@Transactional
public class ShoppingCartDAOHibernate implements ShoppingCartDAO {

	@PersistenceContext
	private Session session;

	public Session getSession() {
		return this.session;
	}

	@Override
	public List<ShowShoppingCartVO> getAll() {
		return this.getSession().createQuery("from ShowShoppingCartVO", ShowShoppingCartVO.class).list();
	}

	@Override
	public ShowShoppingCartVO getByShoppingCartNo(Integer shoppingCartNo) {
		if (shoppingCartNo != null) {
			return this.getSession().get(ShowShoppingCartVO.class, shoppingCartNo);
		}
		return null;
	}

	@Override
	public ShoppingCartVO getByPrimaryKey(Integer shoppingCartNo) {
		if (shoppingCartNo != null) {
			return this.getSession().get(ShoppingCartVO.class, shoppingCartNo);
		}
		return null;
	}

	@Override
	public ShoppingCartVO getByMemberNoAndProdNo(Integer number, Integer prodNo) {
		if (number != null && prodNo != null) {
			try {
				Query<ShoppingCartVO> query = getSession().createQuery(
						"from ShoppingCartVO where number = :number and prodNo= :prodNo",ShoppingCartVO.class);
				
					return query
							.setParameter("number", number)
							.setParameter("prodNo", prodNo)
							.getSingleResult();
			} catch (NoResultException e) {
			}
			
		}
		return null;
	}
	
	@Override
	public ShoppingCartVO insert(ShoppingCartVO shoppingCartVO) {
		if (shoppingCartVO != null) {
			this.getSession().persist(shoppingCartVO);
			return shoppingCartVO;
		}
		return null;
	}

	@Override
	public ShoppingCartVO update(ShoppingCartVO shoppingCartVO) {
		if (shoppingCartVO != null) {
			ShoppingCartVO target = getByMemberNoAndProdNo(shoppingCartVO.getNumber(),shoppingCartVO.getProdNo());
			if(shoppingCartVO.getShoppingQty()!= null) {
				target.setShoppingQty(shoppingCartVO.getShoppingQty());
			}
		}
		return null;
	}

	@Override
	public boolean delete(Integer shoppingCartNo) {
		if (shoppingCartNo != null) {
			ShoppingCartVO temp = this.getSession().get(ShoppingCartVO.class, shoppingCartNo);
			if (temp != null) {
				this.getSession().delete(temp);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<ShowShoppingCartVO> getByMemberNumber(Integer number) {
		if (number != null) {
			Query<ShowShoppingCartVO> query = getSession().createQuery("from ShowShoppingCartVO where number = :number",
					ShowShoppingCartVO.class);
			return query.setParameter("number", number).list();
		}
		return null;
	}
	
	@Override
	public List<ShoppingCartVO> findByMemberNumber(Integer number) {
		if (number != null) {
			Query<ShoppingCartVO> query = getSession().createQuery("from ShoppingCartVO where number = :number",
					ShoppingCartVO.class);
			return query.setParameter("number", number).list();
		}
		return null;
	}

}
