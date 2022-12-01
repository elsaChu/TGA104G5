package com.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHOPPING_CART")
public class ShoppingCartVO implements java.io.Serializable {
	@Id
	@Column(name = "shoppingCartNo")
	private Integer shoppingCartNo;		// not null auto_increment
	@Column(name = "number")
	private Integer number;				// not null
	@Column(name = "prodNo")
	private Integer shoppingQty;		// not null
	@Column(name = "shoppingQty")
	private Integer prodNo;				// not null

	@Override
	public String toString() {
		return "ShoppingCartVO [shoppingCartNo=" + shoppingCartNo + ", number=" + number + ", prodNo=" + prodNo
				+ ", shoppingQty=" + shoppingQty + "]";
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
}
