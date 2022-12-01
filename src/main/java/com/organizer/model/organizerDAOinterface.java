package com.organizer.model;

import java.util.List;

public interface organizerDAOinterface {
	
    public void insert(organizerVO organizerVO);
    public void update(organizerVO organizerVO);
    public void delete(Integer empno);
    public organizerVO findByPrimaryKey(Integer organizerNumber);
    public List<organizerVO> getAll();
//  public List<organizerVO> getAll(Map<String, String[]> map); 
	
}
