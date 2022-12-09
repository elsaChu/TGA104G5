package tw.com.tibame.event.model;

import java.util.List;

public interface SoldTicketsDAO_interface {
	public int insert();
	public int update();
	public List<SoldTicketsVO> selectByOrderID();
	
}
