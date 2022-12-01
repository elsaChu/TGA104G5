package com.product.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;

public class OrderDetailService {
	private OrderDetailDAO dao;
	
	public OrderDetailService() {
		dao = new OrderDetailJdbcDAO();
	}
	
	public OrderDetailVO addDetail(Integer prodOrderNo, Integer prodNo, Integer prodQty, Integer subtotal) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		orderDetailVO.setProdOrderNo(prodOrderNo);
		orderDetailVO.setProdNo(prodNo);
		orderDetailVO.setProdQty(prodQty);
		orderDetailVO.setSubtotal(subtotal);
		dao.insert(orderDetailVO);
		
		return orderDetailVO;
		
	}
	
	// 會員中心 - 單筆訂單查詢
		public List<OrderDetailVO> getByProdOrderNo(Integer prodOrderNo) {
			return dao.getByProdOrderNo(prodOrderNo);
		}
	
	// 會員中心 - 更新商品評論
	public OrderDetailVO updateComment(Float commentRanking, String commentContent, Timestamp commentDate, String returnReason, String refundStatus, Date refundSDate, Date refundEDate, Integer itemNo) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
//		orderDetailVO.setItemNo(itemNo);
		
		if(itemNo != null) {
			
			if(commentContent.trim().isEmpty() == false || commentRanking != 0.0F)
			orderDetailVO.setItemNo(itemNo);
			orderDetailVO.setCommentRanking(commentRanking);
			orderDetailVO.setCommentContent(commentContent);
			orderDetailVO.setCommentDate(new java.sql.Timestamp(new GregorianCalendar().getTimeInMillis()));
			
			dao.update(orderDetailVO);
			return orderDetailVO;
		}
		return orderDetailVO;

		
	}
	
	// 會員中心 - 申請退貨
	public OrderDetailVO updateReturn(Float commentRanking, String commentContent, Timestamp commentDate, String returnReason, String refundStatus, Date refundSDate, Date refundEDate, Integer itemNo) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
//		orderDetailVO.setItemNo(itemNo);
		if(itemNo != null && returnReason.trim().isEmpty() != true) {
			orderDetailVO.setReturnReason(returnReason);

			
			dao.update(orderDetailVO);
			return orderDetailVO;
		}
		return orderDetailVO;

	}
	
	// 廠商訂單管理 - 更新退款狀態
	public OrderDetailVO updateRefundStatus(Float commentRanking, String commentContent, Timestamp commentDate, String returnReason, String refundStatus, Date refundSDate, Date refundEDate, Integer itemNo) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
//		orderDetailVO.setItemNo(itemNo);
		if(itemNo != null) {
			if(refundStatus != null) {
				orderDetailVO.setRefundStatus(refundStatus);			
			} else {
				orderDetailVO.setRefundStatus("處理中");
				orderDetailVO.setRefundSDate(new java.sql.Date(new GregorianCalendar().getTimeInMillis()));
			}

			dao.update(orderDetailVO);
			return orderDetailVO;
		}
		return orderDetailVO;

	}
	
	// 廠商訂單管理 - 更新退款完成時間
	public OrderDetailVO updateRefundDate(Float commentRanking, String commentContent, Timestamp commentDate, String returnReason, String refundStatus, Date refundSDate, Date refundEDate, Integer itemNo) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
//		orderDetailVO.setItemNo(itemNo);
		if(itemNo != null && refundSDate != null) {
			orderDetailVO.setRefundEDate(refundEDate);
			
			dao.update(orderDetailVO);
			return orderDetailVO;
		}
		return orderDetailVO;

	}
	
	
	
	// 廠商
	public List<OrderDetailVO> getAll() {
		return dao.getAll();
	}
	
	
	public OrderDetailVO getOneOrderDetail(Integer itemNo) {
		return dao.getPrimaryKey(itemNo);
	}

}
