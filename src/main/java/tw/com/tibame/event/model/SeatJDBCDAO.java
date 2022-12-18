package tw.com.tibame.event.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SeatJDBCDAO implements SeatDAO_interface{
	private static final String seatInsertSQL = "insert into SEAT(eventNumber,seatNumber,seatSet) value(?,?,?);";
	private static final String seatUpdateSQL = "update SEAT set seatNumber=?, seatSet=? where eventNumber=?;";
	@Override
	public int insert(SeatVO aseat,Connection conn) {
		PreparedStatement ps = null;
		int rowCount = 0;
		try {
			ps = conn.prepareStatement(seatInsertSQL);
			ps.setInt(1, aseat.getEventNumber());
			ps.setInt(2, aseat.getSeatNumber());
			ps.setInt(3,aseat.getSeatSet());
			
			rowCount = ps.executeUpdate();
			System.out.println(rowCount + " Seat inserted!!");
			return rowCount;
		} catch (SQLException se) {
			if (conn != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-Seat");
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
		}
	}

	@Override
	public int update(SeatVO aseat,Connection conn) {
		PreparedStatement ps = null;
		int rowCount = 0;
		try {
			ps = conn.prepareStatement(seatUpdateSQL);
			ps.setInt(1, aseat.getSeatNumber());
			ps.setInt(2,aseat.getSeatSet());
			ps.setInt(3, aseat.getEventNumber());

     		rowCount=ps.executeUpdate();
     		return rowCount;
			// Handle any driver errors
		} catch (SQLException se) {
			if (conn != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-SEAT_update");
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
		}
	}

	@Override
	public int updateByseatType() {

		return 0;
	}

	@Override
	public SeatVO eventNumber() {

		return null;
	}

}
