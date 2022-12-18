package tw.com.tibame.event.model;

import tw.com.tibame.util.common.Common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class EventJDBCDAO implements EventDAO_interface {
	private static final String eventInsertSQL = "insert into `EVENT`(organizerNumber,eventName,eventStartDate,eventEndDate,"
			+ "peopleNumber,eventPlace,eventP2,eventSummary,eventDescribe,bigImg,smallImg,"
			+ "collectType,isON,eventType,needSeat,seatX,seatY) " + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
	private static final String eventUpdateSQL = "update `EVENT` set eventName=?, eventStartDate=?, eventEndDate=?, "
			+ "peopleNumber=?, eventPlace=?, eventP2=?, eventSummary=?, eventDescribe=?, "
			+ "bigImg=?, smallImg=?, collectType=?, isON=?, eventType=?, needSeat=?, seatX=?, seatY=? "
			+ "where eventNumber=?;";
	private static final String selectByPKSQL="select * from `EVENT` where eventNumber=?;";
	@Override
	public int insert(EventVO eventvo,List<TicketVO> ticketlist,List<EventClassVO> eventclasslist,List<SeatVO> seatlist) {
		Connection conn = null;
		PreparedStatement ps = null;
		int recount = 0;
		try {
			Class.forName(Common.driver);
			conn = DriverManager.getConnection(Common.URL,Common.USER,Common.PASSWORD);
			
			conn.setAutoCommit(false);
			//新增event table
			String[] cols = {"EVENT"};
			ps = conn.prepareStatement(eventInsertSQL,cols);
			
			ps.setInt(1,eventvo.getOrganizerNumber());
			ps.setString(2,eventvo.getEventName());
			ps.setTimestamp(3,eventvo.getEventStartDate());
			ps.setTimestamp(4,eventvo.getEventEndDate());
			ps.setInt(5,eventvo.getPeopleNumber());
			ps.setString(6,eventvo.getEventPlace());
			ps.setString(7,eventvo.getEventP2());
			ps.setString(8,eventvo.getEventSummary());
			ps.setString(9,eventvo.getEventDescribe());
			ps.setBytes(10,eventvo.getBigImg());
			ps.setBytes(11,eventvo.getSmallImg());
			ps.setBoolean(12,eventvo.getCollectType());
			ps.setBoolean(13,eventvo.getIsON());
			ps.setString(14,eventvo.getEventType());
			ps.setBoolean(15,eventvo.getNeedSeat());
			ps.setInt(16,eventvo.getSeatX());
			ps.setInt(17,eventvo.getSeatY());
			
			Statement stmt=conn.createStatement();
//			stmt.executeUpdate("set auto_increment_offset=1;");//自增主鍵-初始值
//			stmt.executeUpdate("set auto_increment_increment=1");//自增主鍵-遞增
			int rowCount = ps.executeUpdate();
			String eventNumber_ = null;
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				eventNumber_ = rs.getString(1);
				System.out.println("自增主鍵值= " + eventNumber_);
			}else {
				System.out.println("未取得自增主鍵值");
			}
			System.out.println(rowCount + " EVENT inserted!!");
			rs.close();
			
			//新增event class table
			EventClassJDBCDAO eventclassdao = new EventClassJDBCDAO();
			int evclasscount = 0;
			for(EventClassVO aeventclass:eventclasslist) {
				aeventclass.setEventNumber(Integer.valueOf(eventNumber_));
				int re =eventclassdao.insert(aeventclass, conn);
				evclasscount = evclasscount+re;
			}
			
			//新增Ticket table
			TicketJDBCDAO ticketdao = new TicketJDBCDAO();
			int ticketcount = 0;
			for(TicketVO aticket:ticketlist) {
				aticket.setEventNumber(Integer.valueOf(eventNumber_));
				int re =ticketdao.insert(aticket, conn);
				ticketcount = ticketcount+re;
			}
			
			//needSeat is true 新增seat table
			int seatcount = 0;
			if(eventvo.getNeedSeat()) {
				SeatJDBCDAO seatdao = new SeatJDBCDAO();
				for(SeatVO aseat:seatlist) {
					aseat.setEventNumber(Integer.valueOf(eventNumber_));
					int re = seatdao.insert(aseat, conn);
					seatcount = seatcount+re;
				}
			}
			
			conn.commit();
			conn.setAutoCommit(true);
			
			if(rowCount > 0 && evclasscount > 0 && ticketcount > 0 && seatcount > 0) {
				recount =1;
			}else if(rowCount > 0 && evclasscount > 0 && ticketcount > 0 && !eventvo.getNeedSeat()) {
				recount =1;
			}
			
		// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (conn != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-EventJDBCDAO");
					conn.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return recount;
	}

	@Override
	public List<EventVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventVO selectByeventNumber(Integer eventNumber) {
		EventVO eventvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(Common.driver);
			con = DriverManager.getConnection(Common.URL,Common.USER,Common.PASSWORD);
			pstmt = con.prepareStatement(selectByPKSQL);

			pstmt.setInt(1, eventNumber);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				eventvo = new EventVO();
				eventvo.setEventNumber(rs.getInt("eventNumber"));
				eventvo.setOrganizerNumber(rs.getInt("organizerNumber"));
				eventvo.setEventName(rs.getString("eventName"));
				eventvo.setEventStartDate(rs.getTimestamp("eventStartDate"));
				eventvo.setEventEndDate(rs.getTimestamp("eventEndDate"));
				eventvo.setPeopleNumber(rs.getInt("peopleNumber"));
				eventvo.setEventPlace(rs.getString("eventPlace"));
				eventvo.setEventP2(rs.getString("eventP2"));
				eventvo.setEventSummary(rs.getString("eventSummary"));
				eventvo.setEventDescribe(rs.getString("eventDescribe"));
				eventvo.setBigImg(rs.getBytes("bigImg"));
				eventvo.setSmallImg(rs.getBytes("smallImg"));
				eventvo.setCollectType(rs.getBoolean("collectType"));
				eventvo.setBanner(rs.getInt("banner"));
				eventvo.setFocus(rs.getInt("focus"));
				eventvo.setIsON(rs.getBoolean("isON"));
				eventvo.setEventType(rs.getString("eventType"));
				eventvo.setNeedSeat(rs.getBoolean("needSeat"));
				eventvo.setSeatX(rs.getInt("seatX"));
				eventvo.setSeatY(rs.getInt("seatY"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return eventvo;
	}

	@Override
	public EventVO selectByeventName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventVO> selectByorganizerNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventVO> selectByeventType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EventVO> selectByDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(EventVO eventvo,List<TicketVO> ticketlist,List<EventClassVO> eventclasslist,List<SeatVO> seatlist) {
		Connection con = null;
		PreparedStatement ps = null;
		int recount = 0;
		try {

			Class.forName(Common.driver);
			con = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);

			con.setAutoCommit(false);
			//修改event table
			ps = con.prepareStatement(eventUpdateSQL);

			ps.setString(1,eventvo.getEventName());
			ps.setTimestamp(2,eventvo.getEventStartDate());
			ps.setTimestamp(3,eventvo.getEventEndDate());
			ps.setInt(4,eventvo.getPeopleNumber());
			ps.setString(5,eventvo.getEventPlace());
			ps.setString(6,eventvo.getEventP2());
			ps.setString(7,eventvo.getEventSummary());
			ps.setString(8,eventvo.getEventDescribe());
			ps.setBytes(9,eventvo.getBigImg());
			ps.setBytes(10,eventvo.getSmallImg());
			ps.setBoolean(11,eventvo.getCollectType());
			ps.setBoolean(12,eventvo.getIsON());
			ps.setString(13,eventvo.getEventType());
			ps.setBoolean(14,eventvo.getNeedSeat());
			ps.setInt(15,eventvo.getSeatX());
			ps.setInt(16,eventvo.getSeatY());
			ps.setInt(17,eventvo.getEventNumber());

			int rowCount = ps.executeUpdate();

			System.out.println(rowCount + " EVENT updated!!");
			
			//修改event class table(先刪後新增)
			EventClassJDBCDAO eventclassdao = new EventClassJDBCDAO();
			int evclasscount = 0;
			eventclassdao.deleteByEventNumber(eventvo.getEventNumber(), con);
			for(EventClassVO aeventclass:eventclasslist) {
				aeventclass.setEventNumber(eventvo.getEventNumber());
				int re =eventclassdao.insert(aeventclass, con);
				evclasscount = evclasscount+re;
			}
			
			//修改Ticket table
			TicketJDBCDAO ticketdao = new TicketJDBCDAO();
			int ticketcount = 0;
			for(TicketVO aticket:ticketlist) {
				int re = 0;
				if(aticket.getTicketID() !=null) {
					re =ticketdao.update(aticket, con);
				}else {
					aticket.setEventNumber(eventvo.getEventNumber());
					re =ticketdao.insert(aticket, con);
				}
				ticketcount = ticketcount+re;
			}
			
			//needSeat is true 修改seat table
			int seatcount = 0;
			if(eventvo.getNeedSeat()) {
				SeatJDBCDAO seatdao = new SeatJDBCDAO();
				for(SeatVO aseat:seatlist) {
					aseat.setEventNumber(eventvo.getEventNumber());
					int re = seatdao.update(aseat, con);
					seatcount = seatcount+re;
				}
			}
			
			con.commit();
			con.setAutoCommit(true);
			
			if(rowCount > 0 && evclasscount > 0 && ticketcount > 0 && seatcount > 0) {
				recount =1;
			}else if(rowCount > 0 && evclasscount > 0 && ticketcount > 0 && !eventvo.getNeedSeat()) {
				recount =1;
			}

			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-EventJDBCDAO_update");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return recount;
	}
}
