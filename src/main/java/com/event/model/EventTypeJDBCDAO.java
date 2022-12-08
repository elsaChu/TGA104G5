package com.event.model;

import static conn.DBConnection.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventTypeJDBCDAO implements EventTypeDAO_interface{

	@Override
	public int insert(EventTypeVO eventTypevo) {

		return 0;
	}

	@Override
	public int update(EventTypeVO eventTypevo) {

		return 0;
	}
	
	String sqlall = "select * from `EVENT_TYPE` where `eventClassState` != ?;";
	@Override
	public List<EventTypeVO> selectTypeIsON() {
		List<EventTypeVO> list = new ArrayList<EventTypeVO>();
		EventTypeVO eventtypevo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = con.prepareStatement(sqlall);
			
			pstmt.setBoolean(1,false);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				eventtypevo = new EventTypeVO();
				eventtypevo.setEventClassNumber(rs.getInt(1));
				eventtypevo.setEventClassName(rs.getString(2));
				eventtypevo.setEventClassState(rs.getBoolean(3));
				list.add(eventtypevo);
			}
			

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return list;
	}

	@Override
	public List<EventTypeVO> selectByEventNumber(int eventNumber) {

		return null;
	}

	@Override
	public List<EventTypeVO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
