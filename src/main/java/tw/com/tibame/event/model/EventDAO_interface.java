package tw.com.tibame.event.model;

import java.util.List;

public interface EventDAO_interface {
	public int insert(EventVO eventvo,List<TicketVO> ticketlist,List<EventClassVO> eventclasslist,List<SeatVO> seatlist);
	public List<EventVO> selectAll();
	public EventVO selectByeventNumber(Integer eventNumber);
	public EventVO selectByeventName();
	public List<EventVO> selectByorganizerNumber();
	public List<EventVO> selectByeventType();
	public List<EventVO> selectByDate();
	public int update(EventVO eventvo,List<TicketVO> ticketlist,List<EventClassVO> eventclasslist,List<SeatVO> seatlist);
	
	
}
