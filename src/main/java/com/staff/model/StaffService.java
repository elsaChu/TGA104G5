package com.staff.model;

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
		System.out.println("insert service");
		staffVO staffVO = new staffVO();
		staffVO.setStaffName(staffName);
		staffVO.setStaffAccount(staffAccount);
		staffVO.setStaffPassword(staffPassword);
		dao.insert(staffVO);

		return staffVO;
	}

	public staffVO updateStaff(String staffName, String staffAccount, String staffPassword) {
		System.out.println("update service");
		staffVO staffVO = new staffVO();
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

	public void staffVO(Integer staffNumber) {
		System.out.println("delete service");
		dao.delete(staffNumber);

	}
}