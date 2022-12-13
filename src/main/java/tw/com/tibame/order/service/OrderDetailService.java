package tw.com.tibame.order.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import tw.com.tibame.order.vo.OrderDetailVO;

public interface OrderDetailService {

	OrderDetailVO addDetail(OrderDetailVO orderDetailVO);

	// 會員中心 - 單筆訂單查詢
	List<OrderDetailVO> getByProdOrderNo(Integer prodOrderNo);

	// 會員中心 - 更新商品評論
	OrderDetailVO updateComment(Integer itemNo, Float commentRanking, String commentContent);

	// 會員中心 - 申請退貨
	OrderDetailVO updateReturn(Integer itemNo, String returnReason);

	// 廠商訂單管理 - 更新退款狀態
	OrderDetailVO updateRefundStatus(Integer itemNo, String refundStatus, Date refundSDate, Date refundEDate);

	// 廠商訂單管理 - 更新退款完成時間
	OrderDetailVO updateRefundDate(Integer itemNo, Date refundSDate, Date refundEDate);


	OrderDetailVO getOneOrderDetail(Integer itemNo);


}