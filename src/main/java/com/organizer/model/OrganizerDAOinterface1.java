package com.organizer.model;

import java.util.List;

public interface OrganizerDAOinterface1 {
	
    public void insert(OrganizerVO1 organizerVO);
    public void update(OrganizerVO1 organizerVO);
    public void delete(Integer empno);
    public OrganizerVO1 findByPrimaryKey(Integer organizerNumber);
    public List<OrganizerVO1> selectAll();
//  public List<organizerVO> getAll(Map<String, String[]> map); 
	
}
