package com.organizer.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrganizerDAO implements OrganizerDAOinterface{
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TICK_IT_TEST?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
//
//	public OrganizerDAO() {
//		super();
//	}

	private static final String INSERT_STMT = 
		"INSERT INTO organizer (OAccount, Opassword, organizerName, windowName, windowphone, windowEmail) VALUES (?, ?, ? , ? , ? , ?)";
	private static final String GET_ALL_STMT = 
		"SELECT organizerNumber,organizerName FROM organizer order by organizerNumber";
	private static final String GET_ONE_STMT = 
		"SELECT organizerNumber,organizerName FROM organizer where organizerNumber = ?";
	private static final String DELETE = 
		"DELETE FROM organizer where organizerNumber = ?";
	private static final String UPDATE = 
		"UPDATE organizer set organizerName=? where organizerNumber = ?";

	@Override
	public void insert(OrganizerVO organizerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, organizerVO.getOAccount());
			pstmt.setString(2, organizerVO.getOpassword());
			pstmt.setString(3, organizerVO.getOrganizerName());
			pstmt.setString(4, organizerVO.getWindowName());
			pstmt.setString(5, organizerVO.getWindowPhone());
			pstmt.setString(6, organizerVO.getWindowEmail());
			pstmt.executeUpdate();
			System.out.println("insert done?");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	}
	

	@Override
	public void update(OrganizerVO organizerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, organizerVO.getOrganizerName());
			pstmt.setInt(2, organizerVO.getOrganizerNumber());

			pstmt.executeUpdate();

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

	}

	@Override
	public void delete(Integer organizerNumber) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, organizerNumber);

			pstmt.executeUpdate();

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

	}

	@Override
	public OrganizerVO findByPrimaryKey(Integer organizerNumber) {

		OrganizerVO organizerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, organizerNumber);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// organizerVO 也稱為 Domain objects
				organizerVO = new OrganizerVO();
				organizerVO.setOrganizerNumber(rs.getInt("organizerNumber"));
				organizerVO.setOrganizerName(rs.getString("organizerName"));
			
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
		return organizerVO;
	}

	@Override
	public List<OrganizerVO> selectAll() {
		List<OrganizerVO> list = new ArrayList<OrganizerVO>();
		OrganizerVO organizerVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// organizerVO 也稱為 Domain objects
				organizerVO = new OrganizerVO();
				organizerVO.setOrganizerNumber(rs.getInt("organizerNumber"));
				organizerVO.setOrganizerName(rs.getString("organizerName"));
				list.add(organizerVO); // Store the row in the list
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
		return list;
	}

//	public static void main(String[] args) {
//
//		OrganizerDAO dao = new OrganizerDAO();
//
//		// 新增
//		OrganizerVO organizerVO1 = new OrganizerVO();
//		organizerVO1.setOrganizerNumber(1222);
//		organizerVO1.setOrganizerName("吳永");
//		dao.insert(organizerVO1);
//
//		// 修改
//		organizerVO organizerVO2 = new organizerVO();
//		organizerVO2.setOrganizerNumber(7001);
//		organizerVO2.setOrganizerName("吳永志2");
//		dao.update(organizerVO2);
//
//		// 刪除
//		dao.delete(7014);
//
//		// 查詢
//		organizerVO organizerVO3 = dao.findByPrimaryKey(7001);
//		System.out.print(organizerVO3.getOrganizerNumber() + ",");
//		System.out.print(organizerVO3.getOrganizerName() + ",");
//		System.out.println("---------------------");
//
//		// 查詢
//		List<organizerVO> list = dao.selectAll();
//		for (organizerVO aEmp : list) {
//			System.out.print(aEmp.getOrganizerNumber() + ",");
//			System.out.print(aEmp.getOrganizerName() + ",");
//			System.out.println();
//		}
//	}

}
