package tw.com.tibame.event.model;

import java.sql.Connection;
import java.util.List;

public interface TicketDAO_interface {
	public int insert(TicketVO ticketvo, Connection con);
	public int update();
	public List<TicketVO> selectByeventNumber();
}
