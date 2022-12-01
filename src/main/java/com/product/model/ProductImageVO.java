package com.product.model;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_IMG")
public class ProductImageVO implements java.io.Serializable {
	@Id
	@Column(name = "prodIMGID")
	private Integer prodIMGID;		// not null auto_increment
	@Column(name = "prodNo")
	private Integer prodNo;			// not null
	@Column(name = "prodIMGName")
	private String prodIMGName;		
	@Column(name = "prodIMG")
	private byte[] prodIMG;			// not null
	
	@Override
	public String toString() {
		return "ProductImageVO [prodIMGID=" + prodIMGID + ", prodNo=" + prodNo + ", prodIMGName=" + prodIMGName
				+ ", prodIMG=" + Arrays.toString(prodIMG) + "]";
	}
	public Integer getProdIMGID() {
		return prodIMGID;
	}
	public void setProdIMGID(Integer prodIMGID) {
		this.prodIMGID = prodIMGID;
	}
	public Integer getProdNo() {
		return prodNo;
	}
	public void setProdNo(Integer prodNo) {
		this.prodNo = prodNo;
	}
	public String getProdIMGName() {
		return prodIMGName;
	}
	public void setProdIMGName(String prodIMGName) {
		this.prodIMGName = prodIMGName;
	}
	public byte[] getProdIMG() {
		return prodIMG;
	}
	public void setProdIMG(byte[] prodIMG) {
		this.prodIMG = prodIMG;
	}
}
