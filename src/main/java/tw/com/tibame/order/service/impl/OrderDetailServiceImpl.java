package tw.com.tibame.order.service.impl;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tw.com.tibame.order.dao.OrderDetailDAO;
import tw.com.tibame.order.service.OrderDetailService;
import tw.com.tibame.order.vo.OrderDetailVO;
@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {
	@Autowired
	private OrderDetailDAO orderDetailDAO;
	
	public OrderDetailServiceImpl(OrderDetailDAO orderDetailDAO) {
		super();
		this.orderDetailDAO = orderDetailDAO;
	}
	
	// 新增訂單明細
	@Override
	public OrderDetailVO addDetail(OrderDetailVO orderDetailVO) {
		if(orderDetailVO != null && orderDetailVO.getItemNo() == null) {
			
			return orderDetailDAO.insert(orderDetailVO);
		}
		return null;
		
	}
	
	// 會員中心 - 以訂單編號查詢明細
	@Override
	public List<OrderDetailVO> getByProdOrderNo(Integer prodOrderNo) {
		return orderDetailDAO.getByProdOrderNo(prodOrderNo);
	}
	
	// 會員中心 - 訂單內單一明細查詢
		@Override
		public OrderDetailVO getOneOrderDetail(Integer itemNo) {
			return orderDetailDAO.getPrimaryKey(itemNo);
		}
	
	// 會員中心 - 更新商品評論
	@Override
	public OrderDetailVO updateComment(Integer itemNo, Float commentRanking, String commentContent) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		if(orderDetailDAO.getPrimaryKey(itemNo) != null) {
			
			if(!commentContent.trim().isEmpty() || commentRanking != 0F)
//			orderDetailVO.setItemNo(itemNo);
			orderDetailVO.setCommentRanking(commentRanking);
			orderDetailVO.setCommentContent(commentContent);
			orderDetailVO.setCommentDate(new java.sql.Timestamp(new GregorianCalendar().getTimeInMillis()));
			
			orderDetailDAO.update(orderDetailVO);
			return orderDetailVO;
		}
		return orderDetailVO;
	}
	
	// 會員中心 - 申請退貨
	@Override
	public OrderDetailVO updateReturn(Integer itemNo, String returnReason) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		if(orderDetailDAO.getPrimaryKey(itemNo) != null && !returnReason.trim().isEmpty()) {
			orderDetailVO.setReturnReason(returnReason);
			
			orderDetailDAO.update(orderDetailVO);
			return orderDetailVO;
		}
		return orderDetailVO;
	}
	
	// 廠商訂單管理 - 更新退款狀態
	@Override
	public OrderDetailVO updateRefundStatus(Integer itemNo, String refundStatus, Date refundSDate, Date refundEDate) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		if(itemNo != null) {
			if(refundStatus != null) {
				orderDetailVO.setRefundStatus(refundStatus);			
			} else {
				orderDetailVO.setRefundStatus("處理中");
				orderDetailVO.setRefundSDate(new java.sql.Date(new GregorianCalendar().getTimeInMillis()));
			}
			orderDetailDAO.update(orderDetailVO);
			return orderDetailVO;
		}
		return orderDetailVO;
	}
	
	// 廠商訂單管理 - 更新退款完成時間
	@Override
	public OrderDetailVO updateRefundDate(Integer itemNo, Date refundSDate, Date refundEDate) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		if(itemNo != null && refundSDate != null) {
			orderDetailVO.setRefundEDate(refundEDate);
			
			orderDetailDAO.update(orderDetailVO);
			return orderDetailVO;
		}
		return orderDetailVO;
	}
	
	


}
