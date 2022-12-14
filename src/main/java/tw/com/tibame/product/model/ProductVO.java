package tw.com.tibame.product.model;

//import javax.persistence.*;

//@Entity
//@Table(name = "PRODUCT")

public class ProductVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

//	the variables correspond to the fields in the database, respectively
//	@Id
//	@Column(name = "prodNo")
	private Integer prodNo; // not null auto_increment
//	@Column(name = "eventNumber")
	private Integer eventNumber; // not null
//	@Column(name = "organizerNumber")
	private Integer organizerNumber; // not null
//	@Column(name = "prodName")
	private String prodName; // not null
//	@Column(name = "prodSpec")
	private String prodSpec; // not null
//	@Column(name = "unitPrice")
	private Integer unitPrice; // not null
//	@Column(name = "prodStock")
	private Integer prodStock; // not null
//	@Column(name = "prodDetails")
	private String prodDetails;
//	@Column(name = "prodScore")
	private Float prodScore; // not null
//	@Column(name = "isPOn")
	private Boolean isPOn; // not null

//	override toString() to return strings in human-legible form
	@Override
	public String toString() {
		return "Product [prodNo=" + prodNo + ", eventNumber=" + eventNumber + ", organizerNumber=" + organizerNumber
				+ ", prodName=" + prodName + ", prodSpec=" + prodSpec + ", unitPrice=" + unitPrice + ", prodStock="
				+ prodStock + ", prodDetails=" + prodDetails + ", prodScore=" + prodScore + ", isPOn=" + isPOn + "]";
	}

//	accessors
	public Integer getProdNo() {
		return prodNo;
	}

//	mutators
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