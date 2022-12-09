package com.event.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TicketJDBCDAO implements TicketDAO_interface{
	private static final String ticketInsertSQL = "insert into TICKET(ticketName,eventNumber,price,limitTicket,ticketQuantity,ticketStartTime,ticketEndTime,ticketMIN,ticketMAX,ticketType) value(?,?,?,?,?,?,?,?,?,?);";
	@Override
	public int insert(TicketVO ticketvo, Connection con) {
		PreparedStatement ps = null;
		int rowCount = 0;
		try {
			ps = con.prepareStatement(ticketInsertSQL);
     		ps.setString(1,ticketvo.getTicketName());
     		ps.setInt(2,ticketvo.getEventNumber());
     		ps.setInt(3,ticketvo.getPrice());
     		ps.setBoolean(4,ticketvo.getLimitTicket());
     		ps.setInt(5,ticketvo.getTicketQuantity());
     		ps.setTimestamp(6,ticketvo.getTicketStartTime());
     		ps.setTimestamp(7,ticketvo.getTicketEndTime());
     		ps.setInt(8,ticketvo.getTicketMIN());
     		ps.setInt(9,ticketvo.getTicketMAX());
     		ps.setString(10,ticketvo.getTicketType());
     		
     		rowCount = ps.executeUpdate();
     		System.out.println(rowCount + " Ticket inserted!!");
     		return rowCount;
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-Ticket");
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
		}
		
	}

	@Override
	public int update() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<TicketVO> selectByeventNumber() {
		// TODO Auto-generated method stub
		return null;
	}

}
