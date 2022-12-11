package tw.com.tibame.management.model;

import java.time.LocalDateTime;

public class CommonQAVO {
	private Integer commonID;           // NOT NULL AUTO_INCREMENT
	private String commonName;          // NOT NULL
	private String commonContent;       // NOT NULL
	private LocalDateTime commonCreateDate; // default CURRENT_TIMESTAMP
	private Integer sort;               // NOT NULL
	@Override
	public String toString() {
		return "CommonQAVO [commonID=" + commonID + ", commonName=" + commonName + ", commonContent=" + commonContent
				+ ", commonCreateDate=" + commonCreateDate + ", sort=" + sort + "]";
	}
	public Integer getCommonID() {
		return commonID;
	}
	public void setCommonID(Integer commonID) {
		this.commonID = commonID;
	}
	public String getCommonName() {
		return commonName;
	}
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	public String getCommonContent() {
		return commonContent;
	}
	public void setCommonContent(String commonContent) {
		this.commonContent = commonContent;
	}
	public LocalDateTime getCommonCreateDate() {
		return commonCreateDate;
	}
	public void setCommonCreateDate(LocalDateTime commonCreateDate) {
		this.commonCreateDate = commonCreateDate;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	
}
