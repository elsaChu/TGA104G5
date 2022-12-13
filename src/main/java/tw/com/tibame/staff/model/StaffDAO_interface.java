package tw.com.tibame.staff.model;

import java.util.List;

//要先實作interface出來給service用
public interface StaffDAO_interface {
	
	public void insert(StaffVO staffVO); 
	public void update(StaffVO staffVO);
	public void delete(Integer staffNumber);
	public StaffVO findByPrimaryKey(Integer staffNumber);
	public StaffVO findByStaffAccount(String staffAccount);
	public List<StaffVO> getAll();
	public List<StaffVO> findByStaffNumber(Integer staffNumber);
	//萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<EmpVO> getAll(Map<String, String[]> map);
}
