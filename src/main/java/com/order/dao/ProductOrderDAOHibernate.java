package com.order.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.order.vo.ProductOrderVO;

import conn.HibernateUtil;

public class ProductOrderDAOHibernate implements ProductOrderDAO {
	private SessionFactory sessionFactory;
	public ProductOrderDAOHibernate(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		// 以訂單編號查詢單筆訂單
//		ProductOrderDAO dao = new ProductOrderDAOHibernate(sessionFactory);
//		ProductOrderVO productOrderVO = dao.getPrimaryKey(1);
//		System.out.println(productOrderVO.toString());
		
		//測試更新收件資訊及狀態
		ProductOrderDAO dao = new ProductOrderDAOHibernate(sessionFactory);
		ProductOrderVO productOrderVO = new ProductOrderVO();
		productOrderVO.setReceiverName("小小");
		productOrderVO.setReceiverTel("0288888888");
		productOrderVO.setShippingAdd("小小家");
		productOrderVO.setProdOrderStatus("待收貨");
		productOrderVO.setDeliveryStatus("配送中");
		productOrderVO.setProdOrderNo(5);
		
		productOrderVO = dao.update(productOrderVO);
		System.out.println(productOrderVO.toString());
//		
//		// 新增訂單
//		ProductOrderJdbcDAO dao = new ProductOrderJdbcDAO();
//		ProductOrderVO productOrderVO = new ProductOrderVO();
//		productOrderVO.setNumber(3);
//		productOrderVO.setAmountPrice(3000);
//		productOrderVO.setProdTotal(2);
//		productOrderVO.setReceiverName("收件人");
//		productOrderVO.setReceiverTel("5555");
//		productOrderVO.setShippingAdd("address");
//		
//		dao.insert(productOrderVO);
//		System.out.println(productOrderVO.toString());

		
		transaction.commit();
		session.close();
		HibernateUtil.closeSessionFactory();

	}

	@Override
	public List<ProductOrderVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	private static final String SELECT_BY_NUMBER = "select * from PROD_ORDER where number = ?";

	@Override // 會員中心 - 查詢所有訂單
	public List<ProductOrderVO> getByNumber(Integer number) {
		List<ProductOrderVO> result = new ArrayList<>();
		if(number != null) {
			
		}
		return null;
		
		
		
//		try {
//			Class.forName(driver);
//		} catch (ClassNotFoundException e1) {
//			e1.printStackTrace();
//		}
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement ps = connection.prepareStatement(SELECT_BY_NUMBER)) {
//			/*
//			 * 當Statement關閉，ResultSet也會自動關閉，可以不需要將ResultSet宣告置入try with
//			 * resources小括號內，參看ResultSet說明
//			 */
//			ps.setInt(1, number);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				ProductOrderVO productOrderVO = new ProductOrderVO();
//				productOrderVO.setProdOrderNo(rs.getInt("prodOrderNo"));
//				productOrderVO.setAmountPrice(rs.getInt("amountPrice"));
//				productOrderVO.setProdTotal(rs.getInt("prodTotal"));
//				productOrderVO.setPaymentDate(rs.getTimestamp("paymentDate"));
//				productOrderVO.setReceiverName(rs.getString("receiverName"));
//				productOrderVO.setReceiverTel(rs.getString("receiverTel"));
//				productOrderVO.setShippingAdd(rs.getString("shippingAdd"));
//				productOrderVO.setProdOrderStatus(rs.getString("prodOrderStatus"));
//				productOrderVO.setDeliveryStatus(rs.getString("deliveryStatus"));
//
//				result.add(productOrderVO);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;

	}


	@Override // 會員中心 - 查詢單筆訂單
	public ProductOrderVO getPrimaryKey(Integer prodOrderNo) {
		if(prodOrderNo != null) {
			ProductOrderVO obj = this.getSession().get(ProductOrderVO.class, prodOrderNo);
			return obj;
		}
		return null;
	}

	
	private static final String INSERT = 
			  "insert into PROD_ORDER (number, amountPrice, prodTotal, receiverName, receiverTel, shippingAdd) "
			  + "values	(?, ?, ?, ?, ?, ?)";
	@Override	// 新增訂單
	public ProductOrderVO insert(ProductOrderVO productOrderVO) { //甚麼意思?
		if(productOrderVO != null && productOrderVO.getProdOrderNo()!= null) {
			ProductOrderVO temp = this.getSession().get(ProductOrderVO.class, productOrderVO.getProdOrderNo());
			if(temp == null) {
				this.getSession().persist(productOrderVO);
				return productOrderVO;
			}
		}
		return null;
	}

	private static final String UPDATE = "update PROD_ORDER set receiverName = ?, receiverTel = ?, shippingAdd = ?, prodOrderStatus = ?, deliveryStatus = ? where prodOrderNo = ?;";

	@Override	// 更新收件資訊及狀態
	public ProductOrderVO update(ProductOrderVO productOrderVO) {
		if (productOrderVO != null && productOrderVO.getProdOrderNo() != null) {
			ProductOrderVO temp = this.getSession().get(ProductOrderVO.class, productOrderVO);
			if(temp != null) {
				return (ProductOrderVO) this.getSession().merge(productOrderVO);
			}
		}
		return null;
	}

//	public static void main(String[] args) {

//		// 以訂單編號查詢單筆訂單
//		ProductOrderJdbcDAO dao = new ProductOrderJdbcDAO();
//		ProductOrderVO productOrderVO = dao.getPrimaryKey(1);
//		System.out.println(productOrderVO.toString());
//		
//		//測試更新收件資訊及狀態
//		ProductOrderJdbcDAO dao = new ProductOrderJdbcDAO();
//		ProductOrderVO productOrderVO = new ProductOrderVO();
//		productOrderVO.setReceiverName("");
//		productOrderVO.setReceiverTel("0288888888");
//		productOrderVO.setShippingAdd("");
//		productOrderVO.setProdOrderStatus("");
//		productOrderVO.setDeliveryStatus("");
//		productOrderVO.setProdOrderNo(5);
//		
//		dao.update(productOrderVO);
//		System.out.println(productOrderVO.toString());
//		
//		// 新增訂單
//		ProductOrderJdbcDAO dao = new ProductOrderJdbcDAO();
//		ProductOrderVO productOrderVO = new ProductOrderVO();
//		productOrderVO.setNumber(3);
//		productOrderVO.setAmountPrice(3000);
//		productOrderVO.setProdTotal(2);
//		productOrderVO.setReceiverName("收件人");
//		productOrderVO.setReceiverTel("5555");
//		productOrderVO.setShippingAdd("address");
//		
//		dao.insert(productOrderVO);
//		System.out.println(productOrderVO.toString());
//		
//	}

}
