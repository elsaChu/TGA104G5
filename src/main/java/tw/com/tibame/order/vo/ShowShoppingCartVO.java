package tw.com.tibame.order.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "V_SHOPPING_CART")
public class ShowShoppingCartVO implements java.io.Serializable {
	private static final long serialVersionUID = 6939697774809618278L;

	@Id
	@Column(name = "shoppingCartNo")
	private Integer shoppingCartNo;

	@Column(name = "number")
	private Integer number;

	@Column(name = "prodNo")
	private Integer prodNo;

	@Column(name = "shoppingQty")
	private Integer shoppingQty;

	@Column(name = "prodName")
	private String prodName;

	@Column(name = "prodSpec")
	private String prodSpec;

	@Column(name = "unitPrice")
	private Integer unitPrice;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "phoneNumber")
	private String phoneNumber;
	
	@Column(name = "address")
	private String address;


	@Override
	public String toString() {
		return "ShowShoppingCartVO [shoppingCartNo=" + shoppingCartNo + ", number=" + number + ", prodNo=" + prodNo
				+ ", shoppingQty=" + shoppingQty + ", prodName=" + prodName + ", prodSpec=" + prodSpec + ", unitPrice="
				+ unitPrice + ", name=" + name + ", phoneNumber=" + phoneNumber + ", address=" + address + "]";
	}


	public Integer getShoppingCartNo() {
		return shoppingCartNo;
	}


	public void setShoppingCartNo(Integer shoppingCartNo) {
		this.shoppingCartNo = shoppingCartNo;
	}


	public Integer getNumber() {
		return number;
	}


	public void setNumber(Integer number) {
		this.number = number;
	}


	public Integer getProdNo() {
		return prodNo;
	}


	public void setProdNo(Integer prodNo) {
		this.prodNo = prodNo;
	}


	public Integer getShoppingQty() {
		return shoppingQty;
	}


	public void setShoppingQty(Integer shoppingQty) {
		this.shoppingQty = shoppingQty;
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}

	
}
