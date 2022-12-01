package com.event.model;

import static conn.DBConnection.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

public class EventJDBCDAO implements EventDAO_interface {
	String insertSQL = "insert into `EVENT`(organizerNumber,eventName,eventStartDate,eventEndDate,"
			+ "peopleNumber,eventPlace,eventP2,eventSummary,eventDescribe,bigImg,smallImg,"
			+ "collectType,isON,eventType,needSeat,seatX,seatY) " + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

	@Override
	public int insert(EventVO eventvo) {
		Connection conn = null;
		PreparedStatement ps = null;
		int rowCount = 0;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			ps = conn.prepareStatement(insertSQL);
			
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
			rowCount = ps.executeUpdate();
			
			System.out.println(rowCount + " row(s) inserted!!");
			
			
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
		return rowCount;
	}
}
