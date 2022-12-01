package com.staff.model;

import java.util.List;

import javax.sql.DataSource;

//要先實作interface出來給service用
public interface StaffDAO_interface {
	public void insert(staffVO staffVO); 
	public void update(staffVO staffVO);
	public staffVO findByPrimaryKey(Integer staffNumber);
	public List<staffVO> getAll();
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map);
}
