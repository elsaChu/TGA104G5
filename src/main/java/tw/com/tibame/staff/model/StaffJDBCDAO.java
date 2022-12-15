package tw.com.tibame.staff.model;

import tw.com.tibame.util.common.Common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffJDBCDAO implements StaffDAO_interface {

	private static final String INSERT_STAFF = "INSERT INTO STAFF(staffName, staffAccount, staffPassword) values(?,?,?)";
	private static final String GET_ALL_STAFF = "SELECT * FROM STAFF";
	private static final String GET_ONE_STAFF = "SELECT staffNumber,staffName,staffAccount,staffPassword FROM STAFF where staffNumber = ?";
	private static final String UPDATE_STAFF = "UPDATE STAFF set staffName=?, staffAccount=?, staffPassword=? where staffNumber=?";
	private static final String DELETE_STAFF = "DELETE FROM STAFF where staffNumber = ?";
	private static final String GET_ONE_ACCOUNT = "SELECT staffAccount FROM STAFF where staffAccount = ?";

	@Override
	public void insert(StaffVO staffVO) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			System.out.println("insert jdbc");
			Class.forName(Common.driver);

			conn = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			pstmt = conn.prepareStatement(INSERT_STAFF);

			pstmt.setString(1, staffVO.getStaffName());
			pstmt.setString(2, staffVO.getStaffAccount());
			pstmt.setString(3, staffVO.getStaffPassword());
//			pstmt.setInt(4, staffVO.getStaffNumber());

			// 打包好用這個語法送出去
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public void update(StaffVO staffVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Common.driver);

			conn = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			pstmt = conn.prepareStatement(UPDATE_STAFF);

			pstmt.setString(1, staffVO.getStaffName());
			pstmt.setString(2, staffVO.getStaffAccount());
			pstmt.setString(3, staffVO.getStaffPassword());
			pstmt.setInt(4, staffVO.getStaffNumber());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	public StaffVO findByPrimaryKey(Integer staffNumber) {

		StaffVO staffVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Common.driver);
			conn = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			pstmt = conn.prepareStatement(GET_ONE_STAFF);
			System.out.println("staffNumber=" + staffNumber);
			pstmt.setInt(1, staffNumber);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// staffVo 也稱為 Domain objects
				staffVO = new StaffVO();
				staffVO.setStaffNumber(rs.getInt("staffNumber"));
				staffVO.setStaffName(rs.getString("staffName"));
				staffVO.setStaffAccount(rs.getString("staffAccount"));
				staffVO.setStaffPassword(rs.getString("staffPassword"));

			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return staffVO;
	}

	@Override
	public List<StaffVO> getAll() {
		List<StaffVO> list = new ArrayList<StaffVO>();
		// 初值
		StaffVO staffVO = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			System.out.println("select jdbc");
			Class.forName(Common.driver);

			conn = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			pstmt = conn.prepareStatement(GET_ALL_STAFF);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				staffVO = new StaffVO();
				staffVO.setStaffNumber(rs.getInt("staffNumber"));
//				System.out.println(staffVO.getStaffNumber());
				staffVO.setStaffName(rs.getString("staffName"));
				staffVO.setStaffAccount(rs.getString("staffAccount"));
				staffVO.setStaffPassword(rs.getString("staffPassword"));
				list.add(staffVO);
				// 將上述SET完成的vo，塞進去這個集合。Store the row in the list
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		}
		StaffVO s = list.get(0);
		System.out.println(s.getStaffName());
		return list;
	}

	@Override
	public void delete(Integer staffNumber) {

		Connection conn = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(Common.driver);

			conn = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			pstmt = conn.prepareStatement(DELETE_STAFF);
			pstmt.setInt(1, staffNumber);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public StaffVO findByStaffAccount(String staffAccount) {
		StaffVO staffVO = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(Common.driver);
			conn = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			pstmt = conn.prepareStatement(GET_ONE_ACCOUNT);

			pstmt.setString(1, staffAccount);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// staffVo 也稱為 Domain objects
				staffVO = new StaffVO();
//				staffVO.setStaffNumber(rs.getInt("staffNumber"));
//				staffVO.setStaffName(rs.getString("staffName"));
				staffVO.setStaffAccount(rs.getString("staffAccount"));
//				staffVO.setStaffPassword(rs.getString("staffPassword"));
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return staffVO;
	}

	@Override
	public List<StaffVO> findByStaffNumber(Integer staffNumber) {

		List<StaffVO> list = new ArrayList<StaffVO>();
		// 初值
		StaffVO staffVO = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			System.out.println("findByStaffNumber jdbc");
			Class.forName(Common.driver);

			conn = DriverManager.getConnection(Common.URL, Common.USER, Common.PASSWORD);
			pstmt = conn.prepareStatement(GET_ONE_STAFF);
			pstmt.setInt(1, staffNumber);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				staffVO = new StaffVO();
				staffVO.setStaffNumber(rs.getInt("staffNumber"));
				System.out.println(staffVO.getStaffNumber());
				staffVO.setStaffName(rs.getString("staffName"));
				staffVO.setStaffAccount(rs.getString("staffAccount"));
				staffVO.setStaffPassword(rs.getString("staffPassword"));
				list.add(staffVO);
				// 將上述SET完成的vo，塞進去這個集合。Store the row in the list
			}
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

		}
//		StaffVO s = list.get(0);
//		System.out.println(s.getStaffName());

		return list;

	}
}
