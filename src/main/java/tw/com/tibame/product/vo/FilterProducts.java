package tw.com.tibame.product.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "v_product_eventclass")
public class FilterProducts implements Serializable{
	private static final long serialVersionUID = -5342854916614680133L;

	@Id
	@Column(name = "prodNo")
	private Integer prodNo;
	
	@Column(name = "eventNumber")
	private Integer eventNumber;
	
	@Column(name = "organizerNumber")
	private Integer organizerNumber;
	
	@Column(name = "prodName")
	private String prodName;
	
	@Column(name = "prodSpec")
	private String prodSpec;
	
	@Column(name = "unitPrice")
	private Integer unitPrice;
	
	@Column(name = "prodStock")
	private Integer prodStock;
	
	@Column(name = "prodDetails")
	private String prodDetails;
	
	@Column(name = "prodScore")
	private Float prodScore;
	
	@Column(name = "isPOn")
	private Boolean isPOn;
	
	@Column(name = "eventName")
	private String eventName;
	
	@Column(name = "eventType")
	private String eventType;
	
	@Column(name = "commentQty")
	private Integer commentQty;
	
	@Column(name = "totalComment")
	private Integer totalComment;
	
	@Column(name = "eventClassName")
	private String eventClassName;
	
	@Column(name = "eventClassState")
	private Integer eventClassState;

	public Integer getProdNo() {
		return prodNo;
	}

	public void setProdNo(Integer prodNo) {
		this.prodNo = prodNo;
	}

	public Integer getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(Integer eventNumber) {
		this.eventNumber = eventNumber;
	}

	public Integer getOrganizerNumber() {
		return organizerNumber;
	}

	public void setOrganizerNumber(Integer organizerNumber) {
		this.organizerNumber = organizerNumber;
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

	public Integer getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getProdStock() {
		return prodStock;
	}

	public void setProdStock(Integer prodStock) {
		this.prodStock = prodStock;
	}

	public String getProdDetails() {
		return prodDetails;
	}

	public void setProdDetails(String prodDetails) {
		this.prodDetails = prodDetails;
	}

	public Float getProdScore() {
		return prodScore;
	}

	public void setProdScore(Float prodScore) {
		this.prodScore = prodScore;
	}

	public Boolean getIsPOn() {
		return isPOn;
	}

	public void setIsPOn(Boolean isPOn) {
		this.isPOn = isPOn;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Integer getCommentQty() {
		return commentQty;
	}

	public void setCommentQty(Integer commentQty) {
		this.commentQty = commentQty;
	}

	public Integer getTotalComment() {
		return totalComment;
	}

	public void setTotalComment(Integer totalComment) {
		this.totalComment = totalComment;
	}

	public String getEventClassName() {
		return eventClassName;
	}

	public void setEventClassName(String eventClassName) {
		this.eventClassName = eventClassName;
	}

	public Integer getEventClassState() {
		return eventClassState;
	}

	public void setEventClassState(Integer eventClassState) {
		this.eventClassState = eventClassState;
	}
	
	
}
