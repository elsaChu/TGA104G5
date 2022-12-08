package com.organizer.model;

import java.util.Iterator;
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

		public String addOrganizer(OrganizerVO bean) {
			boolean noMatch = true;
			String returnString ="Insert Success";
			List<OrganizerVO> list2 = dao.selectAll();
			String beanName = bean.getOrganizerName();
			for (Iterator<OrganizerVO> iter = list2.iterator(); iter.hasNext(); ) {
				OrganizerVO ov = new OrganizerVO();
				ov = iter.next();
				if(ov.getOrganizerName() != null) {
						if(ov.getOrganizerName().equalsIgnoreCase(beanName)) {
							noMatch = false;
							returnString = "  Insert Failed.\n Name already exists";
							System.out.println("Match Found");
							//break;
							//用break不能跳出FOR EACH迴圈 要跳出的話要利用EXCPETION，不然就改用FOR LOOP
						}else {
					}
				}
			}
			if(noMatch == true) {
				dao.insert(bean);				
			}
			return returnString;
		}

		public OrganizerVO updateOrganizer(OrganizerVO bean) {
			
			boolean noMatch = true;
			String returnString ="Insert Success";
			int beanNum = bean.getOrganizerNumber();
			OrganizerVO bean2 = dao.findByPrimaryKey(beanNum);
			
			if(bean2 != null) {
				noMatch = false;
				System.out.println("Match Found, execute update");
				dao.update(bean2);
			}else {
				System.out.println("bean == null");
//				dao.insert(bean);
			}
			
//			organizerVO.setorganizerNumber(organizerNumber);
//			organizerVO.setEname(ename);
//			organizerVO.setJob(job);
//			organizerVO.setHiredate(hiredate);
//			organizerVO.setSal(sal);
//			organizerVO.setComm(comm);
//			organizerVO.setDeptno(deptno);
			dao.update(bean);

			return bean;
		}

		public void deleteEmp(Integer organizerNumber) {
			dao.delete(organizerNumber);
		}
		
		public List<OrganizerVO> searchOrganizer(String keyword) {
			List<OrganizerVO> listVO = dao.searchOrganizerByAll(keyword);
			return listVO;
//			//參數
//			boolean noMatch = true;
//			String returnString ="";
//			List<OrganizerVO> matchedList = null;
//			List<OrganizerVO> list2 = dao.selectAll();
//			//search if input(keyword) matches any organizerName in DB, return String
//			for (Iterator<OrganizerVO> iter = list2.iterator(); iter.hasNext(); ) {
//				OrganizerVO ov = new OrganizerVO();
//				ov = iter.next();
//				if(ov.getOrganizerName() != null) {
//						if(ov.getOrganizerName().equalsIgnoreCase(keyword)) {
//							matchedList.add(ov);
//							noMatch = false;
//							returnString = "Match Found";
//							System.out.println("Match Found");
//							System.out.println(ov.getOrganizerName());
//							//break;
//							//用break不能跳出FOR EACH迴圈 要跳出的話要利用EXCPETION，不然就改用FOR LOOP
//						}else {
//					}
//				}
//			}
//			if(noMatch == true) {
//				returnString = "There is no match";
//			}
//			System.out.println(matchedList);
//			return matchedList;	
		}
		
		public OrganizerVO getOneOrganizer(Integer organizerNumber) {
			return dao.findByPrimaryKey(organizerNumber);
		}

		public List<OrganizerVO> selectAll() {
			return dao.selectAll();
		}
	

}
