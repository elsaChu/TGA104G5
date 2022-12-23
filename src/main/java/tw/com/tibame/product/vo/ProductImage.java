package tw.com.tibame.product.vo;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT_IMG")
public class ProductImage implements java.io.Serializable {
	private static final long serialVersionUID = 7615933930252293652L;

	@Id
	@Column(name = "prodIMGID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer prodIMGID; 

	@Column(name = "prodNo")
	private Integer prodNo; 

	@Column(name = "prodIMGName")
	private String prodIMGName;

	@Column(name = "prodIMG")
	private byte[] prodIMG;

	@Override
	public String toString() {
		return "ProductImage [prodIMGID=" + prodIMGID + ", prodNo=" + prodNo + ", prodIMGName=" + prodIMGName
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
