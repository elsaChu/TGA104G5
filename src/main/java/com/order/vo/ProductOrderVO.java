package com.order.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROD_ORDER")
public class ProductOrderVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
		@Id
		@Column(name = "prodOrderNo")
		@GeneratedValue(strategy = GenerationType.IDENTITY)  
		private Integer prodOrderNo; 		// not null auto_increment
		
		@Column(name = "number")
		private Integer number;				// not null
		
		@Column(name = "amountPrice")
		private Integer amountPrice; 		// not null
		
		@Column(name = "prodTotal")
		private Integer prodTotal;			// not null
		
		@Column(name = "paymentDate")
		private Timestamp paymentDate;		// not null default current_timestamp
		
		@Column(name = "receiverName")
		private String receiverName;  		// not null
		
		@Column(name = "receiverTel")
		private String receiverTel; 		// not null
		
		@Column(name = "shippingAdd")
		private String shippingAdd;			// not null
		
		@Column(name = "prodOrderStatus")
		private String prodOrderStatus; 	// not null default
		
		@Column(name = "deliveryStatus")
		private String deliveryStatus;		// not null default
		
		@Override
		public String toString() {
			return "ProductOrderVO [prodOrderNo=" + prodOrderNo + ", number=" + number + ", amountPrice=" + amountPrice
					+ ", prodTotal=" + prodTotal + ", paymentDate=" + paymentDate + ", receiverName=" + receiverName
					+ ", receiverTel=" + receiverTel + ", shippingAdd=" + shippingAdd + ", prodOrderStatus="
					+ prodOrderStatus + ", deliveryStatus=" + deliveryStatus + "]";
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
		
}
