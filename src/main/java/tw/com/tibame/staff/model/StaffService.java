package tw.com.tibame.staff.model;

import java.util.*;

public class StaffService {
	
	private StaffDAO_interface dao;

	public StaffService() {
		dao = new StaffJDBCDAO();
	}

	public List<StaffVO> getAll() {
		return dao.getAll();
	}
	
	public List<StaffVO> findByStaffNumber(Integer staffNumber) {
		return dao.findByStaffNumber(staffNumber);
	}

	public StaffVO insertStaff(String staffName, String staffAccount, String staffPassword) {

		StaffVO staffVO = new StaffVO();
		staffVO.setStaffName(staffName);
		staffVO.setStaffAccount(staffAccount);
		staffVO.setStaffPassword(staffPassword);
		dao.insert(staffVO);

		return staffVO;
	}

	public StaffVO updateStaff(Integer staffNumber, String staffName, String staffAccount, String staffPassword) {

		StaffVO staffVO = new StaffVO();
		
		staffVO.setStaffNumber(staffNumber);
		staffVO.setStaffName(staffName);
		staffVO.setStaffAccount(staffAccount);
		staffVO.setStaffPassword(staffPassword);
		dao.update(staffVO);

		return staffVO;
	}

	public StaffVO getOneStaff(Integer staffNumber) {
		System.out.println("get one service");
		return dao.findByPrimaryKey(staffNumber);
	}

	public void deleteStaff(Integer staffNumber) {
		dao.delete(staffNumber);

	}
	
	public StaffVO getOneByAccount(String staffAccount) {
		System.out.println("getOneByAccount");
		
		return dao.findByStaffAccount(staffAccount);
	}
}