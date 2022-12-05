package com.product.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import static common.Common.driver;

public class ProductOrderDAOJndi implements ProductOrderDAO {
	
	private static DataSource dataSource = null;
	static {
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/TICK_IT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ProductOrderVO> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	private static final String SELECT_BY_NUMBER = "select * from PROD_ORDER where number = ?";

	@Override // 會員中心 - 查詢所有訂單
	public List<ProductOrderVO> getByNumber(Integer number) {
		List<ProductOrderVO> result = new ArrayList<>();
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(SELECT_BY_NUMBER)) {
			/*
			 * 當Statement關閉，ResultSet也會自動關閉，可以不需要將ResultSet宣告置入try with
			 * resources小括號內，參看ResultSet說明
			 */
			ps.setInt(1, number);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ProductOrderVO productOrderVO = new ProductOrderVO();
				productOrderVO.setProdOrderNo(rs.getInt("prodOrderNo"));
				productOrderVO.setAmountPrice(rs.getInt("amountPrice"));
				productOrderVO.setProdTotal(rs.getInt("prodTotal"));
				productOrderVO.setPaymentDate(rs.getTimestamp("paymentDate"));
				productOrderVO.setReceiverName(rs.getString("receiverName"));
				productOrderVO.setReceiverTel(rs.getString("receiverTel"));
				productOrderVO.setShippingAdd(rs.getString("shippingAdd"));
				productOrderVO.setProdOrderStatus(rs.getString("prodOrderStatus"));
				productOrderVO.setDeliveryStatus(rs.getString("deliveryStatus"));

				result.add(productOrderVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}

	private static final String SELECT_BY_PROD_ORDER_NO = "select * from PROD_ORDER where prodOrderNo = ?";

	@Override // 會員中心 - 查詢單筆訂單
	public ProductOrderVO getPrimaryKey(Integer prodOrderNo) {
		ProductOrderVO result = null;
		
		if(prodOrderNo != null) {
			ResultSet rs = null;
			try (Connection connection = dataSource.getConnection();
					PreparedStatement ps = connection.prepareStatement(SELECT_BY_PROD_ORDER_NO)) {
				
				ps.setInt(1, prodOrderNo);
				rs = ps.executeQuery();
				while (rs.next()) {
					result = new ProductOrderVO();
					result.setProdOrderNo(rs.getInt("prodOrderNo"));
					result.setNumber(rs.getInt("number"));
					result.setAmountPrice(rs.getInt("amountPrice"));
					result.setProdTotal(rs.getInt("prodTotal"));
					result.setPaymentDate(rs.getTimestamp("paymentDate"));
					result.setReceiverName(rs.getString("receiverName"));
					result.setReceiverTel(rs.getString("receiverTel"));
					result.setShippingAdd(rs.getString("shippingAdd"));
					result.setProdOrderStatus(rs.getString("prodOrderStatus"));
					result.setDeliveryStatus(rs.getString("deliveryStatus"));
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}	
		return result;
	}

	
	private static final String INSERT = 
			  "insert into PROD_ORDER (number, amountPrice, prodTotal, receiverName, receiverTel, shippingAdd) "
			  + "values	(?, ?, ?, ?, ?, ?)";
	@Override	// 新增訂單
	public void insert(ProductOrderVO productOrderVO) {
		if(productOrderVO != null) {
			try (
				Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(INSERT)) {
				
				ps.setInt(1, productOrderVO.getNumber());
				ps.setInt(2, productOrderVO.getAmountPrice());
				ps.setInt(3, productOrderVO.getProdTotal());
				ps.setString(4, productOrderVO.getReceiverName());
				ps.setString(5, productOrderVO.getReceiverTel());
				ps.setString(6, productOrderVO.getShippingAdd());
				
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		
	}

	private static final String UPDATE = "update PROD_ORDER set receiverName = ?, receiverTel = ?, shippingAdd = ?, prodOrderStatus = ?, deliveryStatus = ? where prodOrderNo = ?;";

	@Override	// 更新收件資訊及狀態
	public void update(ProductOrderVO productOrderVO) {
		if (productOrderVO != null && productOrderVO.getProdOrderNo() != null) {
			try (Connection connection = dataSource.getConnection();
					PreparedStatement ps = connection.prepareStatement(UPDATE)) {
				ps.setString(1, productOrderVO.getReceiverName());
				ps.setString(2, productOrderVO.getReceiverTel());
				ps.setString(3, productOrderVO.getShippingAdd());
				ps.setString(4, productOrderVO.getProdOrderStatus());
				ps.setString(5, productOrderVO.getDeliveryStatus());
				ps.setInt(6, productOrderVO.getProdOrderNo());

				ps.executeUpdate();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		// 測試與資料庫的連線
//		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
//			System.out.println("Connecting to MySQL successfully!!");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		// 以訂單編號查詢單筆訂單
//		ProductOrderJdbcDAO dao = new ProductOrderJdbcDAO();
//		ProductOrderVO productOrderVO = dao.getPrimaryKey(1);
//		System.out.println(productOrderVO.toString());
		
		//測試更新收件資訊及狀態
//		ProductOrderJdbcDAO dao = new ProductOrderJdbcDAO();
//		ProductOrderVO productOrderVO = new ProductOrderVO();
//		productOrderVO.setReceiverName("");
//		productOrderVO.setReceiverTel("0288888888");
//		productOrderVO.setShippingAdd("");
//		productOrderVO.setProdOrderStatus("");
//		productOrderVO.setDeliveryStatus("");
//		productOrderVO.setProdOrderNo(5);
//		
//		dao.update(productOrderVO);
//		System.out.println(productOrderVO.toString());
		
		// 新增訂單
//		ProductOrderJdbcDAO dao = new ProductOrderJdbcDAO();
//		ProductOrderVO productOrderVO = new ProductOrderVO();
//		productOrderVO.setNumber(3);
//		productOrderVO.setAmountPrice(3000);
//		productOrderVO.setProdTotal(2);
//		productOrderVO.setReceiverName("收件人");
//		productOrderVO.setReceiverTel("5555");
//		productOrderVO.setShippingAdd("address");
//		
//		dao.insert(productOrderVO);
//		System.out.println(productOrderVO.toString());
		
	}

}
