package tw.com.tibame.product.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class ProductVO implements Serializable{
	private static final long serialVersionUID = 8001182530313969103L;

	@Id
	@Column(name = "prodNo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Override
	public String toString() {
		return "ProductVO [prodNo=" + prodNo + ", eventNumber=" + eventNumber + ", organizerNumber=" + organizerNumber
				+ ", prodName=" + prodName + ", prodSpec=" + prodSpec + ", unitPrice=" + unitPrice + ", prodStock="
				+ prodStock + ", prodDetails=" + prodDetails + ", prodScore=" + prodScore + ", isPOn=" + isPOn + "]";
	}

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
	
	
	
}
