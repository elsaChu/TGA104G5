package tw.com.tibame.management.model;

import java.sql.Timestamp;

public class TermsOfServiceVO {
	private Integer termsID;
	private String termsTitle;
	private String termsContent;
	private Timestamp termsCreateDate;
	
	@Override
	public String toString() {
		return "TermsOfServiceVO [termsID=" + termsID + ", termsTitle=" + termsTitle + ", termsContent=" + termsContent
				+ ", termsCreateDate=" + termsCreateDate + "]";
	}

	public Integer getTermsID() {
		return termsID;
	}

	public void setTermsID(Integer termsID) {
		this.termsID = termsID;
	}

	public String getTermsTitle() {
		return termsTitle;
	}

	public void setTermsTitle(String termsTitle) {
		this.termsTitle = termsTitle;
	}

	public String getTermsContent() {
		return termsContent;
	}

	public void setTermsContent(String termsContent) {
		this.termsContent = termsContent;
	}

	public Timestamp getTermsCreateDate() {
		return termsCreateDate;
	}

	public void setTermsCreateDate(Timestamp termsCreateDate) {
		this.termsCreateDate = termsCreateDate;
	}

	
}