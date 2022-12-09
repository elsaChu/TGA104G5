package com.event.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EventClassJDBCDAO implements EventClassDAO_interface{
	private static final String evclassInsertSQL="insert into EVENT_CLASS value(?,?);";
	@Override
	public List<EventClassVO> selectByeventNumber() {

		return null;
	}

	@Override
	public int update() {

		return 0;
	}

	
	@Override
	public int insert(EventClassVO eventclassvo, Connection con) {
		PreparedStatement ps = null;
		int rowCount = 0;
		try {

     		ps = con.prepareStatement(evclassInsertSQL);
     		ps.setInt(1,eventclassvo.getEventNumber());
     		ps.setInt(2,eventclassvo.getEventClassNumber());
     		
//     		Statement stmt=con.createStatement();
     		rowCount = ps.executeUpdate();
     		System.out.println(rowCount + " EventClass inserted!!");
     		return rowCount;
		  // Handle any driver errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-EventClass");
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
}
