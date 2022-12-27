package tw.com.tibame.order.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "PROD_ORDER")
public class ProductOrderVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "prodOrderNo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer prodOrderNo;
	
	@Column(name = "number")
	private Integer number;

	@Column(name = "amountPrice")
	private Integer amountPrice;

	@Column(name = "prodTotal")
	private Integer prodTotal;
	
	@Column(name = "paymentDate")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Timestamp paymentDate;
	
	@Column(name = "receiverName")
	private String receiverName;
	
	@Column(name = "receiverTel")
	private String receiverTel;
	
	@Column(name = "shippingAdd")
	private String shippingAdd;

	@Column(name = "prodOrderStatus")
	private String prodOrderStatus;

	@Column(name = "deliveryStatus")
	private String deliveryStatus;

	@Column(name = "orderNotes")
	private String orderNotes;

	@Override
	public String toString() {
		return "ProductOrderVO [prodOrderNo=" + prodOrderNo + ", number=" + number + ", amountPrice=" + amountPrice
				+ ", prodTotal=" + prodTotal + ", paymentDate=" + paymentDate + ", receiverName=" + receiverName
				+ ", receiverTel=" + receiverTel + ", shippingAdd=" + shippingAdd + ", prodOrderStatus="
				+ prodOrderStatus + ", deliveryStatus=" + deliveryStatus + ", orderNotes=" + orderNotes + "]";
	}

	public Integer getProdOrderNo() {
		return prodOrderNo;
	}

	public void setProdOrderNo(Integer prodOrderNo) {
		this.prodOrderNo = prodOrderNo;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getAmountPrice() {
		return amountPrice;
	}

	public void setAmountPrice(Integer amountPrice) {
		this.amountPrice = amountPrice;
	}

	public Integer getProdTotal() {
		return prodTotal;
	}

	public void setProdTotal(Integer prodTotal) {
		this.prodTotal = prodTotal;
	}

	public Timestamp getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverTel() {
		return receiverTel;
	}

	public void setReceiverTel(String receiverTel) {
		this.receiverTel = receiverTel;
	}

	public String getShippingAdd() {
		return shippingAdd;
	}

	public void setShippingAdd(String shippingAdd) {
		this.shippingAdd = shippingAdd;
	}

	public String getProdOrderStatus() {
		return prodOrderStatus;
	}

	public void setProdOrderStatus(String prodOrderStatus) {
		this.prodOrderStatus = prodOrderStatus;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getOrderNotes() {
		return orderNotes;
	}

	public void setOrderNotes(String orderNotes) {
		this.orderNotes = orderNotes;
	}

}
