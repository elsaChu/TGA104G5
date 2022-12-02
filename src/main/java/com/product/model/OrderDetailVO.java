package com.product.model;

import java.sql.Date;
import java.sql.Timestamp;



public class OrderDetailVO implements java.io.Serializable {
private static final long serialVersionUID = 1L;

	private Integer itemNo;				// not null auto_increment
	private Integer prodOrderNo;		// not null
	private Integer prodNo;				// not null
	private Integer prodQty;			// not null
	private Integer subtotal;			// not null
	private Float commentRanking;		// not null default 0
	private String commentContent;
	private Timestamp commentDate;	
	private String returnReason;
	private String refundStatus;		
	private Date refundSDate;
	private Date refundEDate;
	
	@Override
	public String toString() {
		return "OrderDetailVO [itemNo=" + itemNo + ", prodOrderNo=" + prodOrderNo + ", prodNo=" + prodNo + ", prodQty="
				+ prodQty + ", subtotal=" + subtotal + ", commentRanking=" + commentRanking + ", commentContent="
				+ commentContent + ", commentDate=" + commentDate + ", returnReason=" + returnReason + ", refundStatus="
				+ refundStatus + ", refundSDate=" + refundSDate + ", refundEDate=" + refundEDate + "]";
	}
	public Integer getItemNo() {
		return itemNo;
	}
	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	public Integer getProdOrderNo() {
		return prodOrderNo;
	}
	public void setProdOrderNo(Integer prodOrderNo) {
		this.prodOrderNo = prodOrderNo;
	}
	public Integer getProdNo() {
		return prodNo;
	}
	public void setProdNo(Integer prodNo) {
		this.prodNo = prodNo;
	}
	public Integer getProdQty() {
		return prodQty;
	}
	public void setProdQty(Integer prodQty) {
		this.prodQty = prodQty;
	}
	public Integer getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Integer subtotal) {
		this.subtotal = subtotal;
	}
	public Float getCommentRanking() {
		return commentRanking;
	}
	public void setCommentRanking(Float commentRanking) {
		this.commentRanking = commentRanking;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Timestamp getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}
	public String getReturnReason() {
		return returnReason;
	}
	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}
	public String getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
	public Date getRefundSDate() {
		return refundSDate;
	}
	public void setRefundSDate(Date refundSDate) {
		this.refundSDate = refundSDate;
	}
	public Date getRefundEDate() {
		return refundEDate;
	}
	public void setRefundEDate(Date refundEDate) {
		this.refundEDate = refundEDate;
	}
	
	
}
