package tw.com.tibame.product.model;

import java.sql.*;
import java.util.*;

public class ProductOrderJdbcDAO implements ProductOrderDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/TICK_IT?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "password";

//	SQL syntax: select
	private static final String SELECT_BY_PRODUCTORDERNO = "SELECT prodOrderNo, number, amountPrice, prodTotal, paymentDate, receiverName, receiverTel, shippingAdd, prodOrderStatus, deliveryStatus FROM `PROD_ORDER` WHERE prodOrderNo=?";

	@Override
	public ProductOrderVO findByPrimaryKey(Integer prodOrderNo) {
		ProductOrderVO productorderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(SELECT_BY_PRODUCTORDERNO);
			pstmt.setInt(1, prodOrderNo);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productorderVO = new ProductOrderVO();
				productorderVO.setProdOrderNo(rs.getInt("prodOrderNo"));
				productorderVO.setNumber(rs.getInt("number"));
				productorderVO.setAmountPrice(rs.getInt("amountPrice"));
				productorderVO.setProdTotal(rs.getInt("prodTotal"));
				productorderVO.setPaymentDate(rs.getTimestamp("paymentDate"));
				productorderVO.setReceiverName(rs.getString("receiverName"));
				productorderVO.setReceiverTel(rs.getString("receiverTel"));
				productorderVO.setShippingAdd(rs.getString("shippingAdd"));
				productorderVO.setProdOrderStatus(rs.getString("prodOrderStatus"));
				productorderVO.setDeliveryStatus(rs.getString("deliveryStatus"));
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
		return productorderVO;
	}

	// SQL syntax: select
	private static final String LIST_ALL_STMT = "SELECT prodOrderNo, number, amountPrice, prodTotal, paymentDate, receiverName, receiverTel, shippingAdd, prodOrderStatus, deliveryStatus FROM `PROD_ORDER` ORDER BY prodOrderNo DESC";

	@Override
	public List<ProductOrderVO> getAll() {
		List<ProductOrderVO> list = new ArrayList<ProductOrderVO>();
		ProductOrderVO productorderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(LIST_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				productorderVO = new ProductOrderVO();
				productorderVO.setProdOrderNo(rs.getInt("prodOrderNo"));
				productorderVO.setNumber(rs.getInt("number"));
				productorderVO.setAmountPrice(rs.getInt("amountPrice"));
				productorderVO.setProdTotal(rs.getInt("prodTotal"));
				productorderVO.setPaymentDate(rs.getTimestamp("paymentDate"));
				productorderVO.setReceiverName(rs.getString("receiverName"));
				productorderVO.setReceiverTel(rs.getString("receiverTel"));
				productorderVO.setShippingAdd(rs.getString("shippingAdd"));
				productorderVO.setProdOrderStatus(rs.getString("prodOrderStatus"));
				productorderVO.setDeliveryStatus(rs.getString("deliveryStatus"));
				list.add(productorderVO); // Store the row in the list
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
}
