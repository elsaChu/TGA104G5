package tw.com.tibame.management.model;

import java.time.LocalDateTime;

public class privacyVO {

	private Integer privacyID;
	private String privacyTitle;
	private String privacyContent;
	private LocalDateTime privacyCreateDate;

	@Override
	public String toString() {
		return "privacyVO [privacyID=" + privacyID + ", privacyTitle=" + privacyTitle + ", privacyContent="
				+ privacyContent + ", privacyCreateDate=" + privacyCreateDate + "]";
	}

	public Integer getPrivacyID() {
		return privacyID;
	}

	public void setPrivacyID(Integer privacyID) {
		this.privacyID = privacyID;
	}

	public String getPrivacyTitle() {
		return privacyTitle;
	}

	public void setPrivacyTitle(String privacyTitle) {
		this.privacyTitle = privacyTitle;
	}

	public String getPrivacyContent() {
		return privacyContent;
	}

	public void setPrivacyContent(String privacyContent) {
		this.privacyContent = privacyContent;
	}

	public LocalDateTime getPrivacyCreateDate() {
		return privacyCreateDate;
	}

	public void setPrivacyCreateDate(LocalDateTime privacyCreateDate) {
		this.privacyCreateDate = privacyCreateDate;
	}

}
