package tw.com.tibame.management.model;

import java.time.LocalDateTime;

public class ContactUsVO {
	private Integer contactID;
	private String contactTitle;
	private String contactContent;
	private LocalDateTime contactCreateDate;

	@Override
	public String toString() {
		return "ContactUsVO [contactID=" + contactID + ", contactTitle=" + contactTitle + ", contactContent="
				+ contactContent + ", contactCreateDate=" + contactCreateDate + "]";
	}

	public Integer getContactID() {
		return contactID;
	}

	public void setContactID(Integer contactID) {
		this.contactID = contactID;
	}

	public String getContactTitle() {
		return contactTitle;
	}

	public void setContactTitle(String contactTitle) {
		this.contactTitle = contactTitle;
	}

	public String getContactContent() {
		return contactContent;
	}

	public void setContactContent(String contactContent) {
		this.contactContent = contactContent;
	}

	public LocalDateTime getContactCreateDate() {
		return contactCreateDate;
	}

	public void setContactCreateDate(LocalDateTime contactCreateDate) {
		this.contactCreateDate = contactCreateDate;
	}

}
