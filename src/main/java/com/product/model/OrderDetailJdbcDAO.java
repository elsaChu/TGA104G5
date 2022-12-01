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

public class OrderDetailJdbcDAO implements OrderDetailDAO{

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
			Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
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
				Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(SELECT_BY_ID)){
				
				ps.setInt(1, itemNo);
				rs = ps.executeQuery();
				if(rs.next()) {
					result = new OrderDetailVO();
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

	private static final String SELECT_BY_PRODODERNO = "select * from ORDER_DETAIL where prodOrderNo = ?";
	@Override  // 以訂單編號查詢單筆訂單
	public List<OrderDetailVO> getByProdOrderNo(Integer prodOrderNo) {
		List<OrderDetailVO> result = new ArrayList<>();
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try (
			Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = connection.prepareStatement(SELECT_BY_PRODODERNO)) {
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
				Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
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
				Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
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
				Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
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
	
	

}
