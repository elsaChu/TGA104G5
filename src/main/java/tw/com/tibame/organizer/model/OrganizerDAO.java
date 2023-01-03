package tw.com.tibame.organizer.model;

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

public class OrganizerDAO implements OrganizerDAOinterface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TICK_IT?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

//	public OrganizerDAO() {
//		super();
//	}
	
	private static final String INSERT_STMT = "INSERT INTO organizer (OAccount, Opassword, organizerName, windowName, windowPhone, windowEmail) VALUES (?, ?, ? , ? , ? , ?)";
	private static final String GET_ALL_STMT = "SELECT organizerNumber, OAccount, Opassword, organizerName, windowName, windowPhone, windowEmail, taxIDNumber, boss, organizerPhone  FROM organizer order by organizerNumber";
	private static final String DELETE = "DELETE FROM organizer where organizerNumber = ?";
	private static final String UPDATE2 = "UPDATE organizer set organizerName=?,windowName=?,windowPhone=?,windowEmail=?,taxIDNumber=?,boss=?,organizerPhone=?,accountName=?,accountNumber=?,bankCode=?,bankName=? where organizerNumber = ?";

	@Override
	public void insert(OrganizerVO organizerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			//可以考慮用DATASOURCE取代 
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	private static final String UPDATE = "UPDATE organizer SET organizerName= ?, OAccount = ? , Opassword = ? , windowName = ?, windowPhone = ? , windowEmail = ? "
			+ "where organizerNumber = ?";

	@Override
	public void update(OrganizerVO organizerVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, organizerVO.getOrganizerName());
			pstmt.setString(2, organizerVO.getOAccount());
			pstmt.setString(3, organizerVO.getOpassword());
			pstmt.setString(4, organizerVO.getWindowName());
			pstmt.setString(5, organizerVO.getWindowPhone());
			pstmt.setString(6, organizerVO.getWindowEmail());
			pstmt.setInt(7, organizerVO.getOrganizerNumber());
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

	}

	
private static final String GET_ONE_STMT = "SELECT * FROM organizer where organizerNumber = ?";

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
				organizerVO = new OrganizerVO();
				organizerVO.setOrganizerNumber(rs.getInt("organizerNumber"));
				organizerVO.setOrganizerName(rs.getString("organizerName"));
				organizerVO.setOAccount(rs.getString("OAccount"));
				organizerVO.setOpassword(rs.getString("Opassword"));
				organizerVO.setWindowName(	rs.getString("windowName"));
				organizerVO.setWindowEmail(rs.getString("windowEmail"));
				organizerVO.setWindowPhone(rs.getString("windowPhone"));

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
	
	private static final String GET_ONE_VO = "SELECT * FROM organizer where OAccount = ?";

	@Override
	public OrganizerVO findByAccount(String accountName) {

		OrganizerVO organizerVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_VO);

			pstmt.setString(1, accountName);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				organizerVO = new OrganizerVO();
				organizerVO.setOrganizerNumber(rs.getInt("organizerNumber"));
				organizerVO.setOrganizerName(rs.getString("organizerName"));
				organizerVO.setOAccount(rs.getString("OAccount"));
				organizerVO.setOpassword(rs.getString("Opassword"));
				organizerVO.setWindowName(	rs.getString("windowName"));
				organizerVO.setWindowEmail(rs.getString("windowEmail"));
				organizerVO.setWindowPhone(rs.getString("windowPhone"));
				organizerVO.setTaxIDNumber(rs.getString("taxIDNumber"));
				organizerVO.setBoss(rs.getString("boss"));
				organizerVO.setOrganizerPhone(rs.getString("organizerPhone"));
				
				organizerVO.setAccountName(rs.getString("accountName"));
				organizerVO.setAccountNumber(rs.getString("accountNumber"));
				organizerVO.setBankCode(rs.getString("bankCode"));
				organizerVO.setBankName(rs.getString("bankName"));

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
				organizerVO.setOAccount(rs.getString("OAccount"));
				organizerVO.setOpassword(rs.getString("Opassword"));
				organizerVO.setOrganizerName(rs.getString("organizerName"));
				organizerVO.setOrganizerNumber(rs.getInt("organizerNumber"));
				organizerVO.setWindowName(rs.getString("windowName"));
				organizerVO.setWindowEmail(rs.getString("windowEmail"));
				organizerVO.setWindowPhone(rs.getString("windowPhone"));
				organizerVO.setTaxIDNumber(rs.getString("taxIDNumber"));
				organizerVO.setBoss(rs.getString("boss"));
				organizerVO.setOrganizerPhone(rs.getString("organizerPhone"));
				list.add(organizerVO); // Store the row in the list
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
	
	private static final String SEARCH_ALL = 
			"SELECT organizerNumber, OAccount, Opassword, organizerName, windowName, windowPhone, windowEmail " 
			+"FROM organizer "
			//? 放在""與%%旁邊會無法識別，使用|| 來escape這樣的語法問題 (escape syntax)	
			+ "where organizerName like  CONCAT('%', ? , '%') " 
//			+"where organizerNumber like  '%'||?||'%' ";
			 +"   or windowName like CONCAT('%', ? , '%') "
			 +"   or windowEmail like CONCAT('%', ? , '%') "
			+ "	or OAccount like CONCAT('%', ? , '%') ";
//			 +"   or windowPhone like '%'||?||'%' ";
	public List<OrganizerVO> searchOrganizerByAll(String searchString) {
		List<OrganizerVO> list1 = new ArrayList<OrganizerVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SEARCH_ALL);
			//set String into "like" in sql 
			pstmt.setString(1, searchString);
			pstmt.setString(2, searchString);
			pstmt.setString(3, searchString);
			pstmt.setString(4, searchString);
//			pstmt.setString(5, searchString);
//			pstmt.setString(6, searchString);
			System.out.println("Keyword: " + searchString + " executeQuery()");
			rs = pstmt.executeQuery();
			System.out.println("Matched Results: ");
			while (rs.next()) {
				System.out.println("runnin in rs.next");
				OrganizerVO bean = new OrganizerVO();
				bean.setOrganizerNumber(rs.getInt("organizerNumber"));
				bean.setOrganizerName(rs.getString("organizerName"));
//				bean.setOAccount(rs.getString("OAccount"));
//				bean.setOpassword(rs.getString("Opassword"));
//				bean.setWindowName(	rs.getString("windowName"));
//				bean.setWindowEmail(rs.getString("windowEmail"));
//				bean.setWindowPhone(rs.getString("windowPhone"));
				list1.add(bean); // Store the row in the list
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}

		return list1;
	};

//	public static void main(String[] args) {
//}
	
	@Override
	public void update2(OrganizerVO organizerVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			//可以考慮用DATASOURCE取代 
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE2);
			
			pstmt.setString(1, organizerVO.getOrganizerName());
			pstmt.setString(2, organizerVO.getWindowName());
			pstmt.setString(3, organizerVO.getWindowPhone());
			pstmt.setString(4, organizerVO.getWindowEmail());
			pstmt.setString(5, organizerVO.getTaxIDNumber());
			pstmt.setString(6, organizerVO.getBoss());
			pstmt.setString(7, organizerVO.getOrganizerPhone());
			
			pstmt.setString(8, organizerVO.getAccountName());
			pstmt.setString(9, organizerVO.getAccountNumber());
			pstmt.setString(10, organizerVO.getBankCode());
			pstmt.setString(11, organizerVO.getBankName());
			
			pstmt.setInt(12, organizerVO.getOrganizerNumber());
			
			pstmt.executeUpdate();
			System.out.println("我到這裡了");
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		}catch (SQLException se) {
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	
	private static String updateActivate = "UPDATE organizer set OPass=1 where OAccount = ?";
	@Override
	public void updateActivateStatus(String account) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(updateActivate);
			pstmt.setString(1, account);
			pstmt.executeUpdate();
			System.out.println("activated organizer(from DAO)");
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		}catch (SQLException se) {
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	private static final String GET_ONE_VO2 = "SELECT * FROM organizer where OAccount = ?";

	@Override
	public Boolean isActivated(String accountName) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Boolean isActivated = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_VO2);

			pstmt.setString(1, accountName);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				System.out.println("activated is true or false(fromDAO)");
				isActivated = rs.getBoolean("OPass");
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return isActivated;
	}
	


}
