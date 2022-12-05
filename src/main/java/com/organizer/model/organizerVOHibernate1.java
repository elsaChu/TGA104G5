package com.organizer.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORGANIZER")
public class organizerVOHibernate1 {
	@Id
	private Integer organizerNumber;
	private String OAccount;
	private String Opassword;
	private String organizerName;
	private String windowName;
	private String windowPhone;
	private String windowEmail;
	private String accountName;
	private String accountNumber;
	private String bankCode;
	private String bankName;
	private String taxIDNumber;
	private String boss;
	private String organizerPhone;
	private Boolean OPass;
	private Boolean organizerRevoke;
	private LocalDateTime ORevokeSDate;
	private LocalDateTime ORevokeEDate;
	private String ORevokeContent;
	private Boolean OAmount;
	private Integer staffNumber;

	
	
	@Override
	public String toString() {
		return "organizerVO [organizerNumber=" + organizerNumber + ", OAccount=" + OAccount + ", Opassword=" + Opassword
				+ ", organizerName=" + organizerName + ", windowName=" + windowName + ", windowPhone=" + windowPhone
				+ ", windowEmail=" + windowEmail + ", accountName=" + accountName + ", accountNumber=" + accountNumber
				+ ", bankCode=" + bankCode + ", bankName=" + bankName + ", taxIDNumber=" + taxIDNumber + ", boss="
				+ boss + ", organizerPhone=" + organizerPhone + ", OPass=" + OPass + ", organizerRevoke="
				+ organizerRevoke + ", ORevokeSDate=" + ORevokeSDate + ", ORevokeEDate=" + ORevokeEDate
				+ ", ORevokeContent=" + ORevokeContent + ", OAmount=" + OAmount + ", staffNumber=" + staffNumber + "]";
	}

	public Integer getOrganizerNumber() {
		return organizerNumber;
	}

	public void setOrganizerNumber(Integer organizerNumber) {
		this.organizerNumber = organizerNumber;
	}

	public String getOAccount() {
		return OAccount;
	}

	public void setOAccount(String oAccount) {
		OAccount = oAccount;
	}

	public String getOpassword() {
		return Opassword;
	}

	public void setOpassword(String opassword) {
		Opassword = opassword;
	}

	public String getOrganizerName() {
		return organizerName;
	}

	public void setOrganizerName(String organizerName) {
		this.organizerName = organizerName;
	}

	public String getWindowName() {
		return windowName;
	}

	public void setWindowName(String windowName) {
		this.windowName = windowName;
	}

	public String getWindowPhone() {
		return windowPhone;
	}

	public void setWindowPhone(String windowPhone) {
		this.windowPhone = windowPhone;
	}

	public String getWindowEmail() {
		return windowEmail;
	}

	public void setWindowEmail(String windowEmail) {
		this.windowEmail = windowEmail;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getTaxIDNumber() {
		return taxIDNumber;
	}

	public void setTaxIDNumber(String taxIDNumber) {
		this.taxIDNumber = taxIDNumber;
	}

	public String getBoss() {
		return boss;
	}

	public void setBoss(String boss) {
		this.boss = boss;
	}

	public String getOrganizerPhone() {
		return organizerPhone;
	}

	public void setOrganizerPhone(String organizerPhone) {
		this.organizerPhone = organizerPhone;
	}

	public Boolean getOPass() {
		return OPass;
	}

	public void setOPass(Boolean oPass) {
		OPass = oPass;
	}

	public Boolean getOrganizerRevoke() {
		return organizerRevoke;
	}

	public void setOrganizerRevoke(Boolean organizerRevoke) {
		this.organizerRevoke = organizerRevoke;
	}

	public LocalDateTime getORevokeSDate() {
		return ORevokeSDate;
	}

	public void setORevokeSDate(LocalDateTime oRevokeSDate) {
		ORevokeSDate = oRevokeSDate;
	}

	public LocalDateTime getORevokeEDate() {
		return ORevokeEDate;
	}

	public void setORevokeEDate(LocalDateTime oRevokeEDate) {
		ORevokeEDate = oRevokeEDate;
	}

	public String getORevokeContent() {
		return ORevokeContent;
	}

	public void setORevokeContent(String oRevokeContent) {
		ORevokeContent = oRevokeContent;
	}

	public Boolean getOAmount() {
		return OAmount;
	}

	public void setOAmount(Boolean oAmount) {
		OAmount = oAmount;
	}

	public Integer getStaffNumber() {
		return staffNumber;
	}

	public void setStaffNumber(Integer staffNumber) {
		this.staffNumber = staffNumber;
	}
}
