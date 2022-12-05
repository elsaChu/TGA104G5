package com.organizer.model;

import java.util.List;
import java.util.Vector;

public class OrganizerService1 {
//		public static void main(String args[]) {
////			
////			OrganizerService service1 = new OrganizerService();
////			service1.addOrganizer("organizer1", 3567);
//		}
		private OrganizerDAOinterface1 dao;

		public OrganizerService1() {
			 dao = new OrganizerDAO1();
		}

		public OrganizerVO1 addOrganizer(OrganizerVO1 bean) {
			dao.insert(bean);
			return bean;
		}

		public OrganizerVO1 updateOrganizer(Integer organizerNumber, String ename, String job,
				java.sql.Date hiredate, Double sal, Double comm, Integer deptno) {

			OrganizerVO1 organizerVO = new OrganizerVO1();
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

		public OrganizerVO1 getOneEmp(Integer organizerNumber) {
			return dao.findByPrimaryKey(organizerNumber);
		}

		public List<OrganizerVO1> selectAll() {
			return dao.selectAll();
		}
	

}
