package com.product.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_DETAIL")
public class OrderDetailVO implements java.io.Serializable {
	@Id
	@Column(name = "itemNo")
	private Integer itemNo;				// not null auto_increment
	@Column(name = "prodOrderNo")
	private Integer prodOrderNo;		// not null
	@Column(name = "prodNo")
	private Integer prodNo;				// not null
	@Column(name = "prodQty")
	private Integer prodQty;			// not null
	@Column(name = "subtotal")
	private Integer subtotal;			// not null
	@Column(name = "commentRanking")
	private Float commentRanking;		// not null default 0
	@Column(name = "commentContent")
	private String commentContent;
	@Column(name = "commentDate")
	private LocalDateTime commentDate;	
	@Column(name = "returnReason")
	private String returnReason;
	@Column(name = "refundStatus")
	private String refundStatus;		
	@Column(name = "refundSDate")
	private LocalDate refundSDate;
	@Column(name = "refundEDate")
	private LocalDate refundEDate;
	
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
	public LocalDateTime getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(LocalDateTime commentDate) {
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
	public LocalDate getRefundSDate() {
		return refundSDate;
	}
	public void setRefundSDate(LocalDate refundSDate) {
		this.refundSDate = refundSDate;
	}
	public LocalDate getRefundEDate() {
		return refundEDate;
	}
	public void setRefundEDate(LocalDate refundEDate) {
		this.refundEDate = refundEDate;
	}
	
	
}
