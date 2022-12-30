package tw.com.tibame.product.model;

import java.sql.*;
import java.util.*;

public class ProductJDBCDAO implements ProductDAO_interface {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TICK_IT?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";
	
//	SQL syntax: insert
	private static final String INSERT_STMT = "INSERT INTO `PRODUCT`(eventNumber, organizerNumber, prodName, prodSpec, unitPrice, prodStock, prodDetails, isPOn) VALUES (?,?,?,?,?,?,?,?)";
	
	@Override
	public int insert(ProductVO prodVo, ProductImageVO prodimgvo) {
//		System.out.println(prodVo.toString());
		int rowCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.setAutoCommit(false);
			String cols[] = {"PRODUCT"};
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setInt(1, prodVo.getEventNumber());
			pstmt.setInt(2, prodVo.getOrganizerNumber());
			pstmt.setString(3, prodVo.getProdName());
			pstmt.setString(4, prodVo.getProdSpec());
			pstmt.setInt(5, prodVo.getUnitPrice());
			pstmt.setInt(6, prodVo.getProdStock());
			pstmt.setString(7, prodVo.getProdDetails());
			pstmt.setBoolean(8, prodVo.getIsPOn());
			rowCount = pstmt.executeUpdate();
			String next_prodno = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_prodno = rs.getString(1);
				System.out.println("自增主鍵值=" + next_prodno);
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			System.out.println(rowCount + " row(s) inserted!");
			ProductImageJdbcDAO dao = new ProductImageJdbcDAO();
			prodimgvo.setProdNo(Integer.valueOf(next_prodno));
			dao.insert(prodimgvo, con);
			
			con.commit();
			con.setAutoCommit(true);

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
		return rowCount;
	}

//	SQL syntax: update
	private static final String UPDATE_STMT = "UPDATE `PRODUCT` SET eventNumber=?, organizerNumber=?, prodName=?, prodSpec=?, unitPrice=?, prodStock=?, prodDetails=?, isPOn=? WHERE prodNo=?";
	
	@Override
	public int update(ProductVO prodVO) {

		int rowCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
			pstmt.setInt(1, prodVO.getEventNumber());
			pstmt.setInt(2, prodVO.getOrganizerNumber());
			pstmt.setString(3, prodVO.getProdName());
			pstmt.setString(4, prodVO.getProdSpec());
			pstmt.setInt(5, prodVO.getUnitPrice());
			pstmt.setInt(6, prodVO.getProdStock());
			pstmt.setString(7, prodVO.getProdDetails());
			pstmt.setBoolean(8, prodVO.getIsPOn());
			pstmt.setInt(9, prodVO.getProdNo());
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
		return rowCount;
	}
	
//	SQL syntax: delete
//	private static final String DELETE_STMT = "DELETE FROM `PRODUCT` WHERE prodNo = ?";
	
//	@Override
//	public int delete(Integer prodNo) {
//
//		int rowCount = 0;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(DELETE_STMT);
//			pstmt.setInt(1, prodNo);
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return rowCount;
//	}

//	SQL syntax: select
	private static final String SELECT_BY_PRODUCTNO = "SELECT prodNo, eventNumber, organizerNumber, prodName, prodSpec, unitPrice, prodStock, prodDetails, prodScore, isPOn FROM `PRODUCT` WHERE prodNo=?";

	@Override
	public ProductVO findByPrimaryKey(Integer prodNo) {

		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_BY_PRODUCTNO);
			pstmt.setInt(1, prodNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setProdNo(rs.getInt("prodNo"));
				productVO.setEventNumber(rs.getInt("eventNumber"));
				productVO.setOrganizerNumber(rs.getInt("organizerNumber"));
				productVO.setProdName(rs.getString("prodName"));
				productVO.setProdSpec(rs.getString("prodSpec"));
				productVO.setUnitPrice(rs.getInt("unitPrice"));
				productVO.setProdStock(rs.getInt("prodStock"));
				productVO.setProdDetails(rs.getString("prodDetails"));
				productVO.setProdScore(rs.getFloat("prodScore"));
				productVO.setIsPOn(rs.getBoolean("isPOn"));
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
		return productVO;
	}
	
//	@Override
//	public ProductVO findByProductName(String prodName) {
//
//		ProductVO productVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(SELECT_BY_PRODUCTNAME);
//			pstmt.setString(1, prodName);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				productVO = new ProductVO();
//				productVO.setProdNo(rs.getInt("prodNo"));
//				productVO.setEventNumber(rs.getInt("eventNumber"));
//				productVO.setOrganizerNumber(rs.getInt("organizerNumber"));
//				productVO.setProdName(rs.getString("prodName"));
//				productVO.setProdSpec(rs.getString("prodSpec"));
//				productVO.setUnitPrice(rs.getInt("unitPrice"));
//				productVO.setProdStock(rs.getInt("prodStock"));
//				productVO.setProdDetails(rs.getString("prodDetails"));
//				productVO.setProdScore(rs.getFloat("prodScore"));
//				productVO.setIsPOn(rs.getBoolean("isPOn"));
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return productVO;
//	}

//	SQL syntax: select
	private static final String SELECT_BY_PRODUCTNAME = "SELECT prodNo, eventNumber, organizerNumber, prodName, prodSpec, unitPrice, prodStock, prodDetails, prodScore, isPOn FROM `PRODUCT` WHERE INSTR(prodName, ?)";
	
	@Override
	public List<ProductVO> findByProductName(String pdname) {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO prodvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_BY_PRODUCTNAME);
			pstmt.setString(1, pdname);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prodvo = new ProductVO();
				prodvo.setProdNo(rs.getInt("prodNo"));
				prodvo.setEventNumber(rs.getInt("eventNumber"));
				prodvo.setOrganizerNumber(rs.getInt("organizerNumber"));
				prodvo.setProdName(rs.getString("prodName"));
				prodvo.setProdSpec(rs.getString("prodSpec"));
				prodvo.setUnitPrice(rs.getInt("unitPrice"));
				prodvo.setProdStock(rs.getInt("prodStock"));
				prodvo.setProdDetails(rs.getString("prodDetails"));
				prodvo.setProdScore(rs.getFloat("prodScore"));
				prodvo.setIsPOn(rs.getBoolean("isPOn"));
				list.add(prodvo); // Store the row in the list
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
	

	//	SQL syntax: select
	private static final String LIST_ALL_STMT = "SELECT prodNo,eventNumber ,organizerNumber,prodName,prodSpec,unitPrice,prodStock, prodDetails, prodScore, isPOn FROM `PRODUCT` ORDER BY prodNo DESC";
	
	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO prodvo = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(LIST_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				prodvo = new ProductVO();
				prodvo.setProdNo(rs.getInt("prodNo"));
				prodvo.setEventNumber(rs.getInt("eventNumber"));
				prodvo.setOrganizerNumber(rs.getInt("organizerNumber"));
				prodvo.setProdName(rs.getString("prodName"));
				prodvo.setProdSpec(rs.getString("prodSpec"));
				prodvo.setUnitPrice(rs.getInt("unitPrice"));
				prodvo.setProdStock(rs.getInt("prodStock"));
				prodvo.setProdDetails(rs.getString("prodDetails"));
				prodvo.setProdScore(rs.getFloat("prodScore"));
				prodvo.setIsPOn(rs.getBoolean("isPOn"));
				list.add(prodvo); // Store the row in the list
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

//	run a test to check if the program can access to the database
//	public static void main(String[] args) {
//		ProductJDBCDAO dao = new ProductJDBCDAO();
//
//		// insert
//		ProductVO productVO1 = new ProductVO();
//		productVO1.setEventNumber(4);
//		productVO1.setOrganizerNumber(2);
//		productVO1.setProdName("印花T-Shirt");
//		productVO1.setProdSpec("XL");
//		productVO1.setUnitPrice(500);
//		productVO1.setProdStock(50);
//		productVO1.setProdDetails("限量商品，售完為止");
//		productVO1.setIsPOn(true);
//		int count1 = dao.insert(productVO1);
//		System.out.println("inserted" + count1 + " rows");//
//		System.out.println("---------------------------------");
//
//		// update
//		ProductVO productVO2 = new ProductVO();
//		productVO2.setEventNumber(4);
//		productVO2.setOrganizerNumber(2);
//		productVO2.setProdName("海報2號");
//		productVO2.setProdSpec("大");
//		productVO2.setUnitPrice(350);
//		productVO2.setProdStock(150);
//		productVO2.setProdDetails("獨家限量海報，售完為止");
//		productVO2.setIsPOn(true);
//		productVO2.setProdNo(4);
//		int count2 = dao.update(productVO2);
//		System.out.println("updated" + count2 + " rows");//
//		System.out.println("---------------------------------");
//
//		// delete
//		int count3 = dao.delete(5);
//		System.out.println("deleted" + count3 + " rows");//
//		System.out.println("---------------------------------");
//
//		// find by PrimaryKey
//		ProductVO productVO3 = dao.findByPrimaryKey(2);
//		System.out.print(productVO3.getProdNo() + ",");
//		System.out.print(productVO3.getEventNumber() + ",");
//		System.out.print(productVO3.getOrganizerNumber() + ",");
//		System.out.print(productVO3.getProdName() + ",");
//		System.out.print(productVO3.getProdSpec() + ",");
//		System.out.print(productVO3.getUnitPrice() + ",");
//		System.out.println(productVO3.getProdStock() + ",");
//		System.out.println(productVO3.getProdDetails() + ",");
//		System.out.println(productVO3.getProdScore() + ",");
//		System.out.println(productVO3.getIsPOn());
//		System.out.println("---------------------------------");
//
//		// select
//		List<ProductVO> list = dao.getAll();
//		for (ProductVO aProd : list) {
//			System.out.print(aProd.getProdNo() + ",");
//			System.out.print(aProd.getEventNumber() + ",");
//			System.out.print(aProd.getOrganizerNumber() + ",");
//			System.out.print(aProd.getProdName() + ",");
//			System.out.print(aProd.getProdSpec() + ",");
//			System.out.print(aProd.getUnitPrice() + ",");
//			System.out.print(aProd.getProdStock() + ",");
//			System.out.print(aProd.getProdDetails() + ",");
//			System.out.print(aProd.getProdScore() + ",");
//			System.out.print(aProd.getIsPOn());
//			System.out.println();
//		}
//	}
}