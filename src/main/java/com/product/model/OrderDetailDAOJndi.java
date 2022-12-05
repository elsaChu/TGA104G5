package com.product.model;

import static common.Common.driver;
import static common.Common.PASSWORD;
import static common.Common.URL;
import static common.Common.USER;

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

public class OrderDetailDAOJndi implements OrderDetailDAO{

	private static DataSource dataSource = null;
	static {
		try {
			Context ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/TICK_IT");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String SELECT_ALL = 
			"select itemNo, prodOrderNo, prodNo, prodQty, subtotal, commentRanking, commentContent, commentDate, returnReason, refundStatus, refundSDate, refundEDate from ORDER_DETAIL";
	@Override
	public List<OrderDetailVO> getAll() {
		List<OrderDetailVO> result = new ArrayList<>();
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (
			Connection connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(SELECT_ALL)) {
			/*
			 * 當Statement關閉，ResultSet也會自動關閉，可以不需要將ResultSet宣告置入try with
			 * resources小括號內，參看ResultSet說明
			 */
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				OrderDetailVO orderDetailVO = new OrderDetailVO();
				orderDetailVO.setItemNo(rs.getInt("itemNo"));
				orderDetailVO.setProdOrderNo(rs.getInt("prodOrderNo"));
				orderDetailVO.setProdNo(rs.getInt("prodNo"));
				orderDetailVO.setProdQty(rs.getInt("prodQty"));
				orderDetailVO.setSubtotal(rs.getInt("subtotal"));
				orderDetailVO.setCommentRanking(rs.getFloat("commentRanking"));
				orderDetailVO.setCommentContent(rs.getString("commentContent"));
				orderDetailVO.setCommentDate(rs.getTimestamp("commentDate"));
				orderDetailVO.setReturnReason(rs.getString("returnReason"));
				orderDetailVO.setRefundStatus(rs.getString("refundStatus"));
				orderDetailVO.setRefundSDate(rs.getDate("refundSDate"));
				orderDetailVO.setRefundEDate(rs.getDate("refundEDate"));
				
				result.add(orderDetailVO);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	private static final String SELECT_BY_ID =
			"select itemNo, prodOrderNo, prodNo, prodQty, subtotal, commentRanking, commentContent, commentDate, returnReason, refundStatus, refundSDate, refundEDate from ORDER_DETAIL where itemNo = ?";
	@Override
	public OrderDetailVO getPrimaryKey(Integer itemNo) {
		OrderDetailVO result = null;
		
		if(itemNo != null) {
			ResultSet rs = null;
			try (
				Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID)){
				
				ps.setInt(1, itemNo);
				rs = ps.executeQuery();
				if(rs.next()) {
					result = new OrderDetailVO();
					result.setItemNo(rs.getInt("itemNo"));
					result.setProdOrderNo(rs.getInt("prodOrderNo"));
					result.setProdNo(rs.getInt("prodNo"));
					result.setProdQty(rs.getInt("prodQty"));
					result.setSubtotal(rs.getInt("subtotal"));
					result.setCommentRanking(rs.getFloat("commentRanking"));
					result.setCommentContent(rs.getString("commentContent"));
					result.setCommentDate(rs.getTimestamp("commentDate"));
					result.setReturnReason(rs.getString("returnReason"));
					result.setRefundStatus(rs.getString("refundStatus"));
					result.setRefundSDate(rs.getDate("refundSDate"));
					result.setRefundEDate(rs.getDate("refundEDate"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		return result;
	}

	private static final String SELECT_BY_PROD_ODER_NO = "select * from ORDER_DETAIL where prodOrderNo = ?";
	@Override  // 以訂單編號查詢單筆訂單
	public List<OrderDetailVO> getByProdOrderNo(Integer prodOrderNo) {
		List<OrderDetailVO> result = new ArrayList<>();
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (
			Connection connection = dataSource.getConnection();
			PreparedStatement ps = connection.prepareStatement(SELECT_BY_PROD_ODER_NO)) {
			/*
			 * 當Statement關閉，ResultSet也會自動關閉，可以不需要將ResultSet宣告置入try with
			 * resources小括號內，參看ResultSet說明
			 */
			ps.setInt(1, prodOrderNo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				OrderDetailVO orderDetailVO = new OrderDetailVO();
				orderDetailVO.setItemNo(rs.getInt("itemNo"));
				orderDetailVO.setProdOrderNo(rs.getInt("prodOrderNo"));
				orderDetailVO.setProdNo(rs.getInt("prodNo"));
				orderDetailVO.setProdQty(rs.getInt("prodQty"));
				orderDetailVO.setSubtotal(rs.getInt("subtotal"));
				orderDetailVO.setCommentRanking(rs.getFloat("commentRanking"));
				orderDetailVO.setCommentContent(rs.getString("commentContent"));
				orderDetailVO.setCommentDate(rs.getTimestamp("commentDate"));
				orderDetailVO.setReturnReason(rs.getString("returnReason"));
				orderDetailVO.setRefundStatus(rs.getString("refundStatus"));
				orderDetailVO.setRefundSDate(rs.getDate("refundSDate"));
				orderDetailVO.setRefundEDate(rs.getDate("refundEDate"));
				
				result.add(orderDetailVO);	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	private static final String INSERT = 
			  "insert into ORDER_DETAIL (prodOrderNo, prodNo, prodQty, subtotal) values (?, ?, ?, ?)";
	@Override
	public void insert(OrderDetailVO orderDetailVO) {
		if(orderDetailVO != null) {
			try (
				Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(INSERT)) {
				
				ps.setInt(1, orderDetailVO.getProdOrderNo());
				ps.setInt(2, orderDetailVO.getProdNo());
				ps.setInt(3, orderDetailVO.getProdQty());
				ps.setInt(4, orderDetailVO.getSubtotal());
				
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		
	}
	
	private static final String UPDATE = 
			"update ORDER_DETAIL set commentRanking = ?, commentContent = ?, commentDate = ?, returnReason = ?, refundStatus = ?, refundSDate = ?, refundEDate = ? where itemNo = ?";  
	@Override
	public void update(OrderDetailVO orderDetailVO) {
		if(orderDetailVO != null && orderDetailVO.getProdNo() != null) {
			try (
				Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(UPDATE)) {
				ps.setFloat(1, orderDetailVO.getCommentRanking());
				ps.setString(2, orderDetailVO.getCommentContent());
				ps.setTimestamp(3, orderDetailVO.getCommentDate());
				ps.setString(4, orderDetailVO.getReturnReason());
				ps.setString(5, orderDetailVO.getRefundStatus());
				ps.setDate(6, orderDetailVO.getRefundSDate());
				ps.setDate(7, orderDetailVO.getRefundEDate());
				ps.setInt(8, orderDetailVO.getItemNo());
				
				ps.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		
	}


	
	

	
	private static final String DELETE = 
			"delete from ORDER_DETAIL where itemNo=?";  
	@Override
	public boolean delete(Integer itemNo) {
		if(itemNo != null) {
			try (
				Connection connection = dataSource.getConnection();
				PreparedStatement ps = connection.prepareStatement(DELETE)) {
				ps.setInt(1, itemNo);
				
				int rowCount = ps.executeUpdate();
				if(rowCount == 1) {
					System.out.println(rowCount);
					return true;
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return false;
	}
	
	public static void main(String[] args) {

		// 測試與資料庫的連線
		try(Connection connection = dataSource.getConnection()) {
			System.out.println("Connecting to MySQL successfully!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// PK 單一查詢
//		OrderDetailJdbcDAO dao = new OrderDetailJdbcDAO();
//		OrderDetailVO orderDetailVO = dao.getPrimaryKey(3);
//		System.out.println(orderDetailVO.toString());

		// 以訂單編號查詢單筆訂單
//		OrderDetailJdbcDAO dao = new OrderDetailJdbcDAO();
//		List<OrderDetailVO> list = dao.getByProdOrderNo(3);
//		for (OrderDetailVO orderDetailVO : list) {
//			System.out.println(orderDetailVO.toString());
//		}

		// 查詢全部
//		OrderDetailJdbcDAO dao = new OrderDetailJdbcDAO();
//		List<OrderDetailVO> list = dao.getAll();
//		for(OrderDetailVO orderDetailVO : list) {
//			System.out.println(orderDetailVO.toString());
//		}

		// 新增訂單明細
//		OrderDetailJdbcDAO dao = new OrderDetailJdbcDAO();
//		OrderDetailVO orderDetailVO = new OrderDetailVO();
//		orderDetailVO.setProdOrderNo(3);
//		orderDetailVO.setProdNo(3);
//		orderDetailVO.setProdQty(5);
//		orderDetailVO.setSubtotal(6);
//		dao.insert(orderDetailVO);
//		System.out.println(orderDetailVO.toString());

		//測試更新評論退貨退款
//		OrderDetailJdbcDAO dao = new OrderDetailJdbcDAO();
//		OrderDetailVO orderDetailVO = new OrderDetailVO();
//		orderDetailVO.setProdNo(3);
//		orderDetailVO.setCommentContent("讚讚");
//		orderDetailVO.setCommentRanking(4.5F);
//		orderDetailVO.setItemNo(12);
//		dao.update(orderDetailVO);
//		System.out.println(orderDetailVO.toString());

		// 測試刪除(好像用不到)
//		OrderDetailJdbcDAO dao = new OrderDetailJdbcDAO();
//		System.out.println(dao.delete(9));
	}


}
