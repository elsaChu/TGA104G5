package com.organizer.model;
//package com.JHWorkspace;
//
//import java.util.*;
//
//import javax.persistence.TypedQuery;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//
//import conn.HibernateUtil;
//
////import conn.HibernateUtil;
////import model.organizerDAO;
////import model.dao.organizerDAO;
//
//import java.sql.*;
//
//public class OrganizerDAOHibernate implements OrganizerDAOinterfaceHibernate{
//	private SessionFactory sessionFactory;
//	public OrganizerDAOHibernate(SessionFactory sessionFactory) {
//		super();
//		this.sessionFactory = sessionFactory;
//	}
//	public Session getSession() {
//		return this.sessionFactory.getCurrentSession();
//	}
//	
//	public static void main(String[] args) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.getCurrentSession();
//		Transaction transaction = session.beginTransaction();
//		
////		organizerDAOinterface dao = new organizerDAO(sessionFactory);
//////		測試INSERT
////		organizerVOHibernate insert1 = new organizerVOHibernate();
////		insert1.setOrganizerNumber(50);
////		insert1.setAccountName("yoshi");
////		organizerVOHibernate result1 = dao.insert(insert1);
//////	測試getAll
////		List<organizerVOHibernate> beans = dao.getAll();
////		System.out.println("bean="+beans);	
////		測試UPDATE
////		organizerVOHibernate bean2 = new organizerVOHibernate();
////		dao.update("Calpis", 24.0, new java.util.Date(0), 5, 50);
////		測試DELETE
////		boolean delete1 = dao.delete(50);
//		
//		transaction.commit();
//		session.close();
//		HibernateUtil.closeSessionFactory();
//	}
//	
//	@Override
//	public  OrganizerVOHibernate select(Integer organizerNumber) {
//		if(organizerNumber != null) {
//			return this.getSession().get(OrganizerVOHibernate.class, organizerNumber);
//		}
//		return null;
//	}
//	@Override
//	public  OrganizerVOHibernate insert(OrganizerVOHibernate bean) {
//		if(bean!=null && bean.getOrganizerNumber() !=null) {
//			OrganizerVOHibernate temp = this.getSession().get(OrganizerVOHibernate.class, bean.getOrganizerNumber());
//			if(temp==null) {
//				this.getSession().save(bean);
//			}
//			return bean;
//		}
//		return null;
//	}
//
//	
//	@Override
//	public List<OrganizerVOHibernate> getAll(){
////		return this.getSession().createQuery("from organizerVOHibernate", organizerVOHibernate.class).list();
//		
//		CriteriaBuilder cb1 = this.getSession().getCriteriaBuilder();
//		CriteriaQuery<OrganizerVOHibernate> cq1 = cb1.createQuery(OrganizerVOHibernate.class);
//		Root<OrganizerVOHibernate> root1 = cq1.from(OrganizerVOHibernate.class);
//		
//		TypedQuery<OrganizerVOHibernate> tq1 = this.getSession().createQuery(cq1);
//		 List<OrganizerVOHibernate> result1 = tq1.getResultList();
//		 System.out.println("ok");
//		if(result1 != null && !result1.isEmpty()) {
//		//是否是空的
//			System.out.print(result1);
//			System.out.print("this");
//			return result1;
//		}else {
//			System.out.print("this is null");
//			return null;
//		}
//		
//	}
//	
//	@Override
//	public  OrganizerVOHibernate update(OrganizerVOHibernate bean) {
//		if(bean!=null && bean.getOrganizerNumber() !=null) {
//			OrganizerVOHibernate temp = this.getSession().get(OrganizerVOHibernate.class, bean.getOrganizerNumber());
////			if(temp!= null) {
////				temp.setName(name);
////				temp.setMake(make);
////				temp.setPrice(price);
////				temp.setExpire(expire);
////				return temp;
////			}
//		}
//		return null;
//	}
//	
//	@Override
//	public  boolean delete(Integer organizerNumber) {
//		if(organizerNumber!=null) {
//			OrganizerVOHibernate temp = this.getSession().get(OrganizerVOHibernate.class, organizerNumber);
//			if(temp!=null) {
//				this.getSession().delete(temp);
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	
//}
