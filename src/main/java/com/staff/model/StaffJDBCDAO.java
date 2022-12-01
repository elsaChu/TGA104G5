package com.staff.model;

import static conn.DBConnection.PASSWORD;
import static conn.DBConnection.URL;
import static conn.DBConnection.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StaffJDBCDAO implements StaffDAO_interface {

	private static final String INSERT_STAFF =
			"insert into staffVO(staffName, staffAccount, staffPassword) values(?,?,?)";

	@Override
	public void insert(staffVO staffVO) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			pstmt = conn.prepareStatement(INSERT_STAFF);

			pstmt.setString(1, staffVO.getStaffName());
			pstmt.setString(2, staffVO.getStaffAccount());
			pstmt.setString(3, staffVO.getStaffPassword());
			
			//打包好用這個語法送出去
			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
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

	}

	@Override
	public void update(staffVO staffVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public staffVO findByPrimaryKey(Integer staffNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<staffVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
