package tw.com.tibame.event.model;

import java.sql.Connection;
import java.util.List;

public interface EventClassDAO_interface {
	public List<EventClassVO> selectByeventNumber();
	public int update();
	public int insert(EventClassVO eventclassvo, Connection con);
}
