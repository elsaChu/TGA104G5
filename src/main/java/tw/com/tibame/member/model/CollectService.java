package tw.com.tibame.member.model;

import java.util.List;

import tw.com.tibame.event.model.EventVO;

public class CollectService {
	private CollectDAOinterface dao;

	public CollectService() {
		 dao = new CollectDAO();
	}
	
	public List<EventVO> getFavorite(int memberid){
		return dao.selectAll(memberid);
	}

}
