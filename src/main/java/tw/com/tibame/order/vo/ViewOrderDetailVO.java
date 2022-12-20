package tw.com.tibame.order.vo;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "V_ORDER_DETAIL")
public class ViewOrderDetailVO implements java.io.Serializable {
private static final long serialVersionUID = 1L;
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
	private Float commentRanking;		
	
	@Column(name = "commentContent")
	private String commentContent;
	
	@Column(name = "commentDate")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Timestamp commentDate;
	
	@Column(name = "returnReason")
	private String returnReason;
	
	@Column(name = "refundStatus")
	private String refundStatus;		
	
	@Column(name = "refundSDate")
	private Date refundSDate;
	
	@Column(name = "refundEDate")
	private Date refundEDate;
	
	@Column(name = "prodName")
	private String prodName;
	
	@Column(name = "prodSpec")
	private String prodSpec;

	@Override
	public String toString() {
		return "ViewOrderDetailVO [itemNo=" + itemNo + ", prodOrderNo=" + prodOrderNo + ", prodNo=" + prodNo
				+ ", prodQty=" + prodQty + ", subtotal=" + subtotal + ", commentRanking=" + commentRanking
				+ ", commentContent=" + commentContent + ", commentDate=" + commentDate + ", returnReason="
				+ returnReason + ", refundStatus=" + refundStatus + ", refundSDate=" + refundSDate + ", refundEDate="
				+ refundEDate + ", prodName=" + prodName + ", prodSpec=" + prodSpec + "]";
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

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdSpec() {
		return prodSpec;
	}

	public void setProdSpec(String prodSpec) {
		this.prodSpec = prodSpec;
	}
	
	
	
	
}
