package tw.com.tibame.event.model;

import java.sql.Connection;

public interface SeatDAO_interface {
	public int insert(SeatVO aseat,Connection conn);
	public int update();
	public int updateByseatType();
	public SeatVO eventNumber();
}
