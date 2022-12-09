package tw.com.tibame.product.model;

import java.util.Arrays;


public class ProductImageVO implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer prodIMGID;		// not null auto_increment
	private Integer prodNo;			// not null
	private String prodIMGName;		
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
