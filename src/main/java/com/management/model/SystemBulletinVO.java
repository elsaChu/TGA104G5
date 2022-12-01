package com.management.model;

import java.time.LocalDateTime;

public class SystemBulletinVO {
	private Integer bulletinID;            // NOT NULL AUTO_INCREMENT
	private String bulletinName;           // NOT NULL
	private String bulletinContent;        // TEXT NOT NULL
	private LocalDateTime bulletinDate;        // default CURRENT_TIMESTAMP
	private Boolean isTop;                 // NOT NULL default 0
	private Boolean isOpen;                // NOT NULL default 0
	private LocalDateTime bulletinCreateDate;  // default CURRENT_TIMESTAMP
	
	@Override
	public String toString() {
		return "SystemBulletinVO [bulletinID=" + bulletinID + ", bulletinName=" + bulletinName + ", bulletinContent="
				+ bulletinContent + ", bulletinDate=" + bulletinDate + ", isTop=" + isTop + ", isOpen=" + isOpen
				+ ", bulletinCreateDate=" + bulletinCreateDate + "]";
	}
	public Integer getBulletinID() {
		return bulletinID;
	}
	public void setBulletinID(Integer bulletinID) {
		this.bulletinID = bulletinID;
	}
	public String getBulletinName() {
		return bulletinName;
	}
	public void setBulletinName(String bulletinName) {
		this.bulletinName = bulletinName;
	}
	public String getBulletinContent() {
		return bulletinContent;
	}
	public void setBulletinContent(String bulletinContent) {
		this.bulletinContent = bulletinContent;
	}
	public LocalDateTime getBulletinDate() {
		return bulletinDate;
	}
	public void setBulletinDate(LocalDateTime bulletinDate) {
		this.bulletinDate = bulletinDate;
	}
	public Boolean getIsTop() {
		return isTop;
	}
	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
	}
	public Boolean getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}
	public LocalDateTime getBulletinCreateDate() {
		return bulletinCreateDate;
	}
	public void setBulletinCreateDate(LocalDateTime bulletinCreateDate) {
		this.bulletinCreateDate = bulletinCreateDate;
	}
	
	
	
}
