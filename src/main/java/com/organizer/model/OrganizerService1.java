package com.organizer.model;

import java.util.List;
import java.util.Vector;

public class OrganizerService {
//		public static void main(String args[]) {
////			
////			OrganizerService service1 = new OrganizerService();
////			service1.addOrganizer("organizer1", 3567);
//		}
		private OrganizerDAOinterface dao;

		public OrganizerService() {
			 dao = new OrganizerDAO();
		}

		public OrganizerVO addOrganizer(OrganizerVO bean) {
			dao.insert(bean);
			return bean;
		}

		public OrganizerVO updateOrganizer(Integer organizerNumber, String ename, String job,
				java.sql.Date hiredate, Double sal, Double comm, Integer deptno) {

			OrganizerVO organizerVO = new OrganizerVO();
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

		public OrganizerVO getOneEmp(Integer organizerNumber) {
			return dao.findByPrimaryKey(organizerNumber);
		}

		public List<OrganizerVO> selectAll() {
			return dao.selectAll();
		}
	

}
