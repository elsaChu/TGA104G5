package tw.com.tibame.staff.model;

import java.util.*;

public class StaffService {
	
	private StaffDAO_interface dao;

	public StaffService() {
		dao = new StaffJDBCDAO();
	}

	public List<staffVO> getAll() {
		return dao.getAll();
	}

	public staffVO insertStaff(String staffName, String staffAccount, String staffPassword) {

		staffVO staffVO = new staffVO();
		staffVO.setStaffName(staffName);
		staffVO.setStaffAccount(staffAccount);
		staffVO.setStaffPassword(staffPassword);
		dao.insert(staffVO);

		return staffVO;
	}

	public staffVO updateStaff(Integer StaffNumber, String staffName, String staffAccount, String staffPassword) {

		staffVO staffVO = new staffVO();
		
//		staffVO.setStaffNumber(StaffNumber);
		staffVO.setStaffName(staffName);
		staffVO.setStaffAccount(staffAccount);
		staffVO.setStaffPassword(staffPassword);
		dao.update(staffVO);

		return staffVO;
	}

	public staffVO getOneStaff(Integer staffNumber) {
		System.out.println("get one service");
		return dao.findByPrimaryKey(staffNumber);
	}

	public void deleteStaff(Integer staffNumber) {

		dao.delete(staffNumber);

	}
}