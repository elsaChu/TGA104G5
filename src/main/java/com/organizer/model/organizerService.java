package com.organizer.model;

import java.util.List;

public class organizerService {
		public static void main(String args[]) {
			
			organizerService service1 = new organizerService();
			service1.addOrganizer("organizer1", 3567);
		}
		private organizerDAOinterface dao;

		public organizerService() {
			dao = new organizerDAO();
		}

		public organizerVO addOrganizer(String organizerName, Integer organizerNumber) {

			organizerVO organizerVO = new organizerVO();
			organizerVO.setOrganizerNumber(organizerNumber);
			organizerVO.setOrganizerName(organizerName);
			dao.insert(organizerVO);

			return organizerVO;
		}

		public organizerVO updateEmp(Integer organizerNumber, String ename, String job,
				java.sql.Date hiredate, Double sal, Double comm, Integer deptno) {

			organizerVO organizerVO = new organizerVO();

//			organizerVO.setorganizerNumber(organizerNumber);
//			organizerVO.setEname(ename);
//			organizerVO.setJob(job);
//			organizerVO.setHiredate(hiredate);
//			organizerVO.setSal(sal);
//			organizerVO.setComm(comm);
//			organizerVO.setDeptno(deptno);
			dao.update(organizerVO);

			return organizerVO;
		}

		public void deleteEmp(Integer organizerNumber) {
			dao.delete(organizerNumber);
		}

		public organizerVO getOneEmp(Integer organizerNumber) {
			return dao.findByPrimaryKey(organizerNumber);
		}

		public List<organizerVO> getAll() {
			return dao.getAll();
		}
	

}
