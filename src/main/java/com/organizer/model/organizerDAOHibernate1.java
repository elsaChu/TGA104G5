package com.organizer.model;

import java.util.*;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import conn.HibernateUtil;

//import conn.HibernateUtil;
//import model.organizerDAO;
//import model.dao.organizerDAO;

import java.sql.*;

public class organizerDAOHibernate1 implements organizerDAOinterfaceHibernate1{
	private SessionFactory sessionFactory;
	public organizerDAOHibernate1(SessionFactory sessionFactory) {
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
		
//		organizerDAOinterface dao = new organizerDAO(sessionFactory);
////		測試INSERT
//		organizerVOHibernate insert1 = new organizerVOHibernate();
//		insert1.setOrganizerNumber(50);
//		insert1.setAccountName("yoshi");
//		organizerVOHibernate result1 = dao.insert(insert1);
////	測試getAll
//		List<organizerVOHibernate> beans = dao.getAll();
//		System.out.println("bean="+beans);	
//		測試UPDATE
//		organizerVOHibernate bean2 = new organizerVOHibernate();
//		dao.update("Calpis", 24.0, new java.util.Date(0), 5, 50);
//		測試DELETE
//		boolean delete1 = dao.delete(50);
		
		transaction.commit();
		session.close();
		HibernateUtil.closeSessionFactory();
	}
	
	@Override
	public  organizerVOHibernate1 select(Integer organizerNumber) {
		if(organizerNumber != null) {
			return this.getSession().get(organizerVOHibernate1.class, organizerNumber);
		}
		return null;
	}
	@Override
	public  organizerVOHibernate1 insert(organizerVOHibernate1 bean) {
		if(bean!=null && bean.getOrganizerNumber() !=null) {
			organizerVOHibernate1 temp = this.getSession().get(organizerVOHibernate1.class, bean.getOrganizerNumber());
			if(temp==null) {
				this.getSession().save(bean);
			}
			return bean;
		}
		return null;
	}

	
	@Override
	public List<organizerVOHibernate1> getAll(){
//		return this.getSession().createQuery("from organizerVOHibernate", organizerVOHibernate.class).list();
		
		CriteriaBuilder cb1 = this.getSession().getCriteriaBuilder();
		CriteriaQuery<organizerVOHibernate1> cq1 = cb1.createQuery(organizerVOHibernate1.class);
		Root<organizerVOHibernate1> root1 = cq1.from(organizerVOHibernate1.class);
		
		TypedQuery<organizerVOHibernate1> tq1 = this.getSession().createQuery(cq1);
		 List<organizerVOHibernate1> result1 = tq1.getResultList();
		 System.out.println("ok");
		if(result1 != null && !result1.isEmpty()) {
		//是否是空的
			System.out.print(result1);
			System.out.print("this");
			return result1;
		}else {
			System.out.print("this is null");
			return null;
		}
		
	}
	
	@Override
	public  organizerVOHibernate1 update(organizerVOHibernate1 bean) {
		if(bean!=null && bean.getOrganizerNumber() !=null) {
			organizerVOHibernate1 temp = this.getSession().get(organizerVOHibernate1.class, bean.getOrganizerNumber());
//			if(temp!= null) {
//				temp.setName(name);
//				temp.setMake(make);
//				temp.setPrice(price);
//				temp.setExpire(expire);
//				return temp;
//			}
		}
		return null;
	}
	
	@Override
	public  boolean delete(Integer organizerNumber) {
		if(organizerNumber!=null) {
			organizerVOHibernate1 temp = this.getSession().get(organizerVOHibernate1.class, organizerNumber);
			if(temp!=null) {
				this.getSession().delete(temp);
				return true;
			}
		}
		return false;
	}
	
	
}
