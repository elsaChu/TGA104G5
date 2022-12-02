package com.product.model;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import conn.HibernateUtil;

public class ProductOrderHibernateDAO implements ProductOrderDAO{
	private SessionFactory sessionFactory;
	public ProductOrderHibernateDAO(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		
		ProductOrderDAO dao = new ProductOrderHibernateDAO(sessionFactory);
		
		List<ProductOrderVO> productOrderVOs = dao.getAll();
		System.out.println("productOrderVO=" + productOrderVOs);
		
		
		
		transaction.commit();
		session.close();
		HibernateUtil.closeSessionFactory();
	}

	@Override
	public List<ProductOrderVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override	// 會員中心 - 查詢所有訂單
	public List<ProductOrderVO> getByNumber(Integer number) {
		
		return null;
	}

	@Override	// 會員中心 - 查詢單筆訂單
	public ProductOrderVO getPrimaryKey(Integer prodOrderNo) {
		if(prodOrderNo != null) {
			return this.getSession().get(ProductOrderVO.class, prodOrderNo);
		}
		return null;
	}

	@Override
	public void insert(ProductOrderVO productOrderVO) {
		// TODO Auto-generated method stub
		
	}

	@Override	
	public void update(ProductOrderVO productOrderVO) {
		// TODO Auto-generated method stub
		
	}



}
