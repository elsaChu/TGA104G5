package com.organizer.model;

import java.util.List;

import com.organizer.model.*;

public interface organizerDAOinterfaceHibernate1 {
	
		public abstract organizerVOHibernate1 select(Integer organizerNumber);
		public List<organizerVOHibernate1> getAll();
		public abstract organizerVOHibernate1 insert(organizerVOHibernate1 bean); 
		public abstract organizerVOHibernate1 update(organizerVOHibernate1 bean);
		public abstract boolean delete(Integer organizerNumber);
		
}
