package tw.com.tibame.order.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SHOPPING_CART")
public class ShoppingCartVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "shoppingCartNo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer shoppingCartNo;
	
	@Column(name = "number")
	private Integer number; 
	
	@Column(name = "prodNo")
	private Integer prodNo; 
	
	@Column(name = "shoppingQty")
	private Integer shoppingQty; 

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
