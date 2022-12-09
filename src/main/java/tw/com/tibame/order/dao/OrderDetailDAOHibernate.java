package tw.com.tibame.order.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;

import tw.com.tibame.order.vo.OrderDetailVO;

public class OrderDetailDAOHibernate implements OrderDetailDAO{
	
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return this.session;
	}
	
	@Override
	public List<OrderDetailVO> getAll() {
		List<OrderDetailVO> result = new ArrayList<>();
		
		Query<OrderDetailVO> query = getSession().createQuery("from OrderDetailVO", OrderDetailVO.class);
		result = query.getResultList();
		if(result != null && !result.isEmpty()) {
			return result;
		} else {
			return null;
		}
	}
	
	@Override
	public OrderDetailVO getPrimaryKey(Integer itemNo) {
		OrderDetailVO result = null;
		if(itemNo != null) {
			result = this.getSession().get(OrderDetailVO.class, itemNo);
			return result;
		}
		return null;
	}

	@Override  // 以訂單編號查詢單筆訂單
	public List<OrderDetailVO> getByProdOrderNo(Integer prodOrderNo) {
		List<OrderDetailVO> result = new ArrayList<>();
		
		if(prodOrderNo != null) {
			Query<OrderDetailVO> query = getSession().createQuery("from OrderDetailVO where prodOrderNo =: prodOrderNo", OrderDetailVO.class); 
			query.setParameter("prodOrderNo", prodOrderNo);
			result = query.list();
			return result;
		}
		return null;
		}

	@Override
	public boolean insert(OrderDetailVO orderDetailVO) {
		
		if(orderDetailVO != null && orderDetailVO.getProdOrderNo() != null) {
			OrderDetailVO temp = this.getSession().get(OrderDetailVO.class, orderDetailVO.getProdOrderNo());
			if(temp == null) {
				this.getSession().persist(orderDetailVO);
				return true;
			}
		}
		return false;	
	}
	
	@Override
	public OrderDetailVO update(OrderDetailVO orderDetailVO) {
		
		if(orderDetailVO != null && orderDetailVO.getProdNo() != null) {
			OrderDetailVO temp = this.getSession().get(OrderDetailVO.class, orderDetailVO);
			if(temp != null) {
				return (OrderDetailVO) this.getSession().merge(orderDetailVO);
			}
		}
		return null;
		}


	
	

//	public static void main(String[] args) {
//
//		// 測試與資料庫的連線
//		try(Connection connection = dataSource.getConnection()) {
//			System.out.println("Connecting to MySQL successfully!!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//
//		// PK 單一查詢
//		OrderDetailDAOHibernate dao = new OrderDetailDAOHibernate();
//		OrderDetailVO orderDetailVO = dao.getPrimaryKey(3);
//		System.out.println(orderDetailVO.toString());
//
//		// 以訂單編號查詢單筆訂單
//		OrderDetailDAOHibernate dao = new OrderDetailDAOHibernate();
//		List<OrderDetailVO> list = dao.getByProdOrderNo(3);
//		for (OrderDetailVO orderDetailVO : list) {
//			System.out.println(orderDetailVO.toString());
//		}
//
//		// 查詢全部
//		OrderDetailDAOHibernate dao = new OrderDetailDAOHibernate();
//		List<OrderDetailVO> list = dao.getAll();
//		for(OrderDetailVO orderDetailVO : list) {
//			System.out.println(orderDetailVO.toString());
//		}
//
//		// 新增訂單明細
//		OrderDetailDAOHibernate dao = new OrderDetailDAOHibernate();
//		OrderDetailVO orderDetailVO = new OrderDetailVO();
//		orderDetailVO.setProdOrderNo(3);
//		orderDetailVO.setProdNo(3);
//		orderDetailVO.setProdQty(5);
//		orderDetailVO.setSubtotal(6);
//		dao.insert(orderDetailVO);
//		System.out.println(orderDetailVO.toString());
//
//		//測試更新評論退貨退款
//		OrderDetailDAOHibernate dao = new OrderDetailDAOHibernate();
//		OrderDetailVO orderDetailVO = new OrderDetailVO();
//		orderDetailVO.setProdNo(3);
//		orderDetailVO.setCommentContent("讚讚");
//		orderDetailVO.setCommentRanking(4.5F);
//		orderDetailVO.setItemNo(12);
//		dao.update(orderDetailVO);
//		System.out.println(orderDetailVO.toString());
//
//		// 測試刪除(好像用不到)
//		OrderDetailDAOHibernate dao = new OrderDetailDAOHibernate();
//		System.out.println(dao.delete(9));
//	}


}
