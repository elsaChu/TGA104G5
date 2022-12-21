package tw.com.tibame.member.model;

import java.util.List;

import tw.com.tibame.event.model.EventVO;

public interface CollectDAOinterface {
	public boolean insert(int memberId, int eventId);
	public List<EventVO> selectAll(int memeberId);
}
