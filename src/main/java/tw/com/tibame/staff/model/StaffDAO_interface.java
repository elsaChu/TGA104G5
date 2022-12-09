package tw.com.tibame.staff.model;

import java.util.List;

//要先實作interface出來給service用
public interface StaffDAO_interface {
	
	public void insert(staffVO staffVO); 
	public void update(staffVO staffVO);
	public void delete(Integer staffNumber);
	public staffVO findByPrimaryKey(Integer staffNumber);
	public List<staffVO> getAll();
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map);
}
