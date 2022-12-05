package com.organizer.model;

import java.util.List;

import com.organizer.model.*;

public interface organizerDAOinterfaceHibernate {
	
		public abstract organizerVOHibernate select(Integer organizerNumber);
		public List<organizerVOHibernate> getAll();
		public abstract organizerVOHibernate insert(organizerVOHibernate bean); 
		public abstract organizerVOHibernate update(organizerVOHibernate bean);
		public abstract boolean delete(Integer organizerNumber);
		
}
