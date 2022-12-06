package com.order.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import com.order.vo.OrderDetailVO;

public interface OrderDetailService {

	OrderDetailVO addDetail(Integer prodOrderNo, Integer prodNo, Integer prodQty, Integer subtotal);

	// 會員中心 - 單筆訂單查詢
	List<OrderDetailVO> getByProdOrderNo(Integer prodOrderNo);

	// 會員中心 - 更新商品評論
	OrderDetailVO updateComment(Float commentRanking, String commentContent, Timestamp commentDate, String returnReason,
			String refundStatus, Date refundSDate, Date refundEDate, Integer itemNo);

	// 會員中心 - 申請退貨
	OrderDetailVO updateReturn(Float commentRanking, String commentContent, Timestamp commentDate, String returnReason,
			String refundStatus, Date refundSDate, Date refundEDate, Integer itemNo);

	// 廠商訂單管理 - 更新退款狀態
	OrderDetailVO updateRefundStatus(Float commentRanking, String commentContent, Timestamp commentDate,
			String returnReason, String refundStatus, Date refundSDate, Date refundEDate, Integer itemNo);

	// 廠商訂單管理 - 更新退款完成時間
	OrderDetailVO updateRefundDate(Float commentRanking, String commentContent, Timestamp commentDate,
			String returnReason, String refundStatus, Date refundSDate, Date refundEDate, Integer itemNo);

	// 廠商
	List<OrderDetailVO> getAll();

	OrderDetailVO getOneOrderDetail(Integer itemNo);

}