package com.order.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;

import com.order.dao.OrderDetailDAO;
import com.order.dao.OrderDetailDAOJndi;
import com.order.vo.OrderDetailVO;

public class OrderDetailServiceImpl implements OrderDetailService {
	private OrderDetailDAO dao;
	
	public OrderDetailServiceImpl() {
		dao = new OrderDetailDAOJndi();
	}
	
	@Override
	public OrderDetailVO addDetail(Integer prodOrderNo, Integer prodNo, Integer prodQty, Integer subtotal) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		orderDetailVO.setProdOrderNo(prodOrderNo);
		orderDetailVO.setProdNo(prodNo);
		orderDetailVO.setProdQty(prodQty);
		orderDetailVO.setSubtotal(subtotal);
		dao.insert(orderDetailVO);
		
		return orderDetailVO;
		
	}
	
	// 會員中心 - 以訂單編號查詢明細
	@Override
	public List<OrderDetailVO> getByProdOrderNo(Integer prodOrderNo) {
		return dao.getByProdOrderNo(prodOrderNo);
	}
	
	// 會員中心 - 更新商品評論
	@Override
	public OrderDetailVO updateComment(Float commentRanking, String commentContent, Timestamp commentDate, String returnReason, String refundStatus, Date refundSDate, Date refundEDate, Integer itemNo) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		if(itemNo != null && itemNo > 0) {
			
			if(!commentContent.trim().isEmpty() || commentRanking != 0F)
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
	@Override
	public OrderDetailVO updateReturn(Float commentRanking, String commentContent, Timestamp commentDate, String returnReason, String refundStatus, Date refundSDate, Date refundEDate, Integer itemNo) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		if(itemNo != null && !returnReason.trim().isEmpty()) {
			orderDetailVO.setReturnReason(returnReason);

			
			dao.update(orderDetailVO);
			return orderDetailVO;
		}
		return orderDetailVO;

	}
	
	// 廠商訂單管理 - 更新退款狀態
	@Override
	public OrderDetailVO updateRefundStatus(Float commentRanking, String commentContent, Timestamp commentDate, String returnReason, String refundStatus, Date refundSDate, Date refundEDate, Integer itemNo) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
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
	@Override
	public OrderDetailVO updateRefundDate(Float commentRanking, String commentContent, Timestamp commentDate, String returnReason, String refundStatus, Date refundSDate, Date refundEDate, Integer itemNo) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		if(itemNo != null && refundSDate != null) {
			orderDetailVO.setRefundEDate(refundEDate);
			
			dao.update(orderDetailVO);
			return orderDetailVO;
		}
		return orderDetailVO;

	}
	
	
	
	// 廠商
	@Override
	public List<OrderDetailVO> getAll() {
		return dao.getAll();
	}
	
	
	@Override
	public OrderDetailVO getOneOrderDetail(Integer itemNo) {
		return dao.getPrimaryKey(itemNo);
	}

}
