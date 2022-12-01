package com.staff.model;

public class StaffService {
	private StaffDAO_interface dao;

	public StaffService() {
		dao = new StaffJDBCDAO();
	}

	public staffVO insertStaff(String staffName, String staffAccount, String staffPassword) {
		System.out.println("in service");
		staffVO staffVO = new staffVO();
		staffVO.setStaffName(staffName);
		staffVO.setStaffAccount(staffAccount);
		staffVO.setStaffPassword(staffPassword);
		dao.insert(staffVO);

		return staffVO;
	}

//	public staffVO updateStaff(String staffName, String staffAccount, String staffPassword) {
//
//		staffVO staffVO = new staffVO();
//		staffVO.setStaffName(staffName);
//		staffVO.setStaffAccount(staffAccount);
//		staffVO.setStaffPassword(staffPassword);
//		dao.insert(staffVO);
//
//	}
}