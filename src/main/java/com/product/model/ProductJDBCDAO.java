package com.product.model;

import static conn.DBConnection.DRIVER;
import static conn.DBConnection.PASSWORD;
import static conn.DBConnection.URL;
import static conn.DBConnection.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ProductJDBCDAO implements ProductDAO_interface{
	String insertSQL = 
			"insert into `PRODUCT`(eventNumber,organizerNumber,prodName, prodSpec,unitPrice,prodStock,prodDetails,isPOn)"
			+ "values(?,?,?,?,?,?,?,?)";

	@Override
	public OrderDetailVO getPrimaryKey(Integer prodNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(ProductVO prodvo) {
		Connection conn = null;
		PreparedStatement ps = null;
		int rowCount = 0;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL,USER,PASSWORD);
			ps = conn.prepareStatement(insertSQL);
			
			ps.setInt(1,prodvo.getEventNumber());
			ps.setInt(2,prodvo.getOrganizerNumber());
			ps.setString(3,prodvo.getProdName());
			ps.setString(4,prodvo.getProdSpec());
			ps.setInt(5,prodvo.getUnitPrice());
			ps.setInt(6,prodvo.getProdStock());
			ps.setString(7,prodvo.getProdDetails());
			ps.setBoolean(8,prodvo.getIsPOn());
			rowCount = ps.executeUpdate();
			
			System.out.println(rowCount + " row(s) inserted!!");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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

	@Override
	public void update(ProductVO productVO) {
		
	}

	@Override
	public boolean delete(Integer prodNo) {
		return false;
	}

	@Override
	public List<ProductVO> getAll() {
		return null;
	}
}